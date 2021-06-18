package com.magednan.elmagdshoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.magednan.elmagdshoppinglist.R;
import com.magednan.elmagdshoppinglist.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Holder> {

    Context context;
    private List<ShoppingItem> mList = new ArrayList<>();

    @Inject
    public ItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ShoppingItem item = mList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public void setList(List<ShoppingItem> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CardView parent;
        TextView type, note, amount, date;

        public Holder(@NonNull View itemView) {
            super(itemView);
             date = itemView.findViewById(R.id.date);
             type = itemView.findViewById(R.id.type);
            note = itemView.findViewById(R.id.note);
            amount = itemView.findViewById(R.id.amount);
            parent = itemView.findViewById(R.id.parent);
        }

        public void bind(ShoppingItem model) {
            date.setText(model.getDate());
            type.setText(model.getType());
            note.setText(model.getNote());
            String value = String.valueOf(model.getAmount());
            amount.setText("$ " + value);

        }
    }
}
