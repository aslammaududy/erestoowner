package me.aslammaududy.erestoowner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.List;

import me.aslammaududy.erestoowner.R;

public class LayoutBuilderAdapter extends RecyclerView.Adapter<LayoutBuilderAdapter.LayoutBuilderViewHolder> {
    private List<String> layoutHints; //gunanya untuk buat hint dinamis dan untuk tau seberapa banyak form yang harus ditampilkan
    private OnLayoutBuilderFilledListener listener;

    public LayoutBuilderAdapter(List<String> layoutHints) {
        this.layoutHints = layoutHints;
    }

    public void setOnTableAddedListener(OnLayoutBuilderFilledListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LayoutBuilderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
        LayoutBuilderViewHolder viewHolder = new LayoutBuilderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutBuilderViewHolder holder, int position) {
        holder.layoutName.setHint("Layout " + layoutHints.get(position));

        //masih error di sini (x_x)
//        if (!holder.startNumber.getText().toString().equals("")) {
//            int startNumber = Integer.parseInt(holder.startNumber.getText().toString());
//            int endNumber = Integer.parseInt(holder.endNumber.getText().toString());
//
//            listener.onTableNumberFilled(startNumber, endNumber);
//            listener.onLayoutNameFilled(holder.layoutName.getText().toString());
//
//            Log.i("numbers", startNumber + "");
//
//        }


    }

    @Override
    public int getItemCount() {
        return layoutHints.size();
    }

    public interface OnLayoutBuilderFilledListener {
        void onTableNumberFilled(int startNumber, int endNumber);

        void onLayoutNameFilled(String layoutName);
    }

    public class LayoutBuilderViewHolder extends RecyclerView.ViewHolder {
        private BootstrapEditText layoutName, startNumber, endNumber;

        public LayoutBuilderViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutName = itemView.findViewById(R.id.item_layout_name);
            startNumber = itemView.findViewById(R.id.start_number);
            endNumber = itemView.findViewById(R.id.end_number);
        }
    }
}
