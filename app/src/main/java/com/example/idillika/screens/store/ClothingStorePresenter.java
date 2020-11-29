package com.example.idillika.screens.store;

import com.example.idillika.data.DataManager;
import com.example.idillika.data.RequestListener;
import com.example.idillika.data.Store;

import java.util.ArrayList;

public class ClothingStorePresenter {

    private DataManager dataManager;
    private ClothingStoreView clothingStoreView;

    public ClothingStorePresenter(ClothingStoreView view) {
        clothingStoreView = view;
        initRequest();
    }

    private void initRequest() {
        dataManager = new DataManager();

        dataManager.getStoreItems(new RequestListener() {
            @Override
            public void OnSuccess(ArrayList<Store> store) {
                clothingStoreView.hideProgress();
                clothingStoreView.showData(store);
            }

            @Override
            public void OnError(String error) {
                clothingStoreView.hideProgress();
                clothingStoreView.showError(error);
            }
        });
    }
}
