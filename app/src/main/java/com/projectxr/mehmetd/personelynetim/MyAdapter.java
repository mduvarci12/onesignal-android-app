package com.projectxr.mehmetd.personelynetim;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projectxr.mehmetd.personelynetim.models.LoginResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
final ListItem listItem= listItems.get(position );
       //  holder.textViewName.setText(listItem.getProductImage());
         holder.textViewImage.setText(listItem.getFirmaName());

        Picasso.with(context)
                .load(listItem.getProductImage())
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 String mekanID = listItem.getMekan_id();
                Intent i = new Intent(context,FirmaSec.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
      //  public TextView textViewName;
        public TextView textViewImage;
        public ImageView imageView;
        public TextView mekanText;

        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            textViewImage = itemView.findViewById(R.id.firmaName);
           // textViewName= itemView.findViewById(R.id.productImage);
            imageView = itemView.findViewById(R.id.productImage);

            linearLayout = itemView.findViewById(R.id.linearLayout);


        }
    }



}
