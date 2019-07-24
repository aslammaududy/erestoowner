package me.aslammaududy.erestoowner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

import java.util.List;

import me.aslammaududy.erestoowner.Model.Menu;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.NetworkManager.Endpoints;
import me.aslammaududy.erestoowner.R;
import me.aslammaududy.erestoowner.SelectMenuActivity;

public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.InnerViewHolder> {
    private List<String> tableNumbers;
    private List<Integer> layoutPositions;
    private Context context;

    public InnerRecyclerAdapter(List<String> tableNumbers, List<Integer> layoutPositions, Context context) {
        this.tableNumbers = tableNumbers;
        this.context = context;
        this.layoutPositions = layoutPositions;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inner_recycler_item, parent, false);
        return new InnerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.tableNumber.setText(tableNumbers.get(position));
    }

    @Override
    public int getItemCount() {
        return tableNumbers.size();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private BootstrapButton tableNumber;
        private View view;
        private Endpoints endpoints;
        private List<Menu> menus;
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);

            tableNumber = itemView.findViewById(R.id.table_button);

            sharedPreferences = context.getSharedPreferences("erestoowner", 0);
            editor = sharedPreferences.edit();
            editor.apply();

            endpoints = Connection.getEndpoints(context);

            for (int i = 0; i < layoutPositions.size(); i++) {
                // supaya cuma meja pada layoutnya saja yang bisa digabung dan dipisah
                if (OuterRecyclerAdapter.outerAdapterPosition == layoutPositions.get(i)) {
                    tableNumber.setOnClickListener(listener -> {
                        tableNumber.setShowOutline(false);
                        int position = OuterRecyclerAdapter.outerAdapterPosition;
                        String merge = sharedPreferences.getString("merge" + position, "");
                        String unmerged = sharedPreferences.getString("unmerged" + position, "");

                        if (merge.equals("merge" + position)) {
                            Log.i("merge", merge);
                            tableNumber.setBootstrapBrand(DefaultBootstrapBrand.fromAttributeValue(0));
                        } else if (unmerged.equals("unmerged" + position)) {
                            tableNumber.setBootstrapBrand(DefaultBootstrapBrand.fromAttributeValue(1));
                            tableNumber.setShowOutline(true);
                        } else {
                            Intent intent = new Intent(context, SelectMenuActivity.class);
                            intent.putExtra("table", tableNumber.getText().toString());
                            context.startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}
