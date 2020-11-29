package com.example.idillika.screens.store;

import com.example.idillika.data.Store;

import java.util.ArrayList;

public interface ClothingStoreView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showData(ArrayList<Store> store);
}
