package com.example.unihomeapp.Landlord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<PropertyData> mList = new ArrayList<>();
    private Context mContext;
    public MyRecyclerAdapter(List<PropertyData> list, Context context){
        mList = list;
        mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_row_item,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(mList.get(position).getName());
        holder.address.setText(mList.get(position).getAddress());
        holder.price.setText(mList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,address,price;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            address = itemView.findViewById(R.id.tvDescription);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }
}
