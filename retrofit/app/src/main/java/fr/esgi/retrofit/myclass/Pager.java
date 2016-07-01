package fr.esgi.retrofit.myclass;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.esgi.retrofit.tabs.TabFollowed;
import fr.esgi.retrofit.tabs.TabFollowers;
import fr.esgi.retrofit.tabs.TabRepo;

/**
 * Created by vincentk on 26/06/2016.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;
    User user;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount, User user) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
        this.user = user;
    }


    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                TabRepo tabRepo = new TabRepo(this.getUser());
                return tabRepo;
            case 1:
                TabFollowers tabFollowers = new TabFollowers(this.getUser());
                return tabFollowers;
            case 2:
                TabFollowed tabFollowed = new TabFollowed(this.getUser());
                return tabFollowed;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

    public User getUser() {
        return user;
    }
}