package fr.esgi.retrofit.myclass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohsan on 10/05/16.
 */

public class User {

    public String name;
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;
    public String followers;
    @SerializedName("public_repos")
    public String publicrepos;
    public String following;

    public User(String avatarUrl, String followers, String following, String login, String name, String publicrepos) {
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.following = following;
        this.login = login;
        this.name = name;
        this.publicrepos = publicrepos;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublicrepos(String publicrepos) {
        this.publicrepos = publicrepos;
    }

    public String getFollowing() {
        return following;
    }

    public String getPublicrepos() {
        return publicrepos;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFollowers() {
        return followers;
    }
}
