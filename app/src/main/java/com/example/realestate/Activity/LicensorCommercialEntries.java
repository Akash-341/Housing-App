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

import com.example.realestate.Adapter.LicensorCommercialAdapter;
import com.example.realestate.Adapter.LicensorResidentialAdapter;
import com.example.realestate.Model.LicensorCommercialModel;
import com.example.realestate.Model.LicensorResidentialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class LicensorCommercialEntries extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LicensorCommercialAdapter adapter;
    List<LicensorCommercialModel> licensorCommercialModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licensor_commercial_entries);
        this.setTitle("Commercial: Licensor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.buyer_recycler);
        adapter=new LicensorCommercialAdapter(licensorCommercialModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();
    }

    private void showalertfilter() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(LicensorCommercialEntries.this);
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

    List<LicensorCommercialModel> addValues() {


        LicensorCommercialModel buyerCommercialModel1 = new LicensorCommercialModel("21-5-2021","Akash","Yugal sanjala","office","Baner","Pune","9975019502","9876543210","Good");
        LicensorCommercialModel buyerCommercialModel2 = new LicensorCommercialModel("21-5-2021","Akash","Yugal sanjala","office","Baner","Pune","9975019502","9876543210","Good");
        LicensorCommercialModel buyerCommercialModel3 = new LicensorCommercialModel("21-5-2021","Akash","Yugal sanjala","office","Baner","Pune","9975019502","9876543210","Good");
        LicensorCommercialModel buyerCommercialModel4 = new LicensorCommercialModel("21-5-2021","Akash","Yugal sanjala","office","Baner","Pune","9975019502","9876543210","Good");
        LicensorCommercialModel buyerCommercialModel5 = new LicensorCommercialModel("21-5-2021","Akash","Yugal sanjala","office","Baner","Pune","9975019502","9876543210","Good");

        licensorCommercialModelList.add(buyerCommercialModel1);
        licensorCommercialModelList.add(buyerCommercialModel2);
        licensorCommercialModelList.add(buyerCommercialModel3);
        licensorCommercialModelList.add(buyerCommercialModel4);
        licensorCommercialModelList.add(buyerCommercialModel5);

        return licensorCommercialModelList;
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