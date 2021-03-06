package com.example.unihomeapp.Landlord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unihomeapp.Models.EnquiryModel;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;

import java.util.List;

public class EnquiryAdapter  extends RecyclerView.Adapter<EnquiryAdapter.EnquiryViewHolder> {

    private Context mContext2;
    private List<EnquiryModel> myEnquiryList;


    public class EnquiryViewHolder extends RecyclerView.ViewHolder {

        TextView mName2, mProperty2;
        CardView mCardView2;

        public EnquiryViewHolder(@NonNull View itemView) {
            super(itemView);
            mName2 = itemView.findViewById(R.id.tvName2);
            mProperty2 = itemView.findViewById(R.id.tvDescription2);
            mCardView2 = itemView.findViewById(R.id.myCardView2);
        }

    }

    public EnquiryAdapter(Context mContext2, List<EnquiryModel> myEnquiryList) {
        this.mContext2 = mContext2;
        this.myEnquiryList = myEnquiryList;

    }

    @Override
    public EnquiryAdapter.EnquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.enquiry_row_item, parent, false);
        return new EnquiryViewHolder(mView);


    }

    @Override
    public void onBindViewHolder(@NonNull EnquiryAdapter.EnquiryViewHolder holder, int i) {
        holder.mName2.setText(myEnquiryList.get(i).getsName());
        holder.mProperty2.setText(myEnquiryList.get(i).getSubject());

        final String property_key = myEnquiryList.get(i).getPropertyKey().toString();


        holder.mCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String property_key = myEnquiryList.get(i).getPropertyKey().toString();
                Intent intent2 = new Intent(mContext2, EnquiryDetail.class);
                intent2.putExtra("PropertyID",property_key);
                mContext2.startActivity(intent2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myEnquiryList.size();
    }
}
