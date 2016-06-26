package fr.esgi.retrofit.myclass;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.R;

/**
 * Created by vincentk on 14/06/2016.
 */
public class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repo_id)
    TextView id;
    @BindView(R.id.repo_name)
    TextView name;
    @BindView(R.id.repo_forks)
    TextView forks;
    @BindView(R.id.repo_stars)
    TextView stars;
    @BindView(R.id.repo_issues)
    TextView issues;

    public RepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Repo repo){
        String idStr = repo.getId();
        String nameStr = repo.getName();
        String forkStr = repo.getForks();
        String starCount = repo.getStarCount();
        String issueStr = repo.getIssues();

        id.setText(repo.getId());
        name.setText(repo.getName());
        forks.setText(repo.getForks());
        stars.setText(repo.getStarCount());
        issues.setText(repo.getIssues());
    }
}
