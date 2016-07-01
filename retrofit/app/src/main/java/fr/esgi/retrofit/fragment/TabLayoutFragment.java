package fr.esgi.retrofit.fragment;

/**
 * Created by vincentk on 26/06/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.github.GitHubService;
import fr.esgi.retrofit.github.GithubWebService;
import fr.esgi.retrofit.activity.InputActivity;
import fr.esgi.retrofit.myclass.Pager;
import fr.esgi.retrofit.R;
import fr.esgi.retrofit.myclass.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabLayoutFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    GitHubService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tablayout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        tabLayout.addTab(tabLayout.newTab().setText("Repository"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.addTab(tabLayout.newTab().setText("Followed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Service Web
        service = GithubWebService.get();
        String username = getArguments().getString(InputActivity.GITHUB_NAME);
        loadUser(username);
    }

    public void setViewPager(User user) {
        //Creating our pager adapter
        Pager adapter = new Pager(getFragmentManager(), tabLayout.getTabCount(), user);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);

    }


    public void cancelActivity(int code) {
        if(code == 404) {
            Toast.makeText(this.getActivity(),"User does not exist...", Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
        }
    }

    public static TabLayoutFragment newInstance(String username) {
        TabLayoutFragment myFragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(InputActivity.GITHUB_NAME, username);
        myFragment.setArguments(args);
        return myFragment;
    }


    public void loadUser(String name) {
        service.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    setViewPager(user);
                } else {
                    cancelActivity(response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}