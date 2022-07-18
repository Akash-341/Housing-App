package com.example.realestate.Activity;

import static android.view.View.VISIBLE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.realestate.Adapter.RecentRecyclerAdapter;
import com.example.realestate.BaseUrl.AllUrl;
import com.example.realestate.Model.RecentEntriesModel;
import com.example.realestate.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecentRecyclerAdapter adapter;
    List<RecentEntriesModel> recentEntriesModelList=new ArrayList<>();
    FloatingActionButton add, licence,seller;

    DrawerLayout drawerLayout;
    TextView tvlicence,tvseller;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Boolean isAllFabsVisible;
    ImageView imgfilter,close;
    LinearLayout linearmain;

    List<String>rbLocationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Dashboard");

        recyclerView=findViewById(R.id.recent_recycler);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        add=findViewById(R.id.fltadd);
        licence=findViewById(R.id.fltlicense);
        seller=findViewById(R.id.fltseller);
        tvlicence=findViewById(R.id.licence);
        tvseller=findViewById(R.id.Seller);
        imgfilter=findViewById(R.id.imgfilter);

        linearmain=findViewById(R.id.linearmain);
        licence.setVisibility(View.GONE);
        seller.setVisibility(View.GONE);
        tvlicence.setVisibility(View.GONE);
        tvseller.setVisibility(View.GONE);



        getlocation();

        isAllFabsVisible=false;
