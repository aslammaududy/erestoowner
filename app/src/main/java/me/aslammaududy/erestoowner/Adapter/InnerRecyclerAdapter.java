package me.aslammaududy.erestoowner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.List;

import me.aslammaududy.erestoowner.R;

public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.InnerViewHolder> {
    List<String> tableNumbers;

    public InnerRecyclerAdapter(List<String> tableNumbers) {
        this.tableNumbers = tableNumbers;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inner_recycler_item, parent, false);
        InnerViewHolder innerViewHolder = new InnerViewHolder(view);

        return innerViewHolder;
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

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);

            tableNumber = itemView.findViewById(R.id.table_button);
        }
    }
}
