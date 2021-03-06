package fr.esgi.retrofit.tabs;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.R;
import fr.esgi.retrofit.myclass.User;

/**
 * Created by vincentk on 26/06/2016.
 */

public class TabFollowed extends Fragment {


    User user;
    @BindView(R.id.followed_nbr)
    TextView followedNbr;

    public TabFollowed(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_followed, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        if(user.getFollowing().length() > 0) {
            followedNbr.setText(user.getFollowing());
        }
    }
}