//        add.addButton(licence);
//        add.addButton(seller);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible){
                    licence.setVisibility(View.VISIBLE);
                    seller.setVisibility(View.VISIBLE);
                    tvlicence.setVisibility(View.VISIBLE);
                    tvseller.setVisibility(View.VISIBLE);
                    isAllFabsVisible=true;
                    linearmain.setFocusable(false);
                }
                else{
                    licence.setVisibility(View.GONE);
                    seller.setVisibility(View.GONE);
                    tvlicence.setVisibility(View.INVISIBLE);
                    tvseller.setVisibility(View.INVISIBLE);
                    isAllFabsVisible = false;
                }
            }
        });


        licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommercialDialog();
            }
        });

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResidentialDialog();
            }
        });

        imgfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterAlert();
            }
        });
        linearmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible){

                    isAllFabsVisible=true;
                }
                else{
                    licence.setVisibility(View.GONE);
                    seller.setVisibility(View.GONE);
                    tvlicence.setVisibility(View.INVISIBLE);
                    tvseller.setVisibility(View.INVISIBLE);
                    isAllFabsVisible = false;
                }
            }
        });
        adapter=new RecentRecyclerAdapter(recentEntriesModelList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        addValues();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Menu menu=navigationView.getMenu();

              if (id==R.id.Dashboard) {
                  startActivity(new Intent(MainActivity.this, MainActivity.class));
              }
              if (id==R.id.commercial){

                  boolean b=!menu.findItem(R.id.BuyerCommercial).isVisible();
                  menu.findItem(R.id.BuyerCommercial).setVisible(b);
                  menu.findItem(R.id.LicenseeCommercial).setVisible(b);
                  menu.findItem(R.id.SellerRCommercial).setVisible(b);
                  menu.findItem(R.id.LicensorCommercial).setVisible(b);

                  return true;
              }
                if (id==R.id.residential){
                    boolean b=!menu.findItem(R.id.BuyerResidential).isVisible();
                    menu.findItem(R.id.BuyerResidential).setVisible(b);
                    menu.findItem(R.id.LicenseeResidential).setVisible(b);
                    menu.findItem(R.id.SellerResidential).setVisible(b);
                    menu.findItem(R.id.LicensorResidential).setVisible(b);

                    return true;
                }
                    if (id==R.id.BuyerCommercial) {
                        startActivity(new Intent(MainActivity.this, BuyerCommercialEntries.class));
                    }
                    if (id==R.id.allentries){
                        startActivity(new Intent(MainActivity.this, AllEntries.class));
                    }
                if (id==R.id.BuyerResidential) {
                    startActivity(new Intent(MainActivity.this, BuyerResidentialEntries.class));
                }
                if (id==R.id.LicenseeCommercial) {
                    startActivity(new Intent(MainActivity.this, LicenseeCommercialEntries.class));
                }
                if (id==R.id.LicenseeResidential) {
                    startActivity(new Intent(MainActivity.this, LicenseeResidentialEntries.class));
                }
                if (id==R.id.LicensorCommercial) {
                    startActivity(new Intent(MainActivity.this,LicensorCommercialEntries.class));
                }
                if (id==R.id.LicensorResidential) {
                    startActivity(new Intent(MainActivity.this, LicensorResidentialEntries.class));
                }
                if (id==R.id.SellerRCommercial) {
                    startActivity(new Intent(MainActivity.this,SellerCommercialEntries.class));
                }
                if (id==R.id.SellerResidential) {
                    startActivity(new Intent(MainActivity.this, SellerResidentialEntries.class));
                }
                if (id==R.id.LogOut){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setTitle("Delete");
                    alertBuilder.setMessage("Are you sure want to Logout?");
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

                }

                return true;
            }
        });
    }


    private void showFilterAlert() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        final View view =getLayoutInflater().inflate(R.layout.filter_dialog,null);
        SeekBar seekBar;
        Button btnapply,btnreset;
        ImageView close;
        RadioGroup radiogrp;

        btnapply=view.findViewById(R.id.btnapply);
        btnreset=view.findViewById(R.id.btnreset);
        close=view.findViewById(R.id.imgclose);
        radiogrp=view.findViewById(R.id.rbgrp);

        seekBar=view.findViewById(R.id.seekbar);

        for(String location:rbLocationList){
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(location);
            radiogrp.addView(rdbtn);
        }

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
        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();
            }
        });
        alertDialog.show();
    }
    private void getlocation(){

        StringRequest stringRequest=new StringRequest(Request.Method.GET, AllUrl.GET_LOCATION_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){

                    Log.e( "onResponse: ",response );
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray locationArray=jsonObject.getJSONArray("LocationModelList");
                        for (int i=0;i<locationArray.length();i++){

                            JSONObject item=locationArray.getJSONObject(i);
                            String loc=item.getString("Location");
                            Log.e("onResponse: ",loc );
                            rbLocationList.add(loc);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplication().getApplicationContext()).add(stringRequest);
    }
    private void showResidentialDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.bottomSheetBackground));
        final View view =getLayoutInflater().inflate(R.layout.residential_dialog,null);
        TextView dialoglicensor,dialogLicensee,dialogseller,dialogBuyer;
        ImageView close;

        view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialoglicensor=view.findViewById(R.id.dialoglicensor);
        dialogLicensee=view.findViewById(R.id.dialogLicensee);
        dialogseller=view.findViewById(R.id.dialogSeller);
        dialogBuyer=view.findViewById(R.id.dialogBuyer);
        close=view.findViewById(R.id.imgclose);


        dialoglicensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Licensor_residential.class));
            }
        });
        dialogLicensee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Licensee_Residential.class));
            }
        });
        dialogseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Seller_residential.class));
            }
        });
        dialogBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Buyer_residential.class));
            }
        });
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();
            }
        });
        alertDialog.show();
    }

    private void showCommercialDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.bottomSheetBackground));
        final View view =getLayoutInflater().inflate(R.layout.add_dialog,null);
       TextView dialoglicensor,dialogLicensee,dialogseller,dialogBuyer;
        ImageView close;

        view.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialoglicensor=view.findViewById(R.id.dialoglicensor);
        dialogLicensee=view.findViewById(R.id.dialogLicensee);
        dialogseller=view.findViewById(R.id.dialogSeller);
        dialogBuyer=view.findViewById(R.id.dialogBuyer);
        close=view.findViewById(R.id.imgclose);

        dialoglicensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Licensor_commercial.class));
            }
        });
        dialogLicensee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Licensee_Commercial.class));
            }
        });
        dialogseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Seller_commercial.class));
            }
        });
        dialogBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Buyer_commercial.class));
            }
        });
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.hide();
            }
        });
        alertDialog.show();
    }

    List<RecentEntriesModel> addValues() {

        RecentEntriesModel recentEntriesModel=new RecentEntriesModel("21-5-2021","Akash","Yugal sanjala","1","baner","office","Pune","9975019502","9876543210","2.5cr","okdfbdfbfbdsfbfidsbfhbfsdbfsbfudsfbsdufbsdiufsgbusdigusgusgudshguidsgudsush");
         RecentEntriesModel recentEntriesModel1=new RecentEntriesModel("21-5-2021","Akash","Yugal sanjala","1","baner","office","Pune","9975019502","9876543210","2.5cr","ok");

         recentEntriesModelList.add(recentEntriesModel);
         recentEntriesModelList.add(recentEntriesModel1);
        adapter.notifyDataSetChanged();

        return recentEntriesModelList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;

    }
}