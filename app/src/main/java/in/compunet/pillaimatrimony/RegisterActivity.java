package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.DataRequest;
import in.compunet.pillaimatrimony.model.RegisterRequest;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class RegisterActivity extends AppCompatActivity {
    Spinner spr_profile_created_for;
    Toolbar toolbar;
    Button btn_login,btn_continue;
    Activity activity;
    EditText edt_name,edt_mobile,edt_password,edt_email;
    RadioButton radio_male,radio_female;
    TextView txt_dob,txt_terms_conditions;
    int cYear,cMonth,cDay;
    DatePickerDialog datePickerDialog;
    LinearLayout root_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity=RegisterActivity.this;
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_login=toolbar.findViewById(R.id.btn_login);
        btn_continue=findViewById(R.id.btn_continue);
        spr_profile_created_for=findViewById(R.id.spr_profile_created_for);
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_password=findViewById(R.id.edt_password);
        txt_dob=findViewById(R.id.txt_dob);
        radio_male=findViewById(R.id.radio_male);
        radio_female=findViewById(R.id.radio_female);

        txt_terms_conditions=findViewById(R.id.txt_terms_conditions);

        root_view=findViewById(R.id.root_view);


        txt_terms_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,TermsConditionsActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(activity,LoginActivity.class));
                finish();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_name.getText().toString().isEmpty()){
                    Snackbar.make(root_view,"Name must not be empty",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"Name must not be empty",Snackbar.LENGTH_LONG).show();
                }
                else if(!radio_male.isChecked()&&!radio_female.isChecked()){
                    Snackbar.make(root_view,"Select any one gender",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"Select any one gender",Snackbar.LENGTH_LONG).show();
                }
                else if(txt_dob.getText().toString().isEmpty()){
                    Snackbar.make(root_view,"DOB must not be empty",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"DOB must not be empty",Snackbar.LENGTH_LONG).show();
                }
                else if(edt_mobile.getText().toString().isEmpty()||!Patterns.PHONE.matcher(edt_mobile.getText().toString()).matches()){
                    Snackbar.make(root_view,"Please Enter valid Mobile Number",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"Please Enter valid Mobile Number",Snackbar.LENGTH_LONG).show();
                }
                else if(edt_email.getText().toString().isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches()){
                    Snackbar.make(root_view,"Please Enter valid Email ID",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"Please Enter valid Email ID",Snackbar.LENGTH_LONG).show();
                }
                else if(edt_password.getText().toString().length()<8 &&!isValidPassword(edt_password.getText().toString())){
                    Snackbar.make(root_view,"Password must contain mix of upper and lower case letters as well as digits and one special character (minimum 8)",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(activity,"Password must contain mix of upper and lower case letters as well as digits and one special character (minimum 8)",Snackbar.LENGTH_LONG).show();
                }
                else{
                    apiCallRegistration();
                }

            }
        });


        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18);
        cYear = c.get(Calendar.YEAR);
        cMonth = c.get(Calendar.MONTH);
        cDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txt_dob.setText(dateDisplayFormat(year+"-"+(monthOfYear+1)+"-"+dayOfMonth));//dateDisplayFormat()
                    }}, cYear, cMonth, cDay);



        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        ArrayList<String> profileCreatedForList = new ArrayList<String>();
        profileCreatedForList.add("Own");
        profileCreatedForList.add("Brother / Sister");
        profileCreatedForList.add("Son / Daughter");
        profileCreatedForList.add("Friends / Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profileCreatedForList);
        spr_profile_created_for.setAdapter(dataAdapter);
    }


    String dateDisplayFormat(String strDate){

        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(strDate);

                strDate = fmt.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strDate;
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void apiCallRegistration() {
        String url = Constants.REGISTER_URL;
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setProfileCreatedFor(spr_profile_created_for.getSelectedItem().toString());
        registerRequest.setName(edt_name.getText().toString());
        registerRequest.setDOB(txt_dob.getText().toString());
        registerRequest.setEmail(edt_email.getText().toString());
        registerRequest.setGender(radio_male.isChecked()?radio_male.getText().toString():radio_female.getText().toString());
        registerRequest.setMobile(edt_mobile.getText().toString());
        registerRequest.setPassword(edt_password.getText().toString());
        registerRequest.setFireBaseId("00dsfewrfs"+ edt_mobile.getText().toString());
        DataRequest dataRequest=new DataRequest();
        dataRequest.setRegisterRequest(registerRequest);
        Gson gson=new Gson();
        String data=gson.toJson(registerRequest);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.DATA,data);
        RequestQueue mRequestQueue = Volley.newRequestQueue(activity);
        GSONDateRequest<ResultMessage> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, url,
                ResultMessage.class,params,
                createRequestSuccessListener(),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<ResultMessage> createRequestSuccessListener() {

        return new Response.Listener<ResultMessage>() {

            @Override
            public void onResponse(ResultMessage response) {
                Log.e("success",response.toString());
                if(response.getResult()!=null) {
                    if (response.getResult().equals("1") || response.getResult().equals("2")) {
                        Toast.makeText(activity, response.getMessage(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(activity, LoginActivity.class));
                        finish();
                    }
                    if (response.getResult().equals("-1")) {
                        Snackbar.make(root_view, response.getMessage(), Snackbar.LENGTH_LONG).show();
                        Toast.makeText(activity, response.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ResponseListener",error.getMessage());
            }
        };
    }

}
