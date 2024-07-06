

package com.example.kingpho.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.adapter.CategoryAdapter;
import com.example.kingpho.adapter.ProductAdapter;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.helper.TinyDB;
import com.example.kingpho.model.Category;
import com.example.kingpho.model.Food;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Category> categoryList;
    private CategoryAdapter categoryAdapter;

    private RecyclerView recyclerViewProducts;
    private ArrayList<Food> productList;
    private ProductAdapter productAdapter;

    private Manager manager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TinyDB tinyDB = new TinyDB(requireContext());
        manager = new Manager(requireContext(), tinyDB);
        initializeRecyclerViews(view);
        initializeData();
        return view;
    }

    private void initializeRecyclerViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryList, this::updateProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(categoryAdapter);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewHomeProducts);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, manager);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerViewProducts.setAdapter(productAdapter);
    }

    private void initializeData() {
        initializeCategories();
        initializeProducts();

        categoryAdapter.notifyDataSetChanged();
        productAdapter.notifyDataSetChanged();

        if (!categoryList.isEmpty()) {
            updateProductList(categoryList.get(0));
        }
    }

    private void initializeCategories() {
        categoryList.add(new Category("Phở Nước", "phonuoc"));
        categoryList.add(new Category("Phở Khô", "phokho"));
        categoryList.add(new Category("Phở Rán", "phoran"));
        categoryList.add(new Category("Phở Cuốn", "phocuon"));
        categoryList.add(new Category("Phở Trộn", "photron"));
    }

    private void initializeProducts() {
        productList.add(new Food("Phở Nước", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
        productList.add(new Food("Phở Nước2", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
        productList.add(new Food("Phở Nước3", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
        productList.add(new Food("Phở Nước4", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
        productList.add(new Food("Phở Nước5", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));

        productList.add(new Food("Phở Khô", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô1", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô2", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô3", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô4", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô5", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô6", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
        productList.add(new Food("Phở Khô7", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));

        productList.add(new Food("Phở Rán", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
        productList.add(new Food("Phở Rán1", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
        productList.add(new Food("Phở Rán2", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
        productList.add(new Food("Phở Rán3", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
        productList.add(new Food("Phở Rán4", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
        productList.add(new Food("Phở Rán5", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));

        productList.add(new Food("Phở Cuốn1", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
        productList.add(new Food("Phở Cuốn2", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
        productList.add(new Food("Phở Cuốn3", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
        productList.add(new Food("Phở Cuốn4", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));

        productList.add(new Food("Phở Trộn1", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
        productList.add(new Food("Phở Trộn2", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
        productList.add(new Food("Phở Trộn3", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
        productList.add(new Food("Phở Trộn4", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
        productList.add(new Food("Phở Trộn5", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
        productList.add(new Food("Phở Trộn6", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
    }

    private void updateProductList(Category category) {
        ArrayList<Food> filteredProducts = new ArrayList<>();
        for (Food food : productList) {
            if (food.getCategory().getCategoryTitle().equals(category.getCategoryTitle())) {
                filteredProducts.add(food);
            }
        }
        productAdapter.updateProducts(filteredProducts);
    }
}




