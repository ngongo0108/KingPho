package com.example.kingpho.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kingpho.R;
import com.example.kingpho.adapter.FavouriteAdapter;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.helper.TinyDB;
import com.example.kingpho.model.Food;

import java.util.ArrayList;
public class FavouriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;
    private Manager manager;
    private ImageView imgEmpty;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        imgEmpty = view.findViewById(R.id.imgEmpty);
        manager = new Manager(getContext(), new TinyDB(getContext()));
        ArrayList<Food> favouritesList = manager.getFavouritesList();

        recyclerView = view.findViewById(R.id.recyclerViewFav);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2 ));
        favouriteAdapter = new FavouriteAdapter(favouritesList, manager);
        recyclerView.setAdapter(favouriteAdapter);

        emptyUI();

        return  view;
    }

    private void emptyUI() {
        if (manager.getListCart().isEmpty()) {
            imgEmpty.setVisibility(View.VISIBLE);
        } else {
            imgEmpty.setVisibility(View.GONE);
        }
    }
}