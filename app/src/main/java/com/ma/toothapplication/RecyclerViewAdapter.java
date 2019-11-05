package com.ma.toothapplication;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {

    private ArrayList<String> mDataset;
    RecyclerView recycler;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        View v;

        public MyViewHolder(View view) {
            super(view);
            this.text = view.findViewById(R.id.text_view);
            this.v=view;
        }
        View getView(){
            return v;
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_afficher_horaire, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        recycler = recyclerView;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String a=mDataset.get(position);
        if (position%2 == 0){
        //if (a.equals("Lundi") || a.equals("Mardi") || a.equals("Mercredi") || a.equals("Jeudi") || a.equals("Vendredi") || a.equals("Samedi") || a.equals("Dimanche")  ){
            holder.text.setTypeface(holder.text.getTypeface(), Typeface.BOLD); //the first parameter may be null, but in this case the previous type faced will be erased
        }
        holder.text.setText(a);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
