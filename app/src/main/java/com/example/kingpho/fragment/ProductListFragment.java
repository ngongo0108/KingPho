//package com.example.kingpho.fragment;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.kingpho.R;
//import com.example.kingpho.adapter.ProductAdapter;
//import com.example.kingpho.model.Category;
//import com.example.kingpho.model.Food;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProductListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class ProductListFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ProductListFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProductListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProductListFragment newInstance(String param1, String param2) {
//        ProductListFragment fragment = new ProductListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//    private RecyclerView recyclerViewProductList;
//    private ProductAdapter productAdapter;
//    private ArrayList<Category> categoryList;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
//
//        recyclerViewProductList(view);
//        return view;
//    }
//
//    private void recyclerViewProductList(View view) {
//        recyclerViewProductList = view.findViewById(R.id.recyclerViewProducts);
//        recyclerViewProductList.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
//
//        ArrayList<Food> foodList = new ArrayList<>();
//        foodList.add(new Food("Phở Nước", "icon_pho", "Món phở nước", 5.0, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//        foodList.add(new Food("Phở Nước", "phonuoc", "abcxza", 35.00, categoryList.get(0)));
//
//        productAdapter = new ProductAdapter(foodList);
//        recyclerViewProductList.setAdapter(productAdapter);
//    }
//}