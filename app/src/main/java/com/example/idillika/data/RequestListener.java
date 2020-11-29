package com.example.idillika.data;

import java.util.ArrayList;

public interface RequestListener {

    void OnSuccess(ArrayList<Store> stores);

    void OnError(String error);
}
