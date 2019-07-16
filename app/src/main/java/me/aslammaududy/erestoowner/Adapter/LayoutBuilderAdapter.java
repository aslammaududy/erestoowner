package me.aslammaududy.erestoowner.Adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.R;

public class LayoutBuilderAdapter extends RecyclerView.Adapter<LayoutBuilderAdapter.LayoutBuilderViewHolder> {
    private List<String> layoutHints; //gunanya untuk buat hint dinamis dan untuk tau seberapa banyak form yang harus ditampilkan
    private List<LayoutItem> layoutItems;
    private String[] names, sNums, eNums;
    private LayoutItem layoutItem;

    public LayoutBuilderAdapter(List<String> layoutHints) {
        this.layoutHints = layoutHints;
        layoutItems = new ArrayList<>();
        names = new String[layoutHints.size()];
        sNums = new String[layoutHints.size()];
        eNums = new String[layoutHints.size()];
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
    }

    @Override
    public int getItemCount() {
        return layoutHints.size();
    }

    public List<LayoutItem> retrieveData() {
        for (int i = 0; i < layoutHints.size(); i++) {
            layoutItem = new LayoutItem();
            layoutItem.layoutName = names[i];
            layoutItem.startNumber = sNums[i];
            layoutItem.endNumber = eNums[i];

            layoutItems.add(layoutItem);
        }
        return layoutItems;
    }

    public class LayoutItem {
        public String layoutName, startNumber, endNumber;
    }

    public class LayoutBuilderViewHolder extends RecyclerView.ViewHolder {
        private BootstrapEditText layoutName, startNumber, endNumber;

        public LayoutBuilderViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutName = itemView.findViewById(R.id.item_layout_name);
            startNumber = itemView.findViewById(R.id.start_number);
            endNumber = itemView.findViewById(R.id.end_number);

//            layoutName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_DONE) {
//                        layoutItems.get(getAdapterPosition()).setLayoutName(layoutName.getText().toString());
//                        return true;
//                    }
//                    return false;
//                }
//            });
//
//            startNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_DONE) {
//                        layoutItems.get(getAdapterPosition()).setLayoutName(startNumber.getText().toString());
//                        return true;
//                    }
//                    return false;
//                }
//            });
//
//            endNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_DONE) {
//                        layoutItems.get(getAdapterPosition()).setLayoutName(endNumber.getText().toString());
//                        return true;
//                    }
//                    return false;
//                }
//            });
            layoutName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    names[getAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            startNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    sNums[getAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            endNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    eNums[getAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
