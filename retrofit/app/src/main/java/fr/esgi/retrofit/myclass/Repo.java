package fr.esgi.retrofit.myclass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vincentk on 14/06/2016.
 */
public class Repo {

    public String id;
    public String name;

    @SerializedName("stargazers_count")
    public String starCount ;

    @SerializedName("open_issues")
    public String issues;

    @SerializedName("watchers_count")
    public String watchersCount;

    public String forks;

    public Repo(String forks, String id, String issues, String name, String starCount, String watchersCount) {
        this.forks = forks;
        this.id = id;
        this.issues = issues;
        this.name = name;
        this.starCount = starCount;
        this.watchersCount = watchersCount;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }
}
