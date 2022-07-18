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

import com.example.realestate.Adapter.LicensorResidentialAdapter;
import com.example.realestate.Adapter.buyerCommercialAdapter;
import com.example.realestate.Model.BuyerCommercialModel;
import com.example.realestate.Model.LicensorResidentialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class LicensorResidentialEntries extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LicensorResidentialAdapter adapter;
    List<LicensorResidentialModel> licensorResidentialModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licensor_residential_entries);
        this.setTitle("Residential: Licensor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.buyer_recycler);
        adapter=new LicensorResidentialAdapter(licensorResidentialModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();

    }

    private void showalertfilter() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(LicensorResidentialEntries.this);
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

    List<LicensorResidentialModel> addValues() {


        LicensorResidentialModel buyerCommercialModel1 = new LicensorResidentialModel("Akash","21-5-2021","yugal sanjala","102","Baner","office","pune","semi","9975019502","9876543210","good");
        LicensorResidentialModel buyerCommercialModel2 = new LicensorResidentialModel("Akash","21-5-2021","yugal sanjala","102","Baner","office","pune","semi","9975019502","9876543210","good");
        LicensorResidentialModel buyerCommercialModel3 = new LicensorResidentialModel("Akash","21-5-2021","yugal sanjala","102","Baner","office","pune","semi","9975019502","9876543210","good");
        LicensorResidentialModel buyerCommercialModel4 = new LicensorResidentialModel("Akash","21-5-2021","yugal sanjala","102","Baner","office","pune","semi","9975019502","9876543210","good");
        LicensorResidentialModel buyerCommercialModel5 = new LicensorResidentialModel("Akash","21-5-2021","yugal sanjala","102","Baner","office","pune","semi","9975019502","9876543210","good");

        licensorResidentialModelList.add(buyerCommercialModel1);
        licensorResidentialModelList.add(buyerCommercialModel2);
        licensorResidentialModelList.add(buyerCommercialModel3);
        licensorResidentialModelList.add(buyerCommercialModel4);
        licensorResidentialModelList.add(buyerCommercialModel5);

        return licensorResidentialModelList;
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