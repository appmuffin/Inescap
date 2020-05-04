package com.example.inescap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting the customised toolbar as default one.
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //now find our navigation controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupBottomNavMenu(navController);
        setupToolbar(navController);

    }

    //Function to relate bottom navigation with Nav controller
    private void setupBottomNavMenu(NavController navController){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    //Function to relate toolbar with Nav controller and set all fragments as top level containers
    //to remove a back button in top left corner.
    private void setupToolbar(NavController navController){
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.navigate_home,
                        R.id.navigate_chat,
                        R.id.navigate_addpost,
                        R.id.navigate_search,
                        R.id.navigate_profile,
                        R.id.notifications,
                        R.id.settings,R.id.interests).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    }

   //Function to create i.e. inflate icons in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
    //Function for action to take place when we click on toolbar icons.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        boolean navigated=NavigationUI.onNavDestinationSelected(item,navController);
        return NavigationUI.onNavDestinationSelected(item,navController)?navigated:
                super.onOptionsItemSelected(item);
     }

}






















      /*  bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();*/

    //}
    /*The below function replaceFragment adds fragments to backstack if it is called 1st time
    * but if called 2nd or more time it will just start transaction and not add on backstack..
    * checks whether a fragment present in backstack */
   /* private void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager manager = this.getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);


        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {// fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment, fragmentTag);
            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
    }
    }

  //Below function removes backstacks one by one on backpress.
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()> 0) {
            super.onBackPressed();
        }
        else {
            finish();
        }
    }

    //The below function is to show toolbar_menu items on the toolbar display and on clicking them,
    // action should be
    //performed like opening of a fragment.

    //here we inflated the toolbar_menu items into "menu" (created here).
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    /* The below function will open settings and notification fragment when we
    * click on their icons respectively*/

   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selected=null;

        switch (item.getItemId()){
            case R.id.notifications:
                selected=new NotificationFragment();
                toolbar.setTitle("Notifications");
                break;

            case R.id.settings:
                selected=new SettingsFragment();
                toolbar.setTitle("Settings");
                break;
        }
        replaceFragment(selected);


        return true;
    }

    /* The below function helps Bottom navigation elements to be selected and
    actions to be performed when they are clicked */

  /*  private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch (menuItem.getItemId()){

                        case R.id.navigate_home:
                            selectedFragment=new HomeFragment();
                            toolbar.setTitle("Inescap");
                            break;

                        case R.id.navigate_chat:
                            selectedFragment=new ChatFragment();
                            toolbar.setTitle("Chat");
                            break;

                        case R.id.navigate_addpost:
                            selectedFragment=new AddpostFragment();
                            toolbar.setTitle("Add Post");
                            break;

                        case R.id.navigate_search:
                            selectedFragment=new SearchFragment();
                            toolbar.setTitle("Search");
                            break;

                        case R.id.navigate_profile:
                            selectedFragment=new ProfileFragment();
                            toolbar.setTitle("Profile");
                            break;
                    }
                     replaceFragment(selectedFragment);

                    return true;
                }
            };

}*/
