package com.example.kingpho.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kingpho.DetailProductActivity;
import com.example.kingpho.R;
import com.example.kingpho.adapter.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    private SearchView searchView;
    private GridView gridView;
    private FoodAdapter adapter;
    private List<Integer> allFoodImages;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_list, container, false);

        searchView = view.findViewById(R.id.searchView);
        gridView = view.findViewById(R.id.gridView);

        allFoodImages = new ArrayList<>();
        allFoodImages.add(R.drawable.phocuon);
        allFoodImages.add(R.drawable.phokho);
        allFoodImages.add(R.drawable.phonuoc);
        allFoodImages.add(R.drawable.phoran);
        allFoodImages.add(R.drawable.photron);
        allFoodImages.add(R.drawable.photron);
        allFoodImages.add(R.drawable.photron);

        adapter = new FoodAdapter(getContext(), allFoodImages);
        gridView.setAdapter(adapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedFoodImage = (Integer) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetailProductActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Bạn đã chọn sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false); // Mở SearchView
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.filter(newText);
                return true;
            }
        });
        return view;
    }
}
