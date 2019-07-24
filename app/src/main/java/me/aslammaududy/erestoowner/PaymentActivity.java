package me.aslammaududy.erestoowner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.List;

import me.aslammaududy.erestoowner.Model.Order;
import me.aslammaududy.erestoowner.Model.Payment;

public class PaymentActivity extends AppCompatActivity {
    private BootstrapEditText pay;
    private TextView totalPayment, totalChange;
    private List<Order> orders;
    private Payment payment;
    private Intent intent;
    private String idOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalPayment = findViewById(R.id.total_payment);
        totalChange = findViewById(R.id.total_change);
        pay = findViewById(R.id.payment);
        pay.requestFocus();

        payment = new Payment();
        intent = getIntent();

        totalPayment.setText(intent.getStringExtra("total"));

//        idOrder=intent.getStringExtra("")

//        Endpoints endpoints = Connection.getEndpoints(this);
//        endpoints.getOrders().enqueue(new Callback<List<Order>>() {
//            @Override
//            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//                orders = new ArrayList<>(response.body());
//
//                payment.setIdPesanan(orders.stream().filter((order) -> order.getIdPesanan().equals(intent)));
//            }
//
//            @Override
//            public void onFailure(Call<List<Order>> call, Throwable t) {
//
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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

    public void findTotal(View view) {
        int total = Integer.parseInt(totalPayment.getText().toString());
        int pay = Integer.parseInt(this.pay.getText().toString());
        int change = pay - total;

        totalChange.setText(String.valueOf(change));
    }
}
