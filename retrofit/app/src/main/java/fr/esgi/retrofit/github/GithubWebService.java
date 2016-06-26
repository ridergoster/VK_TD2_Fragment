package fr.esgi.retrofit.github;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubWebService {

    static GithubWebService INSTANCE;
    final GitHubService gitHubService;

    public GithubWebService() {
        this.gitHubService = new Retrofit.Builder()
            .baseUrl(GitHubService.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService.class);
    }

    public static GitHubService get() {
        if(INSTANCE == null){
            INSTANCE = new GithubWebService();
        }

        return INSTANCE.gitHubService;
    }

}
