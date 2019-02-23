package com.example.poductlistapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import java.util.List;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        Context context;
        List<Employee> productList;
        OnProductClickedListener listener;

    public CustomAdapter(Context context, List<Employee> productList, OnProductClickedListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.row_list_item,viewGroup,false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int position) {
       Employee product = productList.get(position);
       myViewHolder.tvName.setText(product.getFirstname());
       myViewHolder.tvPrice.setText(product.getAge());
       myViewHolder.tvId.setText(product.getMail());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvId,tvName,tvPrice;
    public MyViewHolder(View itemView){
        super(itemView);
        tvId= itemView.findViewById(R.id.pName);
        tvName=itemView.findViewById(R.id.pMenny);
        tvPrice=itemView.findViewById(R.id.pCode);

        tvName.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
        int position = getAdapterPosition();
        listener.onProductClicked(position);

        }
    }
public interface OnProductClickedListener{
        void onProductClicked(int position);
}

}
