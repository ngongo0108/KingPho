package com.example.kingpho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.model.ImageProduct;

import java.util.List;

public class ImageProductAdapter extends RecyclerView.Adapter<ImageProductAdapter.ImageHolder> {
    private final List<ImageProduct> imgList;

    public ImageProductAdapter(List<ImageProduct> imgList) {
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_product, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        ImageProduct imgProduct = imgList.get(position);
        if (imgProduct == null) {
            return;
        }
        holder.image.setImageResource(imgProduct.getResouceId());
    }

    @Override
    public int getItemCount() {
        if (imgList != null) {
            return imgList.size();
        }
        return 0;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        // Khai báo các view trong item layout
        private final ImageView image;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            image = itemView.findViewById(R.id.image);
        }
    }
}
