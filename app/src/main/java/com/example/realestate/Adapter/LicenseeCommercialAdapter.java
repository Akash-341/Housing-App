package com.example.realestate.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Activity.Seller_commercial;
import com.example.realestate.Model.BuyerCommercialModel;
import com.example.realestate.Model.LicenseeCommercialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class LicenseeCommercialAdapter extends RecyclerView.Adapter<LicenseeCommercialAdapter.ViewHolder> {
    private List<LicenseeCommercialModel> licenseeCommercialModelList=new ArrayList<>();
    Context context;

    public LicenseeCommercialAdapter(List<LicenseeCommercialModel> licenseeCommercialModelList, Context context) {
        this.licenseeCommercialModelList = licenseeCommercialModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.licensee_commercial_entries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LicenseeCommercialModel lcm=licenseeCommercialModelList.get(position);
        holder.date.setText(lcm.getDate());
        holder.name.setText(lcm.getName());
        holder.type.setText(lcm.getType());
        holder.mob1.setText(lcm.getMob1());
        holder.mob2.setText(lcm.getMob2());
        holder.imgmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup=new PopupMenu(context,holder.imgmenu);

                popup.inflate(R.menu.edit_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                Intent intent = new Intent (context, Seller_commercial.class);
                                context.startActivity(intent);
                                return true;
                            case R.id.delete:
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                                alertBuilder.setTitle("Delete");
                                alertBuilder.setMessage("Are you sure want to delete Entries?");
                                alertBuilder.setCancelable(false);
                                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertBuilder.setCancelable(true);
                                    }
                                });
                                alertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertBuilder.setCancelable(false);
                                    }
                                });

                                AlertDialog dialog = alertBuilder.create();
                                dialog.show();
                                return true;

                            default:
                                return false;
                        }

                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return licenseeCommercialModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,flatno ,date,building,location,type,area,mob1,mob2,price,remarks;
        LinearLayout linear1,linear2;
        ImageView imgarrow,imgarrow2,imgmenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tvdate);
            name=itemView.findViewById(R.id.tvName);
            type=itemView.findViewById(R.id.tvtype);
            mob1=itemView.findViewById(R.id.tvMob1);
            mob2=itemView.findViewById(R.id.tvmob2);
            remarks=itemView.findViewById(R.id.tvreamrks);
            linear1=itemView.findViewById(R.id.linear1);
            linear2=itemView.findViewById(R.id.linear2);
            imgarrow=itemView.findViewById(R.id.arrow);
            imgarrow2=itemView.findViewById(R.id.arrow2);
            imgmenu=itemView.findViewById(R.id.edit);
            linear1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (linear2.getVisibility()==view.VISIBLE){
                        linear2.setVisibility(View.GONE);
                        imgarrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                        imgarrow.setVisibility(View.VISIBLE);
                    }else{
                        linear2.setVisibility(View.VISIBLE);
                        imgarrow.setVisibility(View.GONE);
                        imgarrow2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    }
                }
            });
        }
    }
}
