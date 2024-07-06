package com.example.kingpho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> foodImages;
    private List<String> titles;
    private List<Integer> filteredFoodImages;
    private List<String> filteredTitles;

    public FoodAdapter(Context context, List<Integer> foodImages) {
        this.context = context;
        this.foodImages = foodImages;
        this.titles = new ArrayList<>();

        // Tạo danh sách titles mặc định
        for (int i = 0; i < foodImages.size(); i++) {
            titles.add("Item " + (i + 1));
        }

        this.filteredFoodImages = new ArrayList<>(foodImages);
        this.filteredTitles = new ArrayList<>(titles);
    }

    @Override
    public int getCount() {
        return filteredFoodImages.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredFoodImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewList);
        imageView.setImageResource(filteredFoodImages.get(position));

        TextView textView = convertView.findViewById(R.id.titleTxt);
        textView.setText(filteredTitles.get(position));

        return convertView;
    }

    public void filter(String query) {
        filteredFoodImages.clear();
        filteredTitles.clear();

        if (query.isEmpty()) {
            filteredFoodImages.addAll(foodImages);
            filteredTitles.addAll(titles);
        } else {
            query = query.toLowerCase();
            for (int i = 0; i < foodImages.size(); i++) {
                // Lọc dựa trên title
                if (titles.get(i).toLowerCase().contains(query)) {
                    filteredFoodImages.add(foodImages.get(i));
                    filteredTitles.add(titles.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}

