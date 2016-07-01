package fr.esgi.retrofit.tabs;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.R;
import fr.esgi.retrofit.activity.InputActivity;
import fr.esgi.retrofit.github.GithubWebService;
import fr.esgi.retrofit.myclass.User;

/**
 * Created by vincentk on 26/06/2016.
 */

public class TabRepo extends Fragment {

    User user;
    @BindView(R.id.repo_nbr)
    TextView repoNbr;

    public TabRepo(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_repo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        if(user.getPublicrepos().length() > 0) {
            repoNbr.setText(user.getPublicrepos());
        }
    }
}
