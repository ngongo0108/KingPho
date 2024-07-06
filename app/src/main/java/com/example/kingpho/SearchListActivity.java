package com.example.kingpho;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchListActivity extends AppCompatActivity {

    private SearchView searchView;
    private GridView gridView;
    private FoodAdapter adapter;
    private List<Integer> allFoodImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        searchView = findViewById(R.id.searchView);
        gridView = findViewById(R.id.gridView);

        allFoodImages = new ArrayList<>();
        allFoodImages.add(R.drawable.phocuon);
        allFoodImages.add(R.drawable.phokho);
        allFoodImages.add(R.drawable.phonuoc);
        allFoodImages.add(R.drawable.phoran);
        allFoodImages.add(R.drawable.photron);
        allFoodImages.add(R.drawable.photron);
        allFoodImages.add(R.drawable.photron);

        adapter = new FoodAdapter(SearchListActivity.this, allFoodImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedFoodImage = (Integer) parent.getItemAtPosition(position);
                Toast.makeText(SearchListActivity.this, "Bạn đã chọn sản phẩm", Toast.LENGTH_SHORT).show();
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
    }
}
