
package com.example.kingpho.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kingpho.DetailProductActivity;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.R;
import com.example.kingpho.model.Food;

import java.util.ArrayList;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Food> productList;
    private Manager manager;

    public ProductAdapter(ArrayList<Food> productList, Manager manager) {
        this.productList = productList;
        this.manager = manager;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Food product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateProducts(ArrayList<Food> updatedProducts) {
        this.productList = updatedProducts;
        notifyDataSetChanged();

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private ImageView addToCartButton;
        private ImageView favouriteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.pic);
            productName = itemView.findViewById(R.id.titleMethod);
            productPrice = itemView.findViewById(R.id.price);
            addToCartButton = itemView.findViewById(R.id.addtocartBtn);
            favouriteButton = itemView.findViewById(R.id.favouriteBtnAdd);
        }

        public void bind(Food product) {
            productName.setText(product.getFoodTitle());
            productPrice.setText(String.valueOf(product.getFoodPrice()));

            int drawableResourceId = itemView.getContext().getResources().getIdentifier(product.getFoodImage(), "drawable", itemView.getContext().getPackageName());
            Glide.with(itemView.getContext())
                    .load(drawableResourceId)
                    .into(productImage);
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.addToCart(product);
                }
            });

            updateFavouriteButton(product);

            favouriteButton.setOnClickListener(v -> {
                if (manager.isFavourite(product)) {
                    manager.removeFromFavourites(product);
                } else {
                    manager.addToFavourites(product);
                }
                updateFavouriteButton(product);
            });

            productImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetailProductActivity.class);
//                    intent.putExtra("foodId", product.ge());
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "Bạn đã chọn sản phẩm " + product.getFoodTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        private void updateFavouriteButton(Food product) {
            if (manager.isFavourite(product)) {
                favouriteButton.setImageResource(R.drawable.heart_ic_change);
            } else {
                favouriteButton.setImageResource(R.drawable.btn3);
            }
        }
    }
}








