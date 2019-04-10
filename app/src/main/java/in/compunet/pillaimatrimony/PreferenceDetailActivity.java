package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import in.compunet.pillaimatrimony.adapter.RecyclerCheckboxAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.database.DataHelper;
import in.compunet.pillaimatrimony.model.CheckboxModel;
import in.compunet.pillaimatrimony.model.ResultGetPreference;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.model.basic_details.HeightTable;
import in.compunet.pillaimatrimony.model.basic_details.PreferenceDetails;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.utils.Utils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class PreferenceDetailActivity extends AppCompatActivity {
    Button btn_update1,btn_update2,btn_update3,btn_update4,btn_update5;
    LinearLayout root_view;
    ListView list_view;
    EditText edt_age_from,edt_age_to, edt_height_from,edt_height_to, edt_marital_status,
            edt_mother_tongue,edt_physical_status, edt_eating_habits, edt_drinking_habits, edt_smoking_habits,

            edt_religion,edt_caste,edt_sub_caste,edt_star, edt_chevai_dosham,

            edt_education, edt_occupation, edt_annual_income_from,edt_annual_income_to,

            edt_country, edt_state_residing, edt_city_residing, edt_citizenship,

            edt_partner_description;

    ArrayAdapter arrayAdapter;
    EditText viewSpinnerClicked;

    ArrayList<String> valueArrayList = new ArrayList<>();
    ArrayList<String> valueArrayListCopy = new ArrayList<>();

    DrawerLayout drawer;
    NavigationView navigationView;

    CardView card_view1, card_view2, card_view3, card_view4, card_view5;
    LinearLayout l_layout1,l_layout2,l_layout3,l_layout4,l_layout5;
    EditText auto_complete_search;
    DataHelper dataHelper;
    Activity activity;
    private ArrayList<HeightTable> heightTableArrayList = new ArrayList<>();
    String TAG="DetailActivity";
    String strFlag="";
    RecyclerView recycler_view;
    RecyclerCheckboxAdapter recyclerCheckboxAdapter;
    ArrayList<CheckboxModel> checkboxModelArrayList= new ArrayList<>();
    LinearLayout layout_recycler_view;
    Button btn_submit;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_detail);
        activity = PreferenceDetailActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        list_view = findViewById(R.id.list_view);

        recycler_view=findViewById(R.id.recycler_view);

        dataHelper = new DataHelper(activity);

        btn_submit=findViewById(R.id.btn_submit);

        root_view = findViewById(R.id.root_view);
        layout_recycler_view=findViewById(R.id.layout_recycler_view);

        auto_complete_search = findViewById(R.id.auto_complete_search);

        edt_age_from = findViewById(R.id.edt_age_from);
        edt_age_to = findViewById(R.id.edt_age_to);
        edt_height_from = findViewById(R.id.edt_height_from);
        edt_height_to = findViewById(R.id.edt_height_to);
        edt_marital_status=findViewById(R.id.edt_marital_status);
        edt_physical_status = findViewById(R.id.edt_physical_status);
        edt_mother_tongue = findViewById(R.id.edt_mother_tongue);
        edt_eating_habits = findViewById(R.id.edt_eating_habits);
        edt_drinking_habits = findViewById(R.id.edt_drinking_habits);
        edt_smoking_habits = findViewById(R.id.edt_smoking_habits);

        edt_religion= findViewById(R.id.edt_religion);
        edt_caste= findViewById(R.id.edt_caste);
        edt_sub_caste= findViewById(R.id.edt_sub_caste);
        edt_star=findViewById(R.id.edt_star);
        edt_chevai_dosham= findViewById(R.id.edt_chevai_dosham);

        edt_education=findViewById(R.id.edt_education);
        edt_occupation = findViewById(R.id.edt_occupation);
        edt_annual_income_from=findViewById(R.id.edt_annual_income_from);
        edt_annual_income_to=findViewById(R.id.edt_annual_income_to);

        edt_country = findViewById(R.id.edt_country);
        edt_state_residing = findViewById(R.id.edt_state_residing);
        edt_city_residing = findViewById(R.id.edt_city_residing);
        edt_citizenship = findViewById(R.id.edt_citizenship);

        edt_partner_description=findViewById(R.id.edt_partner_description);

        btn_update1= findViewById(R.id.btn_update1);
        btn_update2= findViewById(R.id.btn_update2);
        btn_update3= findViewById(R.id.btn_update3);
        btn_update4= findViewById(R.id.btn_update4);
        btn_update5= findViewById(R.id.btn_update5);

        card_view1 = findViewById(R.id.card_view1);
        card_view2 = findViewById(R.id.card_view2);
        card_view3 = findViewById(R.id.card_view3);
        card_view4 = findViewById(R.id.card_view4);
        card_view5 = findViewById(R.id.card_view5);

        l_layout1 = findViewById(R.id.l_layout1);
        l_layout2 = findViewById(R.id.l_layout2);
        l_layout3 = findViewById(R.id.l_layout3);
        l_layout4 = findViewById(R.id.l_layout4);
        l_layout5 = findViewById(R.id.l_layout5);


        ResultGetPreference resultGetPreference=(ResultGetPreference) getIntent().getSerializableExtra("preference_details");

        if(resultGetPreference!=null){
            setValue(resultGetPreference.getPreferenceDetailsArrayList().get(0));
            setId(resultGetPreference.getPreferenceDetailsArrayList().get(0));
        }

        if (getIntent().getStringExtra("card_number") != null) {
            strFlag="up";
            switch (getIntent().getStringExtra("card_number")) {
                case "1":
                    l_layout1.setVisibility(View.VISIBLE);
                    break;
                case "2":
                    l_layout2.setVisibility(View.VISIBLE);
                    break;
                case "3":
                    l_layout3.setVisibility(View.VISIBLE);
                    break;
                case "4":
                    l_layout4.setVisibility(View.VISIBLE);
                    break;
                case "5":
                    l_layout5.setVisibility(View.VISIBLE);
                    break;
            }

        }
        else{
            strFlag="new";
            switch (SharedPreferenceUtils.getCount(activity)) {
                case 8:
                    l_layout1.setVisibility(View.VISIBLE);
                    break;
                case 9:
                    l_layout2.setVisibility(View.VISIBLE);
                    break;
                case 10:
                    l_layout3.setVisibility(View.VISIBLE);
                    break;
                case 11:
                    l_layout4.setVisibility(View.VISIBLE);
                    break;
                case 12:
                    l_layout5.setVisibility(View.VISIBLE);
                    break;
            }
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<CheckboxModel> checkboxModelTempArrayList=recyclerCheckboxAdapter.getCheckboxModelArrayList();
                String strValue="",strId="";
                for(int i=0;i<checkboxModelTempArrayList.size();i++){
                    if(checkboxModelTempArrayList.get(i).isTitleChecked()){
                            strValue+=checkboxModelTempArrayList.get(i).getStrTitle()+",";
                            strId+=i+",";
                    }
                    if(strValue.isEmpty()){
                        strValue= " ";
                        strId=" ";
                    }
                }
                Log.e("value",strValue);
                Log.e("position",strId);
                if (viewSpinnerClicked != null) {
                viewSpinnerClicked.setText(strValue.substring(0,strValue.length()-1));
                viewSpinnerClicked.setTransitionName(strId.substring(0,strId.length()-1));
                auto_complete_search.setText("");
                }
                drawer.closeDrawer(GravityCompat.END);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recycler_view.setLayoutManager(linearLayoutManager);
        recyclerCheckboxAdapter = new RecyclerCheckboxAdapter(activity, checkboxModelArrayList);
        recycler_view.setAdapter(recyclerCheckboxAdapter);

        arrayAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, valueArrayList);
        list_view.setAdapter(arrayAdapter);

        auto_complete_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                String strSearch = cs.toString().toLowerCase();
                valueArrayList.clear();
                if (strSearch.isEmpty()) {
                    valueArrayList.addAll(0, valueArrayListCopy);
                } else {
                    for (String value : valueArrayListCopy) {
                        if (value.toLowerCase().contains(strSearch)) {
                            valueArrayList.add(value);
                        }
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.closeDrawer(GravityCompat.END);
                if (viewSpinnerClicked != null) {
                    viewSpinnerClicked.setText(valueArrayList.get(position));
                    viewSpinnerClicked.setTransitionName(Integer.toString(valueArrayListCopy.indexOf(valueArrayList.get(position)) + 1));
                    auto_complete_search.setText("");
                }
                if(viewSpinnerClicked==edt_state_residing){
                    edt_city_residing.setText("");
                    edt_city_residing.setTransitionName("");
                }
            }
        });




        edt_age_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                for (int i = 18; i < 61; i += 1) {
                    valueArrayList.add(i + "Yrs");
                }
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_age_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                for (int i = 18; i < 61; i += 1) {
                    valueArrayList.add(i + "Yrs");
                }
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_height_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();

                for (int i = 4; i < 7; i += 1) {
                    for (int j = 1; j <= 11; j++) {

                        valueArrayList.add(i + "Ft " + j + " In");

                    }
                }

                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_height_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();

                        for (int i = 4; i < 7; i += 1) {
                            for (int j = 1; j <= 11; j++) {

                                valueArrayList.add(i + "Ft " + j + " In");

                            }
                        }

                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });



        edt_marital_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("Any"));
                checkboxModelArrayList.add(new CheckboxModel("Never Married"));
                checkboxModelArrayList.add(new CheckboxModel("Widower"));
                checkboxModelArrayList.add(new CheckboxModel("Divorced"));
                checkboxModelArrayList.add(new CheckboxModel("Awaiting Divorce"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);

                viewSpinnerClicked = (EditText) v;
                recyclerCheckboxAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });


        edt_mother_tongue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_recycler_view.setVisibility(View.GONE);
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"language.json"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);

                        valueArrayList.add(jsonObj.getString("nativeName")+ " " + jsonObj.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });


        edt_physical_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("DoesN't Matter");
                valueArrayList.add("Normal");
                valueArrayList.add("Physically Challenged");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });



        edt_eating_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("DoesN't Matter"));
                checkboxModelArrayList.add(new CheckboxModel("Vegetarian"));
                checkboxModelArrayList.add(new CheckboxModel("Non-Vegetarian"));
                checkboxModelArrayList.add(new CheckboxModel("Eggetarian"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_drinking_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("DoesN't Matter"));
                checkboxModelArrayList.add(new CheckboxModel("Non - Drinker"));
                checkboxModelArrayList.add(new CheckboxModel("Light/Social Drinker"));
                checkboxModelArrayList.add(new CheckboxModel("Regular Drinker"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_smoking_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("DoesN't Matter"));
                checkboxModelArrayList.add(new CheckboxModel("Non - Smoker"));
                checkboxModelArrayList.add(new CheckboxModel("Light/Social Smoker"));
                checkboxModelArrayList.add(new CheckboxModel("Regular Smoker"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });


        edt_sub_caste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valueArrayList.clear();
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("Any"));
                checkboxModelArrayList.add(new CheckboxModel("Arunattu Vellalar"));
                checkboxModelArrayList.add(new CheckboxModel("Thirunelveli Pillaimar"));
                checkboxModelArrayList.add(new CheckboxModel("Saiva Pillaimar"));
                checkboxModelArrayList.add(new CheckboxModel("Thanjavur Pillaimar(Veg)"));
                checkboxModelArrayList.add(new CheckboxModel("Thanjavur Pillaimar(Non - Veg)"));
                checkboxModelArrayList.add(new CheckboxModel("Karkatha Pillaimar(Veg)"));
                checkboxModelArrayList.add(new CheckboxModel("Karkatha Pillaimar(Non - Veg)"));
                checkboxModelArrayList.add(new CheckboxModel("Kongu Vellalar"));
                checkboxModelArrayList.add(new CheckboxModel("Chozhia Vellalar"));
                checkboxModelArrayList.add(new CheckboxModel("Thuluva Vellalar"));
                checkboxModelArrayList.add(new CheckboxModel("Veerakodi Vellalar"));
                checkboxModelArrayList.add(new CheckboxModel("Srilankan Vellalar"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("Any"));
                checkboxModelArrayList.add(new CheckboxModel("Ashwini"));
                checkboxModelArrayList.add(new CheckboxModel("Bharani"));
                checkboxModelArrayList.add(new CheckboxModel("Karthigai"));
                checkboxModelArrayList.add(new CheckboxModel("Rohini"));
                checkboxModelArrayList.add(new CheckboxModel("Mirugasheerisam"));
                checkboxModelArrayList.add(new CheckboxModel("Thiruvathirai"));
                checkboxModelArrayList.add(new CheckboxModel("Punarpoosam"));
                checkboxModelArrayList.add(new CheckboxModel("Poosam"));
                checkboxModelArrayList.add(new CheckboxModel("Ayilyam"));
                checkboxModelArrayList.add(new CheckboxModel("Magam"));
                checkboxModelArrayList.add(new CheckboxModel("Pooram"));
                checkboxModelArrayList.add(new CheckboxModel("Utharam"));
                checkboxModelArrayList.add(new CheckboxModel("Hastham"));
                checkboxModelArrayList.add(new CheckboxModel("Chithirai"));
                checkboxModelArrayList.add(new CheckboxModel("Swathi"));
                checkboxModelArrayList.add(new CheckboxModel("Visakam"));
                checkboxModelArrayList.add(new CheckboxModel("Anusham"));
                checkboxModelArrayList.add(new CheckboxModel("Ketta"));
                checkboxModelArrayList.add(new CheckboxModel("Moolam"));
                checkboxModelArrayList.add(new CheckboxModel("Pooradam"));
                checkboxModelArrayList.add(new CheckboxModel("Uthiram"));
                checkboxModelArrayList.add(new CheckboxModel("Thiruvonam"));
                checkboxModelArrayList.add(new CheckboxModel("Avittam"));
                checkboxModelArrayList.add(new CheckboxModel("Shathayam"));
                checkboxModelArrayList.add(new CheckboxModel("Pooratathi"));
                checkboxModelArrayList.add(new CheckboxModel("Uttaratathi"));
                checkboxModelArrayList.add(new CheckboxModel("Revathi"));

                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_chevai_dosham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("DoesN't Matter");
                valueArrayList.add("Yes");
                valueArrayList.add("No");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"degree.json"));
                    checkboxModelArrayList.add(new CheckboxModel("Any"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        checkboxModelArrayList.add(new CheckboxModel(jsonArray.get(i).toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();
                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"jobs.json"));
                    checkboxModelArrayList.add(new CheckboxModel("Any"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        checkboxModelArrayList.add(new CheckboxModel(jsonArray.get(i).toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();

                viewSpinnerClicked = (EditText) v;
                openDrawer();
            }
        });

        edt_annual_income_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("Any");
                valueArrayList.add("Less than 50 thousand");
                valueArrayList.add("Rs 1 Lakh");
                for (int i = 2; i < 10; i += 1) {
                    valueArrayList.add("Rs "+i + " Lakhs");
                }
                for (int i = 10; i < 100; i += 10) {
                    valueArrayList.add("Rs " + i + " Lakhs");
                }
                valueArrayList.add("1 Crore");
                valueArrayList.add("1 Crore");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_annual_income_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("Any");
                valueArrayList.add("Less than 50 thousand");
                valueArrayList.add("Rs 1 Lakh");
                for (int i = 2; i < 10; i += 1) {
                    valueArrayList.add("Rs "+i + " Lakhs");
                }
                for (int i = 10; i < 100; i += 10) {
                    valueArrayList.add("Rs " + i + " Lakhs");
                }
                valueArrayList.add("1 Crore");
                valueArrayList.add("1 Crore");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("India");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_state_residing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"states_districts.json"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        valueArrayList.add(jsonObj.getString("state"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_city_residing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"states_districts.json"));
                    if(edt_state_residing.getTransitionName()!=null&& !edt_state_residing.getTransitionName().isEmpty() ){
                        JSONObject jsonObj = jsonArray.getJSONObject(Integer.parseInt(edt_state_residing.getTransitionName())-1);
                        JSONArray jsonArray1= jsonObj.getJSONArray("districts");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            valueArrayList.add(jsonArray1.get(i).toString());
                        }
                    }
                    else{
                        Toast.makeText(activity,"Please select state first",Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(root_view , "Please select state first ", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        return;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_citizenship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_recycler_view.setVisibility(View.GONE);
                valueArrayList.clear();
                valueArrayList.add("India");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        btn_update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (showErrorMessage(edt_age_from) && showErrorMessage(edt_age_to) &&
                        showErrorMessage(edt_height_from) && showErrorMessage(edt_height_to) &&
                        showErrorMessage(edt_marital_status) && showErrorMessage(edt_mother_tongue) && showErrorMessage(edt_physical_status) &&
                        showErrorMessage(edt_eating_habits) && showErrorMessage(edt_drinking_habits) &&
                        showErrorMessage(edt_smoking_habits)) {

                    PreferenceDetails preferenceDetails = new PreferenceDetails();
                    preferenceDetails.setFlag(strFlag);
                    preferenceDetails.setKey("basicPref");
                    preferenceDetails.setUserId(SharedPreferenceUtils.getUserId(activity));

                    preferenceDetails.setAge(edt_age_from.getText().toString()+","+edt_age_to.getText().toString());
                    preferenceDetails.setHeight(edt_height_from.getText().toString()+","+edt_height_to.getText().toString());
                    preferenceDetails.setHeightId(edt_height_from.getTransitionName()+","+edt_height_to.getTransitionName());

                    preferenceDetails.setAgeFrom(edt_age_from.getText().toString());
                    preferenceDetails.setAgeTo(edt_age_to.getText().toString());
                    preferenceDetails.setHeightFrom(edt_height_from.getText().toString());
                    preferenceDetails.setHeightTo(edt_height_to.getText().toString());

                    preferenceDetails.setAgeFromId(edt_age_from.getTransitionName());
                    preferenceDetails.setAgeToId(edt_age_to.getTransitionName());
                    preferenceDetails.setHeightFromId(edt_height_from.getTransitionName());
                    preferenceDetails.setHeightToId(edt_height_to.getTransitionName());

                    preferenceDetails.setMaritalStatus(edt_marital_status.getText().toString());
                    preferenceDetails.setMaritalStatusId(edt_marital_status.getTransitionName());
                    preferenceDetails.setMotherTongue(edt_mother_tongue.getText().toString());
                    preferenceDetails.setMotherTongueId(edt_mother_tongue.getTransitionName());
                    preferenceDetails.setMotherToungue(edt_mother_tongue.getText().toString());
                    preferenceDetails.setMotherToungueId(edt_mother_tongue.getTransitionName());

                    preferenceDetails.setPhysicalStatus(edt_physical_status.getText().toString());
                    preferenceDetails.setPhysicalStatusId(edt_physical_status.getTransitionName());

                    preferenceDetails.setEatingHabbits(edt_eating_habits.getText().toString());
                    preferenceDetails.setEatingHabbitsId(edt_eating_habits.getTransitionName());
                    preferenceDetails.setDrinkingHabbits(edt_drinking_habits.getText().toString());
                    preferenceDetails.setDrinkingHabbitsId(edt_drinking_habits.getTransitionName());
                    preferenceDetails.setSmokingHabbits(edt_smoking_habits.getText().toString());
                    preferenceDetails.setSmokingHabbitsId(edt_smoking_habits.getTransitionName());

                    apiCall(Constants.PREFERENCE_INSERT, preferenceDetails, l_layout2, l_layout1);
                }
            }
        });


        btn_update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_religion)&&showErrorMessage(edt_caste)&&
                        showErrorMessage(edt_sub_caste)&&showErrorMessage(edt_star)&&
                        showErrorMessage(edt_chevai_dosham)) {
                    PreferenceDetails preferenceDetails = new PreferenceDetails();
                    preferenceDetails.setFlag(strFlag);
                    preferenceDetails.setKey("religionPref");
                    preferenceDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    preferenceDetails.setReligion(edt_religion.getText().toString());
                    preferenceDetails.setCaste(edt_caste.getText().toString());
                    preferenceDetails.setSubCaste(edt_sub_caste.getText().toString());
                    preferenceDetails.setSubCasteId(edt_sub_caste.getTransitionName());
                    preferenceDetails.setStar(edt_star.getText().toString());
                    preferenceDetails.setStarId(edt_star.getTransitionName());
                    preferenceDetails.setDhosham(edt_chevai_dosham.getText().toString());
                    preferenceDetails.setDhoshamId(edt_chevai_dosham.getTransitionName());
                    apiCall(Constants.PREFERENCE_INSERT, preferenceDetails, l_layout3, l_layout2);
                }
            }
        });

        btn_update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(showErrorMessage(edt_education)&&showErrorMessage(edt_occupation)&&
                        showErrorMessage(edt_annual_income_from)&&showErrorMessage(edt_annual_income_to)){

                    PreferenceDetails preferenceDetails = new PreferenceDetails();
                    preferenceDetails.setFlag(strFlag);
                    preferenceDetails.setKey("educationPref");
                    preferenceDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    preferenceDetails.setEducationCategory(edt_education.getText().toString());
                    preferenceDetails.setEducationCategoryId(edt_education.getTransitionName());
                    preferenceDetails.setOccupation(edt_occupation.getText().toString());
                    preferenceDetails.setOccupationId(edt_occupation.getTransitionName());
                    preferenceDetails.setAnnualIncome(edt_annual_income_from.getText().toString()+","+edt_annual_income_to.getText().toString());
                    preferenceDetails.setAnnualIncomeId(edt_annual_income_from.getTransitionName()+","+edt_annual_income_to.getTransitionName());

                    apiCall(Constants.PREFERENCE_INSERT,preferenceDetails,l_layout4,l_layout3);
                }
            }
        });

        btn_update4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_country)&&showErrorMessage(edt_state_residing)&&
                        showErrorMessage(edt_city_residing)&&showErrorMessage(edt_citizenship)) {

                    PreferenceDetails preferenceDetails = new PreferenceDetails();
                    preferenceDetails.setFlag(strFlag);
                    preferenceDetails.setKey("locationPref");
                    preferenceDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    preferenceDetails.setCountry(edt_country.getText().toString());
                    preferenceDetails.setCountryId(edt_country.getTransitionName());
                    preferenceDetails.setState(edt_state_residing.getText().toString());
                    preferenceDetails.setStateId(edt_state_residing.getTransitionName());
                    preferenceDetails.setDistrict(edt_city_residing.getText().toString());
                    preferenceDetails.setDistrictId(edt_city_residing.getTransitionName());
                    preferenceDetails.setCitizenship(edt_citizenship.getText().toString());
                    preferenceDetails.setCitizenshipId(edt_citizenship.getTransitionName());

                    apiCall(Constants.PREFERENCE_INSERT,preferenceDetails,l_layout5,l_layout4);
                }

            }
        });

        btn_update5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_partner_description)) {

                    PreferenceDetails preferenceDetails = new PreferenceDetails();
                    preferenceDetails.setFlag(strFlag);
                    preferenceDetails.setKey("locationPref");
                    preferenceDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    preferenceDetails.setCountry(edt_country.getText().toString());
                   /* preferenceDetails.setCountryId(edt_country.getTransitionName());
                    preferenceDetails.setState(edt_state_residing.getText().toString());
                    preferenceDetails.setStateId(edt_state_residing.getTransitionName());
                    preferenceDetails.setDistrict(edt_city_residing.getText().toString());
                    preferenceDetails.setDistrictId(edt_city_residing.getTransitionName());
                    preferenceDetails.setCitizenship(edt_citizenship.getText().toString());
                    preferenceDetails.setCitizenshipId(edt_citizenship.getTransitionName());

                    apiCall(Constants.PREFERENCE_INSERT,preferenceDetails,l_layout5,l_layout4);*/
                   SharedPreferenceUtils.setCount(activity,SharedPreferenceUtils.getCount(activity)+1);
                   startActivity(new Intent(activity,BottomNavigationActivity.class));
                   finish();
                }

            }
        });
    }


    void openDrawer() {
        auto_complete_search.setText("");
        drawer.openDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            supportFinishAfterTransition();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yy");

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS + "Years / " + format.format(dob.getTime());
    }
    private void apiCall(String URL, PreferenceDetails preferenceDetails, LinearLayout view_layout, LinearLayout gone_layout) {
        progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Gson gson=new Gson();
        String data=gson.toJson(preferenceDetails);
        Log.e("DATA",data);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.DATA,data);
        RequestQueue mRequestQueue = Volley.newRequestQueue(activity);
        GSONDateRequest<ResultMessage> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, URL,
                ResultMessage.class,params,
                createRequestSuccessListener(view_layout,gone_layout),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<ResultMessage> createRequestSuccessListener(final LinearLayout view_layout, final LinearLayout gone_layout) {

        return new Response.Listener<ResultMessage>() {

            @Override
            public void onResponse(ResultMessage response) {
                progress.dismiss();
                if(strFlag.equals("up")) {
                    if (response.getResult() != null && response.getResult().equals("1")) {
                        Snackbar snackbar = Snackbar
                                .make(root_view, response.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        SharedPreferenceUtils.setIsUpdated(activity,true);
                        finish();
                    } else {
                        Toast.makeText(activity,response.getMessage(),Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(root_view, response.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
                else {
                    if (response.getResult() != null && (response.getResult().equals("1"))) {
                        view_layout.setVisibility(View.VISIBLE);
                        gone_layout.setVisibility(View.GONE);
                        SharedPreferenceUtils.setCount(activity,SharedPreferenceUtils.getCount(activity)+1);
                        Toast.makeText(activity,response.getMessage(),Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(root_view, response.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        if(SharedPreferenceUtils.getCount(activity)==13){
                            Toast.makeText(activity,response.getMessage(),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(activity,BottomNavigationActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(activity,response.getMessage(),Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(root_view, response.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Toast.makeText(activity,"Something went wrong please try again after some time ",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(root_view , "Something went wrong please try again after some time ", Snackbar.LENGTH_LONG);
                snackbar.show();
                Log.e("ResponseListener",error.getMessage());
            }
        };
    }

    boolean showErrorMessage(EditText editText) {
        String value=editText.getText().toString();
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(editText.getId()).getParent().getParent();
        String hint = textInputLayout.getHint().toString();
        if(value==null||value.isEmpty()){
            Toast.makeText(activity,hint+" must not be empty",Toast.LENGTH_LONG).show();
            Snackbar  snackbar = Snackbar
                    .make(root_view, hint+" must not be empty", Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }else{
            return true;
        }
    }

    void setValue(PreferenceDetails preferenceDetails){
        String strAge=preferenceDetails.getAge();
        String strHeight=preferenceDetails.getHeight();
        String strAnnualIncome=preferenceDetails.getAnnualIncome();

        if(strAge!=null&&!strAge.isEmpty()){
            List<String> ageList = Arrays.asList(strAge.split("-"));
            edt_age_from.setText(ageList.get(0));
            edt_age_to.setText(ageList.get(1));

        }
        if(strHeight!=null&&!strHeight.isEmpty()){
            List<String> heightList = Arrays.asList(strHeight.split("-"));
            edt_height_from.setText(heightList.get(0));
            edt_height_to.setText(heightList.get(1));
        }
        if(strAnnualIncome!=null&&!strAnnualIncome.isEmpty()){
            List<String> annualIncomeList = Arrays.asList(strAnnualIncome.split(","));
            edt_annual_income_from.setText(annualIncomeList.get(0));
            edt_annual_income_to.setText(annualIncomeList.get(1));

        }

        edt_marital_status.setText(preferenceDetails.getMaritalStatus());
        edt_mother_tongue.setText(preferenceDetails.getMotherToungue());
        edt_physical_status.setText(preferenceDetails.getPhysicalStatus());
        edt_eating_habits.setText(preferenceDetails.getEatingHabbits());
        edt_drinking_habits.setText(preferenceDetails.getDrinkingHabbits());
        edt_smoking_habits.setText(preferenceDetails.getSmokingHabbits());
        edt_religion.setText(preferenceDetails.getReligion());
        edt_caste.setText(preferenceDetails.getCaste());
        edt_sub_caste.setText(preferenceDetails.getSubCaste());
        edt_star.setText(preferenceDetails.getStar());
        edt_chevai_dosham.setText(preferenceDetails.getDhosham());

        edt_education.setText(preferenceDetails.getEducationCategory());
        edt_occupation.setText(preferenceDetails.getOccupation());

        edt_country.setText(preferenceDetails.getCountry());
        edt_state_residing.setText(preferenceDetails.getState());
        edt_city_residing.setText(preferenceDetails.getDistrict());
        edt_citizenship.setText(preferenceDetails.getCitizenship());

    }


    void setId(PreferenceDetails preferenceDetails){

        String strAge=preferenceDetails.getAgeId();
        String strHeight=preferenceDetails.getHeightId();
        String strAnnualIncome=preferenceDetails.getAnnualIncomeId();

        if(strAge!=null&&!strAge.isEmpty()){
            List<String> ageList = Arrays.asList(strAge.split("-"));
            edt_age_from.setTransitionName(ageList.get(0));
            edt_age_to.setTransitionName(ageList.get(1));

        }
        if(strHeight!=null&&!strHeight.isEmpty()){
            List<String> heightList = Arrays.asList(strHeight.split("-"));
            edt_height_from.setTransitionName(heightList.get(0));
            edt_height_to.setTransitionName(heightList.get(1));
        }
        if(strAnnualIncome!=null&&!strAnnualIncome.isEmpty()){
            List<String> annualIncomeList = Arrays.asList(strAnnualIncome.split(","));
            edt_annual_income_from.setTransitionName(annualIncomeList.get(0));
            edt_annual_income_to.setTransitionName(annualIncomeList.get(1));

        }

        edt_marital_status.setTransitionName(preferenceDetails.getMaritalStatusId());
        edt_mother_tongue.setTransitionName(preferenceDetails.getMotherToungueId());
        edt_physical_status.setTransitionName(preferenceDetails.getPhysicalStatusId());
        edt_eating_habits.setTransitionName(preferenceDetails.getEatingHabbitsId());
        edt_drinking_habits.setTransitionName(preferenceDetails.getDrinkingHabbitsId());
        edt_smoking_habits.setTransitionName(preferenceDetails.getSmokingHabbitsId());

        edt_sub_caste.setTransitionName(preferenceDetails.getSubCasteId());
        edt_star.setTransitionName(preferenceDetails.getStarId());
        edt_chevai_dosham.setTransitionName(preferenceDetails.getDhoshamId());

        edt_education.setTransitionName(preferenceDetails.getEducationCategoryId());
        edt_occupation.setTransitionName(preferenceDetails.getOccupationId());

        edt_country.setTransitionName(preferenceDetails.getCountryId());
        edt_state_residing.setTransitionName(preferenceDetails.getStateId());
        edt_city_residing.setTransitionName(preferenceDetails.getDistrictId());
        edt_citizenship.setTransitionName(preferenceDetails.getCitizenshipId());


    }
}
