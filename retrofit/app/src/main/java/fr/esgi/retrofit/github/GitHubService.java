package fr.esgi.retrofit.github;

import java.util.List;

import fr.esgi.retrofit.myclass.Repo;
import fr.esgi.retrofit.myclass.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by mohsan on 10/05/16.
 */
public interface GitHubService {

    String END_POINT = "https://api.github.com";
    String TOKEN = "TOKEN_HERE";

    @Headers("Authorization: token "+TOKEN)
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @Headers("Authorization: token "+TOKEN)
    @GET("users/{username}/repos")
    Call<List<Repo>> getRepo(@Path("username") String username);

}
