package com.example.realestate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.realestate.BaseUrl.AllUrl;
import com.example.realestate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Licensee_Residential extends AppCompatActivity {

    AutoCompleteTextView location, type;
    EditText  name, mob1, mob2, remarks;
    Button save;
    TextView date;
    ImageView cal;
    LinearLayout linearLayout;
    final Calendar myCalendar = Calendar.getInstance();
    String myFormat = "yyyy/MM/dd";
    SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licensee_residential);

        this.setTitle("Residential:Licensee");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        location = findViewById(R.id.locspinner);
        type = findViewById(R.id.typespinner);

        date = findViewById(R.id.etdate);
        name = findViewById(R.id.etname);
        mob1 = findViewById(R.id.etmob1);
        mob2 = findViewById(R.id.etmob2);
        remarks = findViewById(R.id.etremark);
        save=findViewById(R.id.btnsave);


        int year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);


//        locations.add("pune");
//        locations.add("Mumbai");
//        locations.add("Thane");
//        locations.add("Lonavla");
//        locations.add("Satara");


        getlocation(String.valueOf(location));

        List<String> types = new ArrayList<>();
        types.add("1BHK");
        types.add("2BHK");
        types.add("3BHK");

        ArrayAdapter<String> typeadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, types);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeadapter);

        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Licensee_Residential.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);
                        date.setText(dateFormat.format(myCalendar.getTime()));
                    }
                }, year, month, day
                );

                datePickerDialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked=validation();
                if (isAllFieldsChecked) {
                    addnew(name.getText().toString(), location.getText().toString(), remarks.getText().toString(), mob1.getText().toString(), mob2.getText().toString(), type.getText().toString(), date.getText().toString());
                }
            }
        });
    }
    private boolean validation() {

        if (name.getText().toString().isEmpty()){
            name.setError("invalid name");
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
        if(location.getText().toString().isEmpty()){
            location.setError("Invalid Choice");
            return false;
        }

        return true;
    }
    private void getlocation(String Location){

        StringRequest stringRequest=new StringRequest(Request.Method.GET, AllUrl.GET_LOCATION_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){

                    Log.e( "onResponse: ",response );
                    ArrayList<String>locations=new ArrayList<>();
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray locationArray=jsonObject.getJSONArray("LocationModelList");
                        for (int i=0;i<locationArray.length();i++){

                            JSONObject item=locationArray.getJSONObject(i);
                            String loc=item.getString("Location");
                            Log.e("onResponse: ",loc );
                            locations.add(loc);

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(Licensee_Residential.this, android.R.layout.simple_list_item_1, locations);
                        location.setAdapter(arrayAdapter);
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

    private void addnew(String name,String location,String remarks,String mob1,String mob2,String type,String date) {

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AllUrl.LICENSEE_RESIDENTIAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse: ", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject res=jsonObject.getJSONObject("response");
                                Toast.makeText(Licensee_Residential.this,res.getString("Msg"), Toast.LENGTH_SHORT).show();

                                Log.e("JsonObject ", res.toString());



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

                Toast.makeText(Licensee_Residential.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
                Log.e("VOLLEY", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("Name",name);
                params.put("Location",location);
                params.put("MobileNo_1",mob1);
                params.put("MobileNo_2",mob2 );
                params.put("Type", type);
                params.put("Remarks",remarks);
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