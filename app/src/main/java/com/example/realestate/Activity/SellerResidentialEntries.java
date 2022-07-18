package com.example.realestate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.realestate.Adapter.RecentRecyclerAdapter;
import com.example.realestate.Adapter.SellerResidentialAdapter;
import com.example.realestate.Model.RecentEntriesModel;
import com.example.realestate.Model.SellerResidentialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class SellerResidentialEntries extends AppCompatActivity {
    RecyclerView recyclerView;
    SellerResidentialAdapter adapter;
    List<SellerResidentialModel> sellerResidentialModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_residential_entries);
        this.setTitle("Residential: Seller");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.buyer_recycler);
        adapter=new SellerResidentialAdapter(sellerResidentialModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();


    }

    private void showalertfilter() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(SellerResidentialEntries.this);
        final View view =getLayoutInflater().inflate(R.layout.filter_dialog,null);
        SeekBar seekBar;

        seekBar=view.findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    List<SellerResidentialModel> addValues() {

        SellerResidentialModel recentEntriesModel=new SellerResidentialModel("21-5-2021","Akash","Yugal sanjala","1","baner","2.5cr","9975019502","9876543210","office");
        SellerResidentialModel recentEntriesModel1=new SellerResidentialModel("21-5-2021","Akash","Yugal sanjala","1","baner","2.5cr","9975019502","9876543210","office");

        sellerResidentialModelList.add(recentEntriesModel);
        sellerResidentialModelList.add(recentEntriesModel1);
        adapter.notifyDataSetChanged();

        return sellerResidentialModelList;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.filter:
                showalertfilter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu,menu);
        return true;
    }
}