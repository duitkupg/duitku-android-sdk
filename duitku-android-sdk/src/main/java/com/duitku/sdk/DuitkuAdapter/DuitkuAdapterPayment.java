package com.duitku.sdk.DuitkuAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duitku.sdk.DuitkuTransaction;
import com.duitku.sdk.Model.ResponseGetListPaymentMethod;
import com.duitku.sdk.R;
import com.duitku.sdk.Model.ResponseListPaymentMethod;
import com.duitku.sdk.DuitkuUtility.DuitkuKit;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


import butterknife.ButterKnife;

public class DuitkuAdapterPayment extends RecyclerView.Adapter<DuitkuAdapterPayment.AdapterHolder>{

    List<ResponseListPaymentMethod> AllPaymentItemList;
    Context mContext;


    public DuitkuAdapterPayment(Context context, List<ResponseListPaymentMethod> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final ResponseListPaymentMethod responsePaymentMethod = AllPaymentItemList.get(position);

        String payment = responsePaymentMethod.getPaymentName();
        String code = responsePaymentMethod.getPaymentMethod();
        String url= responsePaymentMethod.getPaymentImage();
        String fee = responsePaymentMethod.getTotalFee();
        int fee_ = Integer.parseInt(fee);
        holder.tvPayment.setText(payment);
        holder.tvDetail.setText("Bayar dengan "+payment);


        if (fee_ == 0){
            holder.tvFee.setText("");
        }else{
            holder.tvFee.setText("Fee "+"Rp "+conversiRupiah(""+fee ));
        }

        Glide.with(mContext).load(url).into(holder.ivTextDrawable);

        holder.btn_itemRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DuitkuKit DuitkuKit = new DuitkuKit();
                DuitkuKit.setTitlePayment(payment);
                DuitkuKit.setPaymentMethod(code);
                mContext.startActivity(new Intent(mContext, DuitkuTransaction.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{


        ImageView ivTextDrawable;

        TextView tvPayment;

        TextView tvDetail;

        TextView tvFee;

        RelativeLayout btn_itemRow;

        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            ivTextDrawable = itemView.findViewById(R.id.ivTextDrawable);
            tvPayment = itemView.findViewById(R.id.tvPaymentMethod);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            tvFee = itemView.findViewById(R.id.tvFee);
            btn_itemRow = itemView.findViewById(R.id.btn_itemRow);
        }
    }



    private String conversiRupiah( String payment){
        //conversi currency
        int number = Integer.parseInt(payment);
        String currency = NumberFormat.getNumberInstance(Locale.US).format(number);
        return currency;
    }

}
