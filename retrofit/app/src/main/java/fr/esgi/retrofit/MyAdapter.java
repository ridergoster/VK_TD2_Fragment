package fr.esgi.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentk on 14/06/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Repo> repoList;

    public MyAdapter() {
        repoList = new ArrayList<>();
    }

    public void addObjects(List<Repo> repos){
        repoList.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
        return new RepoViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RepoViewHolder) {
            Repo repo = repoList.get(position);
            ((RepoViewHolder)holder).bind(repo);
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}