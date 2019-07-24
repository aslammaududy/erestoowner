package me.aslammaududy.erestoowner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Model.Menu;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Menu> menuList, orderedMenu;
    private OnOrderedMenuListener listener;
    private String imgURL;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
        orderedMenu = new ArrayList<>();

        imgURL = Connection.IMG;
    }

    public void setOnOrderedMenuListener(OnOrderedMenuListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.menuName.setText(menuList.get(position).getNama());

        Glide.with(holder.menuImage.getContext())
                .load(imgURL + menuList.get(position).getGambar())
                .into(holder.menuImage);

        holder.price = menuList.get(position).getHarga();
        holder.name = menuList.get(position).getNama();
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnOrderedMenuListener {
        void onOrderedMenu(String menuName, String menuPrice, int count);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView menuName;
        private ImageView menuImage;
        private String name, price;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            menuImage = itemView.findViewById(R.id.menu_img);
            menuName = itemView.findViewById(R.id.menu_name);

            itemView.setOnClickListener(v -> listener.onOrderedMenu(name, price, 1));
        }
    }
}
