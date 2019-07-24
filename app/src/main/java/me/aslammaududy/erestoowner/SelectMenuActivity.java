package me.aslammaududy.erestoowner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.aslammaududy.erestoowner.Adapter.MenuAdapter;
import me.aslammaududy.erestoowner.Adapter.OrderAdapter;
import me.aslammaududy.erestoowner.Model.Menu;
import me.aslammaududy.erestoowner.Model.Order;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.NetworkManager.Endpoints;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectMenuActivity extends AppCompatActivity {
    private RecyclerView menuRecycler, orderRecycler;
    private MenuAdapter menuAdapter;
    private OrderAdapter orderAdapter;
    private List<Menu> menuList;
    private List<Order> orders;
    private TextView ppn, total;
    private int total1, cPPN;
    private Endpoints endpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        menuRecycler = findViewById(R.id.menu_recycler);
        orderRecycler = findViewById(R.id.order_recycler);
        ppn = findViewById(R.id.ppn);
        total = findViewById(R.id.total);

        setTitle("Meja " + getIntent().getStringExtra("table"));

        orders = new ArrayList<>();

        endpoints = Connection.getEndpoints(this);
        endpoints.getMenus().enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                menuList = new ArrayList<>(response.body());
                proccessMenuAdapter();
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });

        proccessOrderAdapter();

        menuRecycler.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false));

        orderRecycler.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void proccessMenuAdapter() {
        menuAdapter = new MenuAdapter(menuList);
        menuRecycler.setAdapter(menuAdapter);

        menuAdapter.setOnOrderedMenuListener((menuName, menuPrice, count) -> {
            Order order = new Order();
            order.setNama(menuName);
            order.setCount(count);
            order.setHarga(menuPrice);

            orders.add(order);

            orderAdapter.notifyItemInserted(orders.size() - 1);
        });
    }

    private void proccessOrderAdapter() {
        orderAdapter = new OrderAdapter(this, orders);
        orderRecycler.setAdapter(orderAdapter);
        orderAdapter.setOnOrderAddedListener((price, count) -> {
            int prc = Integer.parseInt(price);
            int ttl = prc * count;
            total1 += ttl;

            calculatePPN();
        });

        orderAdapter.setOnShowOrderItemDetailListener(new OrderAdapter.OnShowOrderItemDetailListener() {
            @Override
            public void onShowOrderItemDetail(int count, int discount, String note, int position) {
                orders.get(position).setCount(count);
                int price = Integer.parseInt(orders.get(position).getHarga());
                total1 -= price;

                if (note == null) {
                    orders.get(position).setNote("-");
                } else {
                    orders.get(position).setNote(note);
                }

                orderAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDeleteOrderItem(int count, int position) {
                int price = Integer.parseInt(orders.get(position).getHarga());

                if (count > 1) {
                    price *= count;
                }

                orders.remove(position);
                total1 -= price;

                calculatePPN();

                orderAdapter.notifyItemRemoved(position);
            }
        });
    }

    private void calculatePPN() {
        cPPN = 0;
        cPPN += (total1 * 10) / 100;

        //total2 adalah nilai total utama
        int total2 = total1 + cPPN;

        ppn.setText(String.valueOf(cPPN));
        total.setText(String.valueOf(total2));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void save(View view) {
        Order order = new Order();
        order.setMeja(getIntent().getStringExtra("table"));
        //setNama adalah nama menu
        order.setNama(orders.stream().map(Order::getNama).collect(Collectors.joining(",")));
        order.setNote(orders.stream().map(Order::getNote).collect(Collectors.joining(",")));

        endpoints.addOrder(order).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    if (object.get("status").equals("sukses")) {
                        new AlertDialog.Builder(SelectMenuActivity.this)
                                .setMessage("Berhasil simpan pesanan")
                                .setPositiveButton("tutup", null)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void delete(View view) {
        orders.clear();

        total1 = 0;
        cPPN = 0;

        ppn.setText(String.valueOf(cPPN));
        total.setText(String.valueOf(total1));

        orderAdapter.notifyDataSetChanged();
    }

    public void pay(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("table", getTitle());
        intent.putExtra("total", total.getText().toString());
        startActivity(intent);
    }
}
