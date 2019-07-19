package me.aslammaududy.erestoowner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Model.Layout;
import me.aslammaududy.erestoowner.R;

public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.OuterViewHolder> {
    private List<Layout> layoutNames;
    private List<String> tableNumbers;
    private InnerRecyclerAdapter innerRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    public OuterRecyclerAdapter(List<Layout> layoutNames, Context context) {
        this.layoutNames = layoutNames;
        this.context = context;
    }

    @NonNull
    @Override
    public OuterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.outer_recycler_item, parent, false);

        tableNumbers = new ArrayList<>();
        layoutManager = new GridLayoutManager(parent.getContext(), 5);


        innerRecyclerAdapter = new InnerRecyclerAdapter(tableNumbers, context);

        return new OuterViewHolder(view);

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
        private BootstrapButton mergeTable;

        public OuterViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutName = itemView.findViewById(R.id.layout_name);
            innerRecycler = itemView.findViewById(R.id.inner_recycler);
            mergeTable = itemView.findViewById(R.id.merge_table);

            innerRecycler.setLayoutManager(layoutManager);
            innerRecycler.setAdapter(innerRecyclerAdapter);
        }
    }
}
