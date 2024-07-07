package com.example.kingpho.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.kingpho.DetailProductActivity;
import com.example.kingpho.R;
import com.example.kingpho.adapter.FoodAdapter;
import com.example.kingpho.model.FoodActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductFragment extends Fragment {

    private SearchView searchView;
    private GridView gridView;
    private FoodAdapter adapter;
    private List<FoodActivity> allFoodList;
    private List<FoodActivity> filteredFoodList;
    private ImageButton filterButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_list, container, false);

        searchView = view.findViewById(R.id.searchView);
        gridView = view.findViewById(R.id.gridView);
        filterButton = view.findViewById(R.id.filterButton);

        // Khởi tạo danh sách mẫu
        allFoodList = new ArrayList<>();
        allFoodList.add(new FoodActivity("Phở trộn2", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn3", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn4", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn5", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn6", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở cuốn", 50.0, "phocuon"));
        allFoodList.add(new FoodActivity("Phở khô", 45.0, "phokho"));
        allFoodList.add(new FoodActivity("Phở nước", 40.0, "phonuoc"));
        allFoodList.add(new FoodActivity("Phở rán", 55.0, "phoran"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn2", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn3", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn4", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn5", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn6", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn7", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));
        allFoodList.add(new FoodActivity("Phở trộn", 60.0, "photron"));

        // Khởi tạo danh sách filteredFoodList ban đầu
        filteredFoodList = new ArrayList<>(allFoodList);

        // Khởi tạo adapter
        adapter = new FoodAdapter(getContext(), filteredFoodList);
        gridView.setAdapter(adapter);

        // Xử lý khi nhấn vào nút filterButton
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterPopupMenu();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodActivity selectedFood = (FoodActivity) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetailProductActivity.class);
                // Truyền thông tin extra nếu cần thiết
                intent.putExtra("foodId", selectedFood.getFoodId());
                startActivity(intent);
                Toast.makeText(getContext(), "Bạn đã chọn sản phẩm: " + selectedFood.getFoodTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
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

    private void showFilterPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), filterButton);
        popupMenu.getMenuInflater().inflate(R.menu.filter_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.filter_by_name) {
                    filterData("Tên A-Z");
                } else if (itemId == R.id.filter_by_price) {
                    filterData("Giá tiền");
                }

                return true;
            }
        });

        popupMenu.show();
    }

    private void filterData(String filterOption) {
        List<FoodActivity> sortedList = new ArrayList<>(allFoodList); // Tạo một bản sao của danh sách gốc

        switch (filterOption) {
            case "Tên A-Z":
                // Sắp xếp danh sách theo tên từ A-Z
                Collections.sort(sortedList, new Comparator<FoodActivity>() {
                    @Override
                    public int compare(FoodActivity food1, FoodActivity food2) {
                        return food1.getFoodTitle().compareTo(food2.getFoodTitle());
                    }
                });
                break;
            case "Giá tiền":
                // Sắp xếp danh sách theo giá tiền
                Collections.sort(sortedList, new Comparator<FoodActivity>() {
                    @Override
                    public int compare(FoodActivity food1, FoodActivity food2) {
                        return Double.compare(food1.getFoodPrice(), food2.getFoodPrice());
                    }
                });
                break;
            default:
                break;
        }

        // Cập nhật lại adapter sau khi sắp xếp
        adapter.updateData(sortedList);
    }
}
