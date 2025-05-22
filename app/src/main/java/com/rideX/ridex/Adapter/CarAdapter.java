package com.rideX.ridex.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rideX.ridex.Activity.DetailActivity;
import com.rideX.ridex.Model.CarModel;
import com.rideX.ridex.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final Context context;
    private List<CarModel> carList;

    public CarAdapter(Context context, List<CarModel> carList) {
        this.context = context;
        this.carList = carList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<CarModel> newList) {
        carList = newList;
        notifyDataSetChanged();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CarModel> newList) {
        carList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cars, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarModel car = carList.get(position);
        holder.title.setText(car.getTitle());
        holder.price.setText("BDT" + car.getPrice());

        String imageUrl = "http://your-api-url.com/images/" + car.getPicName(); // Replace base URL
        Glide.with(context).load(imageUrl).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", car.getTitle());
            intent.putExtra("picName", car.getPicName());
            intent.putExtra("ContactBuyer", car.getContactBuyer());
            intent.putExtra("MilageRan", car.getMilageRan());
            intent.putExtra("price", car.getPrice());
            intent.putExtra("rating", car.getRating());
            intent.putExtra("TotalCapacity", car.getTotalCapacity());
            intent.putExtra("description", car.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.carTitle);
            price = itemView.findViewById(R.id.carPrice);
            image = itemView.findViewById(R.id.carImage);
        }
    }
}
