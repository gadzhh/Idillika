package com.example.idillika.screens.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idillika.R;
import com.example.idillika.data.SharedPreferenceHelper;
import com.example.idillika.data.Store;
import com.example.idillika.screens.basket.BasketFragment;
import com.example.idillika.screens.home.HomeFragment;

import java.util.ArrayList;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class ClothingStoreFragment extends Fragment implements ClothingStoreView, OnButtonListener {

    private ProgressBar progressBar;
    private StoreItemsAdapter storeItemsAdapter;
    private ClothingStorePresenter clothingStorePresenter;
    private SharedPreferenceHelper preferenceHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clothing_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        initViews(view);
        initPresenter();
        initPrefs();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_basket:
                return true;
        }

        return false;
    }

    private void initPresenter() {
        clothingStorePresenter = new ClothingStorePresenter(this);
    }

    private void initPrefs() {

        if (getActivity().getBaseContext() != null) {
            preferenceHelper = new SharedPreferenceHelper(getActivity().getBaseContext());
        }
    }

    private void initViews(View view) {

        Toolbar toolbar;
        RecyclerView clothingList;

        toolbar = view.findViewById(R.id.store_toolbar);
        if (toolbar.getMenu().size() < 1) {
            toolbar.inflateMenu(R.menu.menu_items);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.btn_basket) {
                        Toast.makeText(getContext(), "HHH", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });
        }

        toolbar.setNavigationOnClickListener(view1 -> {
            HomeFragment homeFragment = new HomeFragment();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_for_fragments, homeFragment).addToBackStack(null);
            fragmentTransaction.commit();
        });

        toolbar.setOnMenuItemClickListener((Toolbar.OnMenuItemClickListener) view12 -> {
            BasketFragment basketFragment = new BasketFragment();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_for_fragments, basketFragment).addToBackStack(null);
            fragmentTransaction.commit();

            return false;
        });


        clothingList = view.findViewById(R.id.rv_store);
        clothingList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        clothingList.setHasFixedSize(true);

        storeItemsAdapter = new StoreItemsAdapter(this);
        clothingList.setAdapter(storeItemsAdapter);

        progressBar = view.findViewById(R.id.progress);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, LENGTH_SHORT).show();
    }

    @Override
    public void showData(ArrayList<Store> store) {

        for (int i = 0; i < store.size(); i++) {
            store.get(i).setFavorite(preferenceHelper.idInBasket(store.get(i).getId()));
        }
        storeItemsAdapter.setData(store);
    }

    @Override
    public void onButtonClick(int productId, boolean isDelete) {
        if (isDelete) {
            preferenceHelper.removeFromCart(productId);
        } else {
            preferenceHelper.addToCart(productId);
        }
    }
}
