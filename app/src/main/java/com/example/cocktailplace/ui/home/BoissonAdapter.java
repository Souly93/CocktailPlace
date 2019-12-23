package com.example.cocktailplace.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cocktailplace.R;

import java.util.List;
import android.support.v7.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoissonAdapter extends RecyclerView.Adapter<BoissonAdapter.MyViewHolder>{

    private List<Boisson> boissonList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView boisson;

        public MyViewHolder(View view) {
            super(view);
            boisson = (TextView) view.findViewById(R.id.boisson);
        }
    }

    public BoissonAdapter(List<Boisson> boissonList) {
        this.boissonList = boissonList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Boisson boisson = boissonList.get(position);
        holder.boisson.setText(boisson.getBoisson());
    }

    @Override
    public int getItemCount() {
        return boissonList.size();
    }
}
