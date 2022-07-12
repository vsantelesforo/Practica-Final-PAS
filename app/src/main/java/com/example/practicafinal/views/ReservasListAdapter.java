package com.example.practicafinal.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicafinal.R;
import com.example.practicafinal.database.entity.ReservaEntity;

import java.util.List;

public class ReservasListAdapter extends RecyclerView.Adapter<ReservasListAdapter.ReservaViewHolder> {

    static class ReservaViewHolder extends RecyclerView.ViewHolder{
        private final TextView dateItemView;
        private final TextView hourItemView;
        private final TextView courtItemView;

        private ReservaViewHolder(View item){
            super(item);
            dateItemView = item.findViewById(R.id.item_date);
            hourItemView = item.findViewById(R.id.subitem_hour);
            courtItemView = item.findViewById(R.id.subitem_court);
        }
    }

    private final LayoutInflater mInflater;
    private List<ReservaEntity> itemsList;

    public ReservasListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ReservaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        if (itemsList != null) {
            ReservaEntity current = itemsList.get(position);
//            String text =  current.getFecha() + "  " + current.getHora() + "  " + current.getPista();
            holder.dateItemView.setText(current.getFecha());
            holder.hourItemView.setText(current.getHora());
            holder.courtItemView.setText(current.getPista());
        } else {
            // Covers the case of data not being ready yet.
            holder.dateItemView.setText("No item");
            holder.hourItemView.setText("No item");
            holder.courtItemView.setText("No item");
        }
    }

    public void setItems(List<ReservaEntity> userList){
        itemsList = userList;
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (itemsList == null)
                ? 0
                : itemsList.size();
    }

    public ReservaEntity getGrupoAtPosition (int position) {
        return itemsList.get(position);
    }
}
