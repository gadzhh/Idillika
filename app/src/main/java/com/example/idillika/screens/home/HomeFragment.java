package com.example.idillika.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.idillika.R;
import com.example.idillika.screens.store.ClothingStoreFragment;


public class HomeFragment extends Fragment {

    private Button showProductsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    private void initViews(View view) {
        showProductsButton = view.findViewById(R.id.btn_show_products);
        showProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClothingStoreFragment clothingStoreFragment = new ClothingStoreFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_for_fragments, clothingStoreFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
