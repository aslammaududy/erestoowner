package me.aslammaududy.erestoowner.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.List;

import me.aslammaududy.erestoowner.Model.Order;
import me.aslammaududy.erestoowner.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orders;
    private Context context;
    private OnOrderAddedListener listener;
    private OnShowOrderItemDetailListener listener1;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    public void setOnOrderAddedListener(OnOrderAddedListener listener) {
        this.listener = listener;
    }

    public void setOnShowOrderItemDetailListener(OnShowOrderItemDetailListener listener1) {
        this.listener1 = listener1;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new OrderViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.menuName.setText(orders.get(position).getNama());
        holder.menuCount.setText("x" + orders.get(position).getCount());
        holder.menuPrice.setText(orders.get(position).getHarga());

        listener.findTotal(orders.get(position).getHarga(), orders.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public interface OnOrderAddedListener {
        void findTotal(String price, int count);
    }

    public interface OnShowOrderItemDetailListener {
        void onShowOrderItemDetail(int count, int discount, String note, int position);

        void onDeleteOrderItem(int count, int position);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView menuName, menuCount, menuPrice, detailMenuName;
        private View viewDialog;
        private BootstrapEditText count, discount, note;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            menuName = itemView.findViewById(R.id.menu_detail_name);
            menuCount = itemView.findViewById(R.id.menu_detail_count);
            menuPrice = itemView.findViewById(R.id.menu_detail_price);

            itemView.setOnClickListener(v -> {
                viewDialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_item_menu, null);
                detailMenuName = viewDialog.findViewById(R.id.edit_menu_detail_name);
                count = viewDialog.findViewById(R.id.edit_menu_detail_count);
                discount = viewDialog.findViewById(R.id.edit_menu_detail_discount);
                note = viewDialog.findViewById(R.id.edit_menu_detail_note);

                detailMenuName.setText(menuName.getText().toString());

                if (orders.get(getAdapterPosition()).getCount() > 1) {
                    count.setText(String.valueOf(orders.get(getAdapterPosition()).getCount()));
                }

                if (orders.get(getAdapterPosition()).getDiskon() != null) {
                    discount.setText(orders.get(getAdapterPosition()).getDiskon());
                }

                if (orders.get(getAdapterPosition()).getNote() != null) {
                    note.setText(orders.get(getAdapterPosition()).getNote());
                }

                new AlertDialog.Builder(context)
                        .setView(viewDialog)
                        .setPositiveButton("Simpan", (dialog, which) -> {
                            int cnt = Integer.parseInt(count.getText().toString());
                            int dscnt = Integer.parseInt(discount.getText().toString());
                            String nt = note.getText().toString();

                            listener1.onShowOrderItemDetail(cnt, dscnt, nt, getAdapterPosition());
                        })
                        .setNegativeButton("Batal", null)
                        .setNeutralButton("Hapus", (dialog, which) -> {
                            int cnt = Integer.parseInt(count.getText().toString());
                            listener1.onDeleteOrderItem(cnt, getAdapterPosition());
                        })
                        .setCancelable(false)
                        .show();
            });
        }
    }
}
