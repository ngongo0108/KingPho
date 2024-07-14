

package com.example.kingpho.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.ChatActivity;
import com.example.kingpho.R;
import com.example.kingpho.adapter.CategoryAdapter;
import com.example.kingpho.adapter.ProductAdapter;
import com.example.kingpho.callback.CategoryCallback;
import com.example.kingpho.callback.ProductCallback;
import com.example.kingpho.callback.UserCallback;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.helper.TinyDB;
import com.example.kingpho.model.Category;
import com.example.kingpho.model.Food;
import com.example.kingpho.model.Product;
import com.example.kingpho.model.User;
import com.example.kingpho.service.CategoryService;
import com.example.kingpho.service.ProductService;
import com.example.kingpho.service.UserService;
import com.example.kingpho.utils.RetrofitClient;
import com.example.kingpho.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    private RecyclerView recyclerView;
    private ArrayList<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerViewProducts;
    private ArrayList<Food> productList;
    private ProductAdapter productAdapter;
    private TextView seeAll, tvUsername;
    private ImageView imageViewNoti, imageViewMessage;
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
        seeAll = view.findViewById(R.id.seeAll);
        tvUsername = view.findViewById(R.id.username);
        imageViewMessage = view.findViewById(R.id.imageViewMessage);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance(getContext());
        userService = retrofit.create(UserService.class);
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);

        String username = SharedPrefManager.getInstance(getContext()).getUser().getUsername();
        getUserByUsername(username, new UserCallback() {
            @Override
            public void onUserFetched(User user) {
                if (user != null) {
                    tvUsername.setText(user.getFullname());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ProductFragment(); // Replace with your target fragment
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
                ((MainActivity) requireActivity()).setActiveButton(((MainActivity) requireActivity()).productsBtn);
            }
        });
        imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

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
//        initializeProducts();

        categoryAdapter.notifyDataSetChanged();
        productAdapter.notifyDataSetChanged();

        if (!categoryList.isEmpty()) {
            updateProductList(categoryList.get(0));
        }
    }

    private void initializeCategories() {
//        categoryList.add(new Category("Phở Nước", "phonuoc"));
//        categoryList.add(new Category("Phở Khô", "phokho"));
//        categoryList.add(new Category("Phở Rán", "phoran"));
//        categoryList.add(new Category("Phở Cuốn", "phocuon"));
//        categoryList.add(new Category("Phở Trộn", "photron"));

        getAllCategories(new CategoryCallback() {
            @Override
            public void onListCategoryFetched(List<Category> categories) {
                for (Category category : categories) {
                    categoryList.add(new Category(category.getCategoryId(), category.getName(), category.getImage()));
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void initializeProducts() {
//        productList.add(new Food("Phở Nước", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
//        productList.add(new Food("Phở Nước2", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
//        productList.add(new Food("Phở Nước3", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
//        productList.add(new Food("Phở Nước4", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
//        productList.add(new Food("Phở Nước5", "phonuoc", "Món phở nước", 5.0, 1, categoryList.get(0)));
//
//        productList.add(new Food("Phở Khô", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô1", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô2", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô3", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô4", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô5", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô6", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//        productList.add(new Food("Phở Khô7", "phokho", "Món phở khô", 6.0, 1, categoryList.get(1)));
//
//        productList.add(new Food("Phở Rán", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//        productList.add(new Food("Phở Rán1", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//        productList.add(new Food("Phở Rán2", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//        productList.add(new Food("Phở Rán3", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//        productList.add(new Food("Phở Rán4", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//        productList.add(new Food("Phở Rán5", "phoran", "Món phở rán", 6.0, 1, categoryList.get(2)));
//
//        productList.add(new Food("Phở Cuốn1", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
//        productList.add(new Food("Phở Cuốn2", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
//        productList.add(new Food("Phở Cuốn3", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
//        productList.add(new Food("Phở Cuốn4", "phocuon", "Món phở cuốn", 6.0, 1, categoryList.get(3)));
//
//        productList.add(new Food("Phở Trộn1", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
//        productList.add(new Food("Phở Trộn2", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
//        productList.add(new Food("Phở Trộn3", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
//        productList.add(new Food("Phở Trộn4", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
//        productList.add(new Food("Phở Trộn5", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));
//        productList.add(new Food("Phở Trộn6", "photron", "Món phở trộn", 6.0, 1, categoryList.get(4)));

    }

    private void updateProductList(Category category) {
        ArrayList<Food> filteredProducts = new ArrayList<>();
        for (Food food : productList) {
            if (food.getCategory().getName().equals(category.getName())) {
                filteredProducts.add(food);
            }
        }
        productAdapter.updateProducts(filteredProducts);
    }

    public void getUserByUsername(String username, UserCallback callback) {
        try {
            Call<User> call = userService.getUserByUsername(username);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User user = response.body();

                        callback.onUserFetched(user);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllCategories(CategoryCallback callback) {
        try {
            Call<List<Category>> call = categoryService.getAllCategories();
            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Category> categories = response.body();

                        callback.onListCategoryFetched(categories);
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllProducts(ProductCallback callback) {
        try {
            Call<List<Product>> call = productService.getAllProducts();
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Product> products = response.body();

                        callback.onListProductFetched(products);
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable throwable) {
                    Toast.makeText(getContext(), "Fail to get product", Toast.LENGTH_LONG).show();
                    throwable.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}




