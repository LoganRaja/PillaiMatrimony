package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.LoginRequest;
import in.compunet.pillaimatrimony.model.LoginResult;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_login,btn_register,btn_register_toolbar;
    EditText edt_mobile,edt_password;
    TextView txt_forgot_password;
    Activity activity;
    LinearLayout root_view;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity=LoginActivity.this;
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_register=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);
        btn_register_toolbar=toolbar.findViewById(R.id.btn_register_toolbar);
        root_view=findViewById(R.id.root_view);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_password=findViewById(R.id.edt_password);


        btn_register_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,RegisterActivity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail=edt_mobile.getText().toString().trim();
                String strPassword=edt_password.getText().toString().trim();
                if(edt_mobile.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(root_view , "Invalid Mobile number / Email ID", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(strPassword.isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(root_view , "Password must not be empty", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else{
                   apiCallLogin();
                }
            }
        });


    }

    private void apiCallLogin() {
        progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String url = Constants.LOGIN_URL;
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUser(edt_mobile.getText().toString());
        loginRequest.setPassword(edt_password.getText().toString());
        Gson gson=new Gson();
        String data=gson.toJson(loginRequest);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.DATA,data);
        RequestQueue mRequestQueue = Volley.newRequestQueue(activity);
        GSONDateRequest<LoginResult> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, url,
                LoginResult.class,params,
                createRequestSuccessListener(),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<LoginResult> createRequestSuccessListener() {

        return new Response.Listener<LoginResult>() {

            @Override
            public void onResponse(LoginResult response) {
                progress.dismiss();
                Log.e("success",response.toString());
                if(response.getResult().equals("1")){
                    SharedPreferenceUtils.setUserId(activity,response.getId());
                    SharedPreferenceUtils.setUserName(activity,response.getName());
                    SharedPreferenceUtils.setGender(activity,response.getGender());
                    SharedPreferenceUtils.setStarId(activity,response.getStarId());
                    SharedPreferenceUtils.setCount(activity,response.getRegCount()==null||response.getRegCount().isEmpty()?0:Integer.parseInt(response.getRegCount()));
                  if(SharedPreferenceUtils.getCount(activity)<8){
                        startActivity(new Intent(activity,DetailActivity.class));
                        finish();
                    }
                    else{
                      if(SharedPreferenceUtils.getCount(activity)<12) {
                          startActivity(new Intent(activity, PreferenceDetailActivity.class));
                          finish();
                      }
                      else {
                          startActivity(new Intent(activity, BottomNavigationActivity.class));
                          finish();
                      }
                    }
                    Toast.makeText(activity, response.getMsg(), Toast.LENGTH_LONG).show();
                    /*Intent intent=new Intent(activity,ProfileActivity.class);
                    intent.putExtra("name",response.getName());
                    startActivity(intent);*/
                }
                else{
                    Snackbar snackbar = Snackbar
                            .make(root_view , response.getMsg(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Toast.makeText(activity, response.getMsg(), Toast.LENGTH_LONG).show();
                }

            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Snackbar snackbar = Snackbar
                        .make(root_view , "Something went wrong please try again after some time ", Snackbar.LENGTH_LONG);
                snackbar.show();
                Log.e("ResponseListener",error.getMessage());
            }
        };
    }




}
