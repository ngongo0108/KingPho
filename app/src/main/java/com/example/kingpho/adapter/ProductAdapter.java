//
//
//package com.example.kingpho.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.kingpho.R;
//import com.example.kingpho.model.Food;
//
//import java.util.ArrayList;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
//
//    private ArrayList<Food> productList;
//    private ArrayList<Food> cartList = new ArrayList<>();
//
//    public ProductAdapter(ArrayList<Food> productList) {
//        this.productList = productList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_list, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Food product = productList.get(position);
//
//        holder.productName.setText(product.getFoodTitle());
//        holder.productPrice.setText(String.valueOf(product.getFoodPrice()));
//
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(product.getFoodImage(), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.productImage);
//
//        // Sự kiện cho nút Add to Cart
//        holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToCart(product, v.getContext());
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView productName, productDescription, productPrice;
//        ImageView productImage, addCartBtn;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productName = itemView.findViewById(R.id.title);
//            productPrice = itemView.findViewById(R.id.price);
//            productImage = itemView.findViewById(R.id.pic);
//            addCartBtn = itemView.findViewById(R.id.addtocartBtn);
//        }
//    }
//
//    public void updateProducts(ArrayList<Food> products) {
//        this.productList = products;
//        notifyDataSetChanged();
//    }
//
//    // Hàm thêm sản phẩm vào giỏ hàng
//    private void addToCart(Food product, Context context) {
//        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
//        boolean isInCart = false;
//        for (Food item : cartList) {
//            if (item.getFoodTitle().equals(product.getFoodTitle())) {
//                // Tăng số lượng sản phẩm nếu đã có trong giỏ hàng
//                item.setNumberInCart(item.getNumberInCart() + 1);
//                isInCart = true;
//                break;
//            }
//        }
//        if (!isInCart) {
//            // Thêm sản phẩm mới vào giỏ hàng
//            product.setNumberInCart(1);
//            cartAdapter.updateCartItems(cartList);
//        }
//        // Thông báo cập nhật giỏ hàng (có thể cập nhật UI hoặc lưu trữ dữ liệu giỏ hàng)
//        Toast.makeText(context, product.getFoodTitle() + " added to cart", Toast.LENGTH_SHORT).show();
//    }
//
//    public ArrayList<Food> getCartList() {
//        return cartList;
//    }
//}
package com.example.kingpho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
            productName = itemView.findViewById(R.id.title);
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
        }

        private void updateFavouriteButton(Food product) {
            if (manager.isFavourite(product)) {
                favouriteButton.setImageResource(R.drawable.heart_ic_change);
            } else {
                favouriteButton.setImageResource(R.drawable.btn3); // Update with your border favourite icon
            }
        }
    }
}




//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//
//    private ArrayList<Food> products;
//    private OnProductClickListener listener;
//
//    public ProductAdapter(ArrayList<Food> products, OnProductClickListener listener) {
//        this.products = products;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product, parent, false);
//        return new ProductViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
//        Food product = products.get(position);
//
//        holder.productName.setText(product.getFoodTitle());
//        holder.productPrice.setText(String.valueOf(product.getFoodPrice()));
//
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(product.getFoodImage(), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.productImage);
//
//        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Gọi sự kiện để thêm sản phẩm vào giỏ hàng
//                listener.onProductClicked(product);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return products.size();
//    }
//
//    public void updateProducts(ArrayList<Food> products) {
//        this.products = products;
//        notifyDataSetChanged();
//    }
//
//    // Interface để gọi lại từ CartFragment khi thêm sản phẩm vào giỏ hàng
//    public interface OnProductClickListener {
//        void onProductClicked(Food product);
//    }
//
//    static class ProductViewHolder extends RecyclerView.ViewHolder {
//        ImageView productImage;
//        TextView productName;
//        TextView productPrice;
//        ImageView addToCartButton;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productImage = itemView.findViewById(R.id.pic);
//            productName = itemView.findViewById(R.id.title);
//            productPrice = itemView.findViewById(R.id.price);
//            addToCartButton = itemView.findViewById(R.id.addtocartBtn);
//        }
//    }
//}



