package com.rg.developer.maquinaexpendedora;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.rg.developer.maquinaexpendedora.models.Producto;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Producto> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Producto> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);

        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.binData(mData.get(position));
    }

    public void setItems(List<Producto> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name, precio, status;

        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.iconImageView);
            name = view.findViewById(R.id.nameTextView);
            precio = view.findViewById(R.id.cityTextView);
            status = view.findViewById(R.id.statusTextView);
        }

        void binData(final Producto item){
            imageView.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
            name.setText(item.getNombre());
            precio.setText(""+item.getPrecio_venta());
            status.setText(""+item.getId());
        }
    }


}
