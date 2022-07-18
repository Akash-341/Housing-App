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

import com.example.realestate.Adapter.buyerCommercialAdapter;
import com.example.realestate.Model.BuyerCommercialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class BuyerResidentialEntries extends AppCompatActivity {
    private RecyclerView recyclerView;
    private buyerCommercialAdapter adapter;
    List<BuyerCommercialModel> buyerCommercialModelList=new ArrayList<>();
    ImageView filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_residential_entries);
        this.setTitle("Residential: buyer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.buyer_recycler);
        adapter=new buyerCommercialAdapter(buyerCommercialModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();
    }

    private void showFilterAlert() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(BuyerResidentialEntries.this);
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

    List<BuyerCommercialModel> addValues() {


        BuyerCommercialModel buyerCommercialModel1 = new BuyerCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Pune", "Good");
        BuyerCommercialModel buyerCommercialModel2 = new BuyerCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Pune", "Good");
        BuyerCommercialModel buyerCommercialModel3 = new BuyerCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Pune", "Good");
        BuyerCommercialModel buyerCommercialModel4 = new BuyerCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Pune", "Good");
        BuyerCommercialModel buyerCommercialModel5 = new BuyerCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Pune", "Good");

        buyerCommercialModelList.add(buyerCommercialModel1);
        buyerCommercialModelList.add(buyerCommercialModel2);
        buyerCommercialModelList.add(buyerCommercialModel3);
        buyerCommercialModelList.add(buyerCommercialModel4);
        buyerCommercialModelList.add(buyerCommercialModel5);
        return buyerCommercialModelList;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.filter:
                showFilterAlert();
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