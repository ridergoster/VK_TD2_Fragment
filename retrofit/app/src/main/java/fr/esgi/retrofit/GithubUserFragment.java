package fr.esgi.retrofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vincentk on 14/06/2016.
 */

public class GithubUserFragment extends Fragment {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pseudo)
    TextView pseudo;
    @BindView(R.id.followers)
    TextView followers;
    @BindView(R.id.image)
    ImageView avatar;

    GitHubService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_github_user, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        service = GithubWebService.get();
        String username = getArguments().getString(InputActivity.GITHUB_NAME);
        loadUser(username);
    }

    public void cancelActivity(int code) {
        if(code == 404) {
            Toast.makeText(this.getActivity(),"User does not exist...", Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
        }
    }

    public void loadUser(String name) {
        service.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    displayUser(user);
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

    public static GithubUserFragment newInstance(String username) {
        GithubUserFragment myFragment = new GithubUserFragment();
        Bundle args = new Bundle();
        args.putString(InputActivity.GITHUB_NAME, username);
        myFragment.setArguments(args);
        return myFragment;
    }

    public void displayUser(User user) {
        name.setText(user.getName());
        pseudo.setText(user.getLogin());
        followers.setText(user.getFollowers());

        if (user.getAvatarUrl() != null) {
            Glide.with(avatar.getContext())
                    .load(user.getAvatarUrl())
                    .into(avatar);
        }

    }
}