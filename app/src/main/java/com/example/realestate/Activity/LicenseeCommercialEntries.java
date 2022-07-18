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

import com.example.realestate.Adapter.LicenseeCommercialAdapter;
import com.example.realestate.Adapter.buyerCommercialAdapter;
import com.example.realestate.Model.BuyerCommercialModel;
import com.example.realestate.Model.LicenseeCommercialModel;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;

public class LicenseeCommercialEntries extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LicenseeCommercialAdapter adapter;
    List<LicenseeCommercialModel> licenseeCommercialModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licensee_commercial_entries);
        this.setTitle("Commercial: Licensee");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.buyer_recycler);
        adapter=new LicenseeCommercialAdapter(licenseeCommercialModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();

    }

    private void showAlertFilter() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(LicenseeCommercialEntries.this);
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

    List<LicenseeCommercialModel> addValues() {


        LicenseeCommercialModel licenseeCommercialModel1 = new LicenseeCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK" , "Good");
        LicenseeCommercialModel licenseeCommercialModel2 = new LicenseeCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK", "Good");
        LicenseeCommercialModel licenseeCommercialModel3 = new LicenseeCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK",  "Good");
        LicenseeCommercialModel licenseeCommercialModel4= new LicenseeCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK",  "Good");
        LicenseeCommercialModel licenseeCommercialModel5 = new LicenseeCommercialModel("21-5-2021", "Akash", "9975019502", "9876543210", "2BHK",  "Good");

        licenseeCommercialModelList.add(licenseeCommercialModel1);
        licenseeCommercialModelList.add(licenseeCommercialModel2);
        licenseeCommercialModelList.add(licenseeCommercialModel3);
        licenseeCommercialModelList.add(licenseeCommercialModel4);
        licenseeCommercialModelList.add(licenseeCommercialModel5);
        return licenseeCommercialModelList;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.filter:
                showAlertFilter();
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