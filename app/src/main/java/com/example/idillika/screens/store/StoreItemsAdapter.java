package com.example.idillika.screens.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idillika.R;
import com.example.idillika.data.Store;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoreItemsAdapter extends RecyclerView.Adapter<StoreItemsAdapter.StoreItemsHolder> {

    public OnButtonListener onButtonListener;
    private ArrayList<Store> items = new ArrayList<>();

    public StoreItemsAdapter(OnButtonListener listener) {
        onButtonListener = listener;
    }

    public void setData(ArrayList<Store> data) {
        items.clear();
        items.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothing_store_item, parent, false);
        return new StoreItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class StoreItemsHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewClothes;
        private TextView nameOfClothes, descriptionOfClothes, priceOfClothes;
        private Button btnHeart;

        public StoreItemsHolder(@NonNull View itemView) {
            super(itemView);

            imageViewClothes = itemView.findViewById(R.id.iv_clothes);
            nameOfClothes = itemView.findViewById(R.id.tv_name_of_clothes);
            descriptionOfClothes = itemView.findViewById(R.id.tv_description_of_clothes);
            priceOfClothes = itemView.findViewById(R.id.tv_price_of_clothes);
            btnHeart = itemView.findViewById(R.id.btn_heart);
        }

        void bind(Store item) {

            Picasso
                    .get()
                    .load(item.getImageLink())
                    .into(imageViewClothes);

            btnHeart.setSelected(item.getFavorite());
            nameOfClothes.setText(item.getBrand());
            descriptionOfClothes.setText(item.getDescription());
            priceOfClothes.setText(String.valueOf(item.getPrice()));

            btnHeart.setOnClickListener(view -> {
                onButtonListener.onButtonClick(item.getId(), item.getFavorite());
                item.setFavorite(!item.getFavorite());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
