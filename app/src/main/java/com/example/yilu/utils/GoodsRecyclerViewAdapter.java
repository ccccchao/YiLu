package com.example.yilu.utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.yilu.R;
import com.example.yilu.entities.Good;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class GoodsRecyclerViewAdapter extends RecyclerView.Adapter<GoodsRecyclerViewAdapter.ViewHolder> {
    private final List<Good> goodList;

    public GoodsRecyclerViewAdapter(List<Good> goods){
        this.goodList=goods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_layout, parent, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position, List payloads) {
        onBindViewHolder(holder, position);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Good good=goodList.get(position);
        holder.goodCheckBox.setChecked(good.getIs_checked());
        holder.goodNameTextView.setText(good.getName());
        holder.goodCategoryTextView.setText(good.getCategory());
        holder.goodPriceTextView.setText(""+good.getPrice());
        holder.goodAmountTextView.setText(""+good.getAmount());
        holder.goodImageView.setImageResource(good.getImage());

        holder.goodCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_checked = good.getIs_checked();
                good.setIs_checked(!is_checked);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialCheckBox shopCheckBox, goodCheckBox;
        ImageView platformImageView, goodImageView;
        TextView shopNameTextView, goodNameTextView, goodCategoryTextView, goodPriceTextView, goodAmountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            shopCheckBox=itemView.findViewById(R.id.shop_check_box);
            goodCheckBox=itemView.findViewById(R.id.good_check_box);
//            platformImageView=itemView.findViewById(R.id.platform_image);
            goodImageView=itemView.findViewById(R.id.good_image);
//            shopNameTextView=itemView.findViewById(R.id.shop_name);
            goodNameTextView=itemView.findViewById(R.id.good_name);
            goodCategoryTextView=itemView.findViewById(R.id.good_category);
            goodPriceTextView=itemView.findViewById(R.id.good_price);
            goodAmountTextView=itemView.findViewById(R.id.good_amount);
        }
    }
}

