package com.example.android.baking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.baking.Model.BakingFood;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BakingFoodTagAdapter extends RecyclerView.Adapter<BakingFoodTagAdapter.BakingFoodTagAdapterViewHolder>{

    private List<BakingFood> bakingFood;
    private Context context;

    private final BakingFoodTagAdapterOnClickHandler mClickHandler;

    public interface BakingFoodTagAdapterOnClickHandler {
        void onClick(BakingFood foodTag);
    }

    public BakingFoodTagAdapter(List<BakingFood> bFood, Context context, BakingFoodTagAdapterOnClickHandler clickHandler){
        this.context = context;
        this.bakingFood = bFood;
        this.mClickHandler = clickHandler;
    }

    public void setAdapter(List<BakingFood> data) {
        this.bakingFood = data;
        //notifyDataSetChanged();
    }

    /*public void clear() {
        int size = bakingFood.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                bakingFood.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }*/

    public class BakingFoodTagAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mFoodTag;
        public final ImageView mFoodTagImage;
        public final ImageView mInsertImage;


        public BakingFoodTagAdapterViewHolder(View view){
            super(view);

            mFoodTag = (TextView) view.findViewById(R.id.tv_foodTagName);
            mFoodTagImage = (ImageView) view.findViewById(R.id.tv_foodTagImage);
            mInsertImage = (ImageView) view.findViewById(R.id.tv_insertImage);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            BakingFood foodTag = bakingFood.get(adapterPosition);
            mClickHandler.onClick(foodTag);
        }

    }

    @NonNull
    @Override
    public BakingFoodTagAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.baking_food_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new BakingFoodTagAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakingFoodTagAdapterViewHolder holder, int position) {

        try{
            Picasso.with(context)
                    .load(bakingFood.get(position).getImage())
                    .into(holder.mFoodTagImage);
        }catch (Exception e){
            e.printStackTrace();
            holder.mFoodTagImage.setVisibility(View.INVISIBLE);
            holder.mInsertImage.setVisibility(View.VISIBLE);

        }

        holder.mFoodTag.setText(bakingFood.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return bakingFood.size();
    }

}
