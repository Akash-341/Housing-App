package com.example.realestate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.realestate.BaseUrl.AllUrl;
import com.example.realestate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seller_residential extends AppCompatActivity {

    AutoCompleteTextView type;
    EditText date,name,building,apt,area,price,mob1,mob2;
    LinearLayout linearLayout;
    Button save;
    boolean isAllFieldsChecked = false;
    final Calendar myCalendar= Calendar.getInstance();
    String myFormat="yyyy/MM/dd";
    SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_residential);
        this.setTitle("Residential:Seller");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        type=findViewById(R.id.typespinner);
        date=findViewById(R.id.etdate);
        name=findViewById(R.id.etname);
        building=findViewById(R.id.etbname);
        apt=findViewById(R.id.etaptno);
        area=findViewById(R.id.etarea);
        price=findViewById(R.id.etprice);
        mob1=findViewById(R.id.etmob1);
        mob2=findViewById(R.id.etmob2);
        save=findViewById(R.id.btnsave);


        int year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);

        List<String> types=new ArrayList<>();
        types.add("1BHK");
        types.add("2BHK");
        types.add("3BHK");

        ArrayAdapter<String> typeadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, types);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeadapter);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Seller_residential.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);
                        date.setText(dateFormat.format(myCalendar.getTime()));
                    }
                }  , year, month, day
                );

                datePickerDialog.show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked=validation();
                if (isAllFieldsChecked) {
                    addnew(name.getText().toString(), mob1.getText().toString(), mob2.getText().toString(), type.getText().toString(), price.getText().toString(), apt.getText().toString(), building.getText().toString(), area.getText().toString(), date.getText().toString());
                }
                }
        });
    }

    private boolean validation() {

        if (name.getText().toString().isEmpty()){
            name.setError("invalid name");
            return false;
        }
        if (building.getText().toString().isEmpty()){
            building.setError("invalid name");
           return false;
        }
        if (price.getText().toString().isEmpty()){
            price.setError("invalid name");
            return false;
        }
        if (apt.getText().toString().isEmpty()){
            apt.setError("invalid name");
            return false;
        }
        if (area.getText().toString().isEmpty()){
            area.setError("invalid name");
            return false;
        }
        if (mob1.getText().length()!=10){
            mob1.setError("incorrect number");
            return false;
        }
        if (mob2.getText().length()!=10){
            mob2.setError("incorrect number");
            return false;
        }
        if(type.getText().toString().isEmpty()){
            type.setError("Invalid Choice");
            return false;
        }
        if(date.getText().toString().isEmpty()){
            date.setError("Invalid Choice");
            return false;
        }


        return true;
    }

    private void addnew(String name,String mob1,String mob2,String type,String price,String apt,String area,String building,String date) {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllUrl.SELLER_RESIDENTIAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e( "onResponse: ",response );
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject res = jsonObject.getJSONObject("response");
                            Log.e( "JsonObject ",jsonObject.toString() );
                            Toast.makeText(Seller_residential.this,res.getString("Msg"),Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                            Log.i("VOLLEY", response);
                        } catch (JSONException e) {
                            pDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Seller_residential.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
                Log.i("VOLLEY", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("Name",name);
                params.put("MobileNo_1", mob1);
                params.put("MobileNo_2", mob2);
                params.put("Type", type);
                params.put("Price",price);
                params.put("AptNo",apt);
                params.put("BuildingName",building);
                params.put("Area",area);
                params.put("Date", date);


                return params;
            }
        };

        Volley.newRequestQueue(getApplication().getApplicationContext()).add(stringRequest);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}