package com.axiagroups.productlist.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.axiagroups.productlist.R;
import com.axiagroups.productlist.model.Product;

// https://developer.android.com/codelabs/android-room-with-a-view#11
public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {
    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current.getName(), String.valueOf(current.getPrice()));

    }

    public static class ProductDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}

class ProductViewHolder extends RecyclerView.ViewHolder {
    private final TextView productNameTv, productPriceTv;
    ImageView editIcon, deleteIcon;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productNameTv = itemView.findViewById(R.id.row_productNameTV);
        productPriceTv = itemView.findViewById(R.id.row_productPriceTV);
        editIcon = itemView.findViewById(R.id.editIcon);
        deleteIcon = itemView.findViewById(R.id.deleteIcon);
    }

    public void bind(String name, String price) {
        productNameTv.setText(name);
        productPriceTv.setText(price);

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), getAdapterPosition() + " Edit Item is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delt Item is clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }

    static ProductViewHolder create(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view);
    }
}
