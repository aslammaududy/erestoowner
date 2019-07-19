package me.aslammaududy.erestoowner;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Adapter.OuterRecyclerAdapter;
import me.aslammaududy.erestoowner.Helper.ProgressBar;
import me.aslammaududy.erestoowner.Model.Layout;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.NetworkManager.Endpoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTableActivity extends AppCompatActivity {
    private RecyclerView outerRecycler;
    private OuterRecyclerAdapter adapter;
    private List<Layout> layoutNames;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        outerRecycler = findViewById(R.id.outer_recycler);

        handler = new Handler();
        ProgressBar.show(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        outerRecycler.setLayoutManager(layoutManager);
        outerRecycler.setHasFixedSize(true);

        Endpoints endpoints = Connection.getEndpoints();
        endpoints.getLayouts().enqueue(new Callback<List<Layout>>() {
            @Override
            public void onResponse(Call<List<Layout>> call, Response<List<Layout>> response) {
                layoutNames = new ArrayList<>(response.body());
                adapter = new OuterRecyclerAdapter(layoutNames, SelectTableActivity.this);
                outerRecycler.setAdapter(adapter);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProgressBar.dissmiss();
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Call<List<Layout>> call, Throwable t) {

            }
        });


    }
}
