package me.aslammaududy.erestoowner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Model.Layout;
import me.aslammaududy.erestoowner.R;

public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.OuterViewHolder> {
    private List<Layout> layoutNames;
    private List<String> tableNumbers;

    public OuterRecyclerAdapter(List<Layout> layoutNames) {
        this.layoutNames = layoutNames;
    }

    @NonNull
    @Override
    public OuterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.outer_recycler_item, parent, false);
        OuterViewHolder outerViewHolder = new OuterViewHolder(view);

        tableNumbers = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(parent.getContext(), 5);


        InnerRecyclerAdapter innerRecyclerAdapter = new InnerRecyclerAdapter(tableNumbers);
        outerViewHolder.innerRecycler.setLayoutManager(layoutManager);
        outerViewHolder.innerRecycler.setAdapter(innerRecyclerAdapter);
        return outerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OuterViewHolder holder, int position) {
        holder.layoutName.setText(layoutNames.get(position).getNama());

        String[] numbers = layoutNames.get(position).getNomorMeja().split(",");

        for (int i = 0; i < numbers.length; i++) {
            tableNumbers.add(numbers[i]);
        }
    }

    @Override
    public int getItemCount() {
        return layoutNames.size();
    }

    public class OuterViewHolder extends RecyclerView.ViewHolder {
        private TextView layoutName;
        private RecyclerView innerRecycler;

        public OuterViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutName = itemView.findViewById(R.id.layout_name);
            innerRecycler = itemView.findViewById(R.id.inner_recycler);
        }
    }
}
