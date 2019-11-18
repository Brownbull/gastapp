package com.utilone.gastapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utilone.gastapp.R;
import com.utilone.gastapp.model.Transact;

import java.util.List;

/**
 * Created by lalit on 9/12/2016 -> Modified by Brownbull 11/09/2019
 */

public class TransactRecyclerAdapter extends RecyclerView.Adapter<TransactRecyclerAdapter.TransactViewHolder> {

    private List<Transact> listTransact;

    public TransactRecyclerAdapter(List<Transact> listTransact) {
        this.listTransact = listTransact;
    }

    @Override
    public TransactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transact_recycler, parent, false);

        return new TransactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactViewHolder holder, int position) {
        holder.textViewDay.setText(listTransact.get(position).getTransactDayStr());
        holder.textViewAmnt.setText(listTransact.get(position).getAmountStr());
        holder.textViewCategory.setText(listTransact.get(position).getCategory());
        holder.textViewDesc.setText(listTransact.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        Log.v(TransactRecyclerAdapter.class.getSimpleName(),""+listTransact.size());
        return listTransact.size();
    }

    /**
     * ViewHolder class
     */
    public class TransactViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewDay;
        public AppCompatTextView textViewAmnt;
        public AppCompatTextView textViewCategory;
        public AppCompatTextView textViewDesc;

        public TransactViewHolder(View view) {
            super(view);
            textViewDay = (AppCompatTextView) view.findViewById(R.id.textViewDay);
            textViewAmnt = (AppCompatTextView) view.findViewById(R.id.textViewAmnt);
            textViewCategory = (AppCompatTextView) view.findViewById(R.id.textViewCategory);
            textViewDesc = (AppCompatTextView) view.findViewById(R.id.textViewDesc);
        }
    }

}
