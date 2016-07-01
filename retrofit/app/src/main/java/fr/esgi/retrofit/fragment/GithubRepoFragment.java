package fr.esgi.retrofit.fragment;

/**
 * Created by vincentk on 14/06/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.github.GitHubService;
import fr.esgi.retrofit.github.GithubWebService;
import fr.esgi.retrofit.activity.InputActivity;
import fr.esgi.retrofit.myclass.MyAdapter;
import fr.esgi.retrofit.R;
import fr.esgi.retrofit.myclass.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vincentk on 14/06/2016.
 */

public class GithubRepoFragment extends Fragment {

    @BindView(R.id.repoRecyclerView)
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    GitHubService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_github_repo, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        service = GithubWebService.get();
        String username = getArguments().getString(InputActivity.GITHUB_NAME);
        loadRepo(username);
    }

    public void loadRepo(String name) {
        service.getRepo(name).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                myAdapter.addObjects(repos);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
    }

    public static GithubRepoFragment newInstance(String username) {
        GithubRepoFragment myFragment = new GithubRepoFragment();
        Bundle args = new Bundle();
        args.putString(InputActivity.GITHUB_NAME, username);
        myFragment.setArguments(args);
        return myFragment;
    }
}