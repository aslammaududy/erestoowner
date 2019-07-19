package me.aslammaududy.erestoowner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Model.Menu;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.NetworkManager.Endpoints;
import me.aslammaududy.erestoowner.R;

public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.InnerViewHolder> {
    private List<String> tableNumbers;
    private Context context;

    public InnerRecyclerAdapter(List<String> tableNumbers, Context context) {
        this.tableNumbers = tableNumbers;
        this.context = context;
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
        private LinearLayout innerLayout;
        private View view;
        private Endpoints endpoints;
        private List<Menu> menus;
        private List<List<String>> tmpMenuName, tmpMenuSum;
        //        private TextView menuName, menuPrice, menuDiscount;
        private EditText menuName1, menuName2, menuName3, menuSum1, menuSum2, menuSum3;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tmpMenuName = new ArrayList<>();
            tmpMenuSum = new ArrayList<>();

            innerLayout = itemView.findViewById(R.id.inner_layout);
            tableNumber = itemView.findViewById(R.id.table_button);

            endpoints = Connection.getEndpoints();

            tableNumber.setOnClickListener(listener -> {
                tableNumber.setShowOutline(false);

                view = LayoutInflater.from(context).inflate(R.layout.menu_detail, null);

                menuName1 = view.findViewById(R.id.menu_name1_);
                menuName2 = view.findViewById(R.id.menu_name2_);
                menuName3 = view.findViewById(R.id.menu_name3_);

                menuSum1 = view.findViewById(R.id.menu_sum1_);
                menuSum2 = view.findViewById(R.id.menu_sum2_);
                menuSum3 = view.findViewById(R.id.menu_sum3_);

//                        menuName = view.findViewById(R.id.detail_menu_name);
//                        menuPrice = view.findViewById(R.id.detail_menu_price);
//                        menuDiscount = view.findViewById(R.id.detail_menu_discount);
//
//                        endpoints.getMenus().enqueue(new Callback<List<Menu>>() {
//                            @Override
//                            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
//                                menus = new ArrayList<>(response.body());
//
//                                menuName.setText(menus.get(0).getNama());
//                                menuPrice.setText(menus.get(0).getHarga());
//                                menuDiscount.setText(menus.get(0).getDiskon());
//                            }
//
//                            @Override
//                            public void onFailure(Call<List<Menu>> call, Throwable t) {
//
//                            }
//                        });

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(view)
                        .setPositiveButton("Simpan", (dialog1, which) -> {
                            List<String> tmn = new ArrayList<>();
                            List<String> tms = new ArrayList<>();

                            tmn.add(menuName1.getText().toString());
                            tmn.add(menuName2.getText().toString());
                            tmn.add(menuName3.getText().toString());

                            tms.add(menuSum1.getText().toString());
                            tms.add(menuSum2.getText().toString());
                            tms.add(menuSum3.getText().toString());

                            tmpMenuName.add(tmn);

                            tmpMenuSum.add(tms);

                        })
                        .setNegativeButton("Tutup", null)
                        .setNeutralButton("Telah Bayar", (dialog1, which) -> {
                            tmpMenuSum.clear();
                            tmpMenuName.clear();

                            tableNumber.setShowOutline(true);
                        })
                        .setCancelable(false)
                        .show();


                if (dialog.isShowing()) {
                    if (!tmpMenuName.isEmpty() && !tmpMenuSum.isEmpty()) {
                        menuName1.setText(tmpMenuName.get(0).get(0));
                        menuName2.setText(tmpMenuName.get(0).get(1));
                        menuName3.setText(tmpMenuName.get(0).get(2));

                        menuSum1.setText(tmpMenuSum.get(0).get(0));
                        menuSum2.setText(tmpMenuSum.get(0).get(1));
                        menuSum3.setText(tmpMenuSum.get(0).get(2));
                    }
                }
            });
        }
    }
}
