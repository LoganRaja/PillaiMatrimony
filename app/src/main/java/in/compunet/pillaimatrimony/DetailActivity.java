package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import in.compunet.pillaimatrimony.adapter.RecyclerCheckboxAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.database.DataHelper;
import in.compunet.pillaimatrimony.model.CheckboxModel;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.model.basic_details.HeightTable;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.utils.Utils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class DetailActivity extends AppCompatActivity {
    Button btn_update1,btn_update2,btn_update3,btn_update4,btn_update5,btn_update6,btn_update7,btn_update8;
    LinearLayout root_view;
    ListView list_view;
    EditText edt_own_words, edt_name, edt_age, edt_height, edt_weight, edt_marital_status, edt_no_of_children,
            edt_body_type, edt_complexion, edt_physical_status, edt_mother_tongue,
            edt_eating_habits, edt_drinking_habits, edt_smoking_habits,
            edt_religion,edt_caste,edt_sub_caste,edt_gothram,edt_star,
            edt_raasi,edt_having_dosham,edt_specify_dosham,
            edt_highest_education, edt_college_name, edt_education_details, edt_occupation, edt_occupation_details,
            edt_employed_in, edt_annual_income,
            edt_country, edt_state, edt_city, edt_citizenship,
            edt_family_value, edt_family_type, edt_family_status, edt_father_occupation, edt_mother_occupation,
            edt_brothers, edt_sisters,
            edt_about_family, edt_hobbies, edt_interests;
    TextInputLayout til_no_of_children,til_specify_dosham;
    ArrayAdapter arrayAdapter;
    EditText viewSpinnerClicked;

    ArrayList<String> valueArrayList = new ArrayList<>();
    ArrayList<String> valueArrayListCopy = new ArrayList<>();

    DrawerLayout drawer;
    NavigationView navigationView;
    int cYear, cMonth, cDay;
    DatePickerDialog datePickerDialog;
    CardView card_view1, card_view2, card_view3, card_view4, card_view5, card_view6, card_view7, card_view8;

    LinearLayout l_layout1,l_layout2,l_layout3,l_layout4,l_layout5,l_layout6,l_layout7,l_layout8;

    EditText auto_complete_search;
    DataHelper dataHelper;
    Activity activity;
    ProgressDialog progress;
    private ArrayList<HeightTable> heightTableArrayList = new ArrayList<>();
    String TAG="DetailActivity";
    String strAge="";
    RecyclerView recycler_view;
    RecyclerCheckboxAdapter recyclerCheckboxAdapter;
    ArrayList<CheckboxModel> checkboxModelArrayList= new ArrayList<>();
    LinearLayout layout_recycler_view;
    String strFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        activity = DetailActivity.this;

        //```````getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        list_view = findViewById(R.id.list_view);

        recycler_view=findViewById(R.id.recycler_view);

        dataHelper = new DataHelper(activity);

        btn_update1= findViewById(R.id.btn_update1);
        btn_update2= findViewById(R.id.btn_update2);
        btn_update3= findViewById(R.id.btn_update3);
        btn_update4= findViewById(R.id.btn_update4);
        btn_update5= findViewById(R.id.btn_update5);
        btn_update6= findViewById(R.id.btn_update6);
        btn_update7= findViewById(R.id.btn_update7);
        btn_update8= findViewById(R.id.btn_update8);

        root_view = findViewById(R.id.root_view);
        layout_recycler_view=findViewById(R.id.layout_recycler_view);

        auto_complete_search = findViewById(R.id.auto_complete_search);

        edt_own_words = findViewById(R.id.edt_own_words);

        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        edt_height = findViewById(R.id.edt_height);
        edt_weight = findViewById(R.id.edt_weight);
        edt_marital_status = findViewById(R.id.edt_marital_status);
        til_no_of_children = findViewById(R.id.til_no_of_children);
        edt_no_of_children = findViewById(R.id.edt_no_of_children);
        edt_body_type = findViewById(R.id.edt_body_type);
        edt_complexion = findViewById(R.id.edt_complexion);
        edt_physical_status = findViewById(R.id.edt_physical_status);
        edt_mother_tongue = findViewById(R.id.edt_mother_tongue);
        edt_eating_habits = findViewById(R.id.edt_eating_habits);
        edt_drinking_habits = findViewById(R.id.edt_drinking_habits);
        edt_smoking_habits = findViewById(R.id.edt_smoking_habits);

        edt_religion= findViewById(R.id.edt_religion);
        edt_caste= findViewById(R.id.edt_caste);
        edt_sub_caste= findViewById(R.id.edt_sub_caste);
        edt_gothram= findViewById(R.id.edt_gothram);
        edt_star= findViewById(R.id.edt_star);
        edt_raasi= findViewById(R.id.edt_raasi);
        edt_having_dosham= findViewById(R.id.edt_having_dosham);
        til_specify_dosham=findViewById(R.id.til_specify_dosham);
        edt_specify_dosham= findViewById(R.id.edt_specify_dosham);

        edt_highest_education = findViewById(R.id.edt_highest_education);
        edt_college_name = findViewById(R.id.edt_college_name);
        edt_education_details = findViewById(R.id.edt_education_details);
        edt_occupation = findViewById(R.id.edt_occupation);
        edt_occupation_details = findViewById(R.id.edt_occupation_details);
        edt_employed_in = findViewById(R.id.edt_employed_in);
        edt_annual_income = findViewById(R.id.edt_annual_income);

        edt_country = findViewById(R.id.edt_country);
        edt_state = findViewById(R.id.edt_state);
        edt_city = findViewById(R.id.edt_city);
        edt_citizenship = findViewById(R.id.edt_citizenship);

        edt_family_value = findViewById(R.id.edt_family_value);
        edt_family_type = findViewById(R.id.edt_family_type);
        edt_family_status = findViewById(R.id.edt_family_status);
        edt_father_occupation = findViewById(R.id.edt_father_s_occupation);
        edt_mother_occupation = findViewById(R.id.edt_mother_s_occupation);
        edt_brothers = findViewById(R.id.edt_brothers);
        edt_sisters = findViewById(R.id.edt_sisters);

        edt_about_family = findViewById(R.id.edt_about_family);
        edt_hobbies = findViewById(R.id.edt_hobbies);
        edt_interests = findViewById(R.id.edt_interests);

        card_view1 = findViewById(R.id.card_view1);
        card_view2 = findViewById(R.id.card_view2);
        card_view3 = findViewById(R.id.card_view3);
        card_view4 = findViewById(R.id.card_view4);
        card_view5 = findViewById(R.id.card_view5);
        card_view6 = findViewById(R.id.card_view6);
        card_view7 = findViewById(R.id.card_view7);
        card_view8 = findViewById(R.id.card_view8);

        l_layout1 = findViewById(R.id.l_layout1);
        l_layout2 = findViewById(R.id.l_layout2);
        l_layout3 = findViewById(R.id.l_layout3);
        l_layout4 = findViewById(R.id.l_layout4);
        l_layout5 = findViewById(R.id.l_layout5);
        l_layout6 = findViewById(R.id.l_layout6);
        l_layout7 = findViewById(R.id.l_layout7);
        l_layout8 = findViewById(R.id.l_layout8);

        edt_name.setText(SharedPreferenceUtils.getUserName(activity));

        ResultMessage resultMessage=(ResultMessage) getIntent().getSerializableExtra("user_detail");

        if(resultMessage!=null){
           setValue(resultMessage.getUserDetailsArrayList().get(0));
           setId(resultMessage.getUserDetailsIdArrayList().get(0));
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
                case "6":
                    l_layout6.setVisibility(View.VISIBLE);
                    break;
                case "7":
                    l_layout7.setVisibility(View.VISIBLE);
                    break;
                case "8":
                    l_layout8.setVisibility(View.VISIBLE);
                    break;
            }

        }else{
            strFlag="new";
            switch (SharedPreferenceUtils.getCount(activity)) {
                case 0:
                    l_layout2.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    l_layout4.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    l_layout5.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    l_layout3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    l_layout6.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    l_layout7.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    l_layout8.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    l_layout1.setVisibility(View.VISIBLE);
                    break;
            }
        }


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

                    if (edt_marital_status.getText().toString().isEmpty() || edt_marital_status.getText().toString().equals("Never Married")){
                        til_no_of_children.setVisibility(View.GONE);
                    }else {
                        til_no_of_children.setVisibility(View.VISIBLE);
                    }
                    if (viewSpinnerClicked==edt_having_dosham){
                        if(edt_having_dosham.getText().toString().equals("Yes")) {
                            til_specify_dosham.setVisibility(View.VISIBLE);
                        }
                        else {
                            til_specify_dosham.setVisibility(View.GONE);
                        }
                    }
                    if(viewSpinnerClicked==edt_state){
                        edt_city.setText("");
                        edt_city.setTransitionName("");
                    }
                }
            }
        });

        edt_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
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

                        edt_age.setText(getAge(year, monthOfYear, dayOfMonth));//dateDisplayFormat()
                    }
                }, cYear, cMonth, cDay);

        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        edt_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();

                for (int i = 4; i < 7; i += 1) {
                    for (int j = 1; j <= 11; j++) {
                        valueArrayList.add(i + "Ft " + j + " In");
                    }
                }
                Log.e(TAG,"else");

                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                for (int i = 41; i < 140; i += 1) {
                    valueArrayList.add(i + " Kgs");
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
                valueArrayList.clear();
                valueArrayList.add("Never Married");
                valueArrayList.add("Widower");
                valueArrayList.add("Divorced");
                valueArrayList.add("Awaiting Divorce");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_no_of_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("None");
                valueArrayList.add("1");
                valueArrayList.add("2");
                valueArrayList.add("3");
                valueArrayList.add("4+");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_body_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Average");
                valueArrayList.add("Athletic");
                valueArrayList.add("Slim");
                valueArrayList.add("Heavy");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_complexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Very Fair");
                valueArrayList.add("Fair");
                valueArrayList.add("Wheatish");
                valueArrayList.add("Wheatish Brown");
                valueArrayList.add("Dark");
                valueArrayList.add("Very Dark");
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
                valueArrayList.clear();
                valueArrayList.add("Normal");
                valueArrayList.add("Physically Challenged");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_mother_tongue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
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

        edt_eating_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Vegetarian");
                valueArrayList.add("Non-Vegetarian");
                valueArrayList.add("Eggetarian");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_drinking_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("No");
                valueArrayList.add("Occasionally");
                valueArrayList.add("Yes");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_smoking_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("No");
                valueArrayList.add("Occasionally");
                valueArrayList.add("Yes");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_sub_caste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Arunattu Vellalar");
                valueArrayList.add("Thirunelveli Pillaimar");
                valueArrayList.add("Saiva Pillaimar");
                valueArrayList.add("Thanjavur Pillaimar(Veg)");
                valueArrayList.add("Thanjavur Pillaimar(Non - Veg)");
                valueArrayList.add("Karkatha Pillaimar(Veg)");
                valueArrayList.add("Karkatha Pillaimar(Non - Veg)");
                valueArrayList.add("Kongu Vellalar");
                valueArrayList.add("Chozhia Vellalar");
                valueArrayList.add("Thuluva Vellalar");
                valueArrayList.add("Veerakodi Vellalar");
                valueArrayList.add("Srilankan Vellalar");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();

                valueArrayList.add("Ashwini");
                valueArrayList.add("Bharani");
                valueArrayList.add("Karthigai");
                valueArrayList.add("Rohini");
                valueArrayList.add("Mirugasheerisam");
                valueArrayList.add("Thiruvathirai");
                valueArrayList.add("Punarpoosam");
                valueArrayList.add("Poosam");
                valueArrayList.add("Ayilyam");
                valueArrayList.add("Magam");
                valueArrayList.add("Pooram");
                valueArrayList.add("Utharam");
                valueArrayList.add("Hastham");
                valueArrayList.add("Chithirai");
                valueArrayList.add("Swathi");
                valueArrayList.add("Visakam");
                valueArrayList.add("Anusham");
                valueArrayList.add("Ketta");
                valueArrayList.add("Moolam");
                valueArrayList.add("Pooradam");
                valueArrayList.add("Uthiram");
                valueArrayList.add("Thiruvonam");
                valueArrayList.add("Avittam");
                valueArrayList.add("Shathayam");
                valueArrayList.add("Pooratathi");
                valueArrayList.add("Uttaratathi");
                valueArrayList.add("Revathi");


                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_raasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();

                valueArrayList.add("Mesham");
                valueArrayList.add("Rishabam");
                valueArrayList.add("Mithunam");
                valueArrayList.add("Katakam");
                valueArrayList.add("Simham");
                valueArrayList.add("Kannim");
                valueArrayList.add("Thulam");
                valueArrayList.add("Viruchigamm");
                valueArrayList.add("Thanusu");
                valueArrayList.add("Makaram");
                valueArrayList.add("Kumbam");
                valueArrayList.add("Meenam");

                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_having_dosham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Yes");
                valueArrayList.add("No");
                valueArrayList.add("Don't Know");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_specify_dosham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* layout_recycler_view.setVisibility(View.VISIBLE);
                checkboxModelArrayList.clear();
                checkboxModelArrayList.add(new CheckboxModel("Chevvai dosham"));
                checkboxModelArrayList.add(new CheckboxModel("Naga dosham"));
                checkboxModelArrayList.add(new CheckboxModel("Kala sarpa dosham"));
                checkboxModelArrayList.add(new CheckboxModel("Rahu dosham"));
                checkboxModelArrayList.add(new CheckboxModel("Kethu dosham"));
                checkboxModelArrayList.add(new CheckboxModel("Kalathra dosham"));
                recyclerCheckboxAdapter.setCheckboxModelArrayList(checkboxModelArrayList);
                recyclerCheckboxAdapter.notifyDataSetChanged();*/

                valueArrayList.clear();
                valueArrayList.add("Chevvai dosham");
                valueArrayList.add("Naga dosham");
                valueArrayList.add("Kala sarpa dosham");
                valueArrayList.add("Rahu dosham");
                valueArrayList.add("Kethu dosham");
                valueArrayList.add("Kalathra dosham");

                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();

                openDrawer();
            }
        });

        edt_highest_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"degree.json"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        valueArrayList.add(jsonArray.get(i).toString());
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

        edt_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"jobs.json"));
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        valueArrayList.add(jsonArray.get(i).toString());
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

        edt_employed_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Government");
                valueArrayList.add("Defence");
                valueArrayList.add("Private");
                valueArrayList.add("Business");
                valueArrayList.add("Self Employed");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_annual_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
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
                valueArrayList.clear();
                valueArrayList.add("India");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        edt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(Utils.loadJSONFromAsset(activity,"states_districts.json"));
                    if(edt_state.getTransitionName()!=null&& !edt_state.getTransitionName().isEmpty() ){
                        JSONObject jsonObj = jsonArray.getJSONObject(Integer.parseInt(edt_state.getTransitionName())-1);
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
                valueArrayList.clear();
                valueArrayList.add("India");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_family_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Orthodox");
                valueArrayList.add("Traditional");
                valueArrayList.add("Moderate");
                valueArrayList.add("Liberal");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_family_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Joint Family");
                valueArrayList.add("Nuclear Family");
                valueArrayList.add("Others");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_family_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Middle Class");
                valueArrayList.add("Upper-Middle Class");
                valueArrayList.add("High Class");
                valueArrayList.add("Rich / Affluent");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_father_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Employed");
                valueArrayList.add("Business Man");
                valueArrayList.add("Professional");
                valueArrayList.add("Retired");
                valueArrayList.add("Not Employed");
                valueArrayList.add("Passed Away");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_mother_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("Homemaker");
                valueArrayList.add("Business Women");
                valueArrayList.add("Professional");
                valueArrayList.add("Retired");
                valueArrayList.add("Passed Away");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });

        edt_brothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("None");
                valueArrayList.add("1");
                valueArrayList.add("2");
                valueArrayList.add("3");
                valueArrayList.add("4");
                valueArrayList.add("5");
                valueArrayList.add("5+");
                valueArrayListCopy.clear();
                valueArrayListCopy.addAll(0, valueArrayList);
                viewSpinnerClicked = (EditText) v;
                arrayAdapter.notifyDataSetChanged();
                openDrawer();
            }
        });



        edt_sisters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueArrayList.clear();
                valueArrayList.add("None");
                valueArrayList.add("1");
                valueArrayList.add("2");
                valueArrayList.add("3");
                valueArrayList.add("4");
                valueArrayList.add("5");
                valueArrayList.add("5+");
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

                if(showErrorMessage(edt_own_words)) {

                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("about");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setAboutMe(edt_own_words.getText().toString());
                    apiCall(Constants.PERSONAL_INSERT,userDetails,l_layout1,l_layout1);
                }
            }
        });

        btn_update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showErrorMessage(edt_name)&&showErrorMessage(edt_age)&&
                        showErrorMessage(edt_height)&&showErrorMessage(edt_weight)&&
                        showErrorMessage(edt_marital_status)&&showErrorMessage(edt_no_of_children)&&
                        showErrorMessage(edt_body_type)&&showErrorMessage(edt_complexion)&&
                        showErrorMessage(edt_physical_status)&&showErrorMessage(edt_mother_tongue)&&
                        showErrorMessage(edt_eating_habits)&&showErrorMessage(edt_drinking_habits)&&
                        showErrorMessage(edt_smoking_habits)){

                    UserDetails userDetails=new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("basic");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setName(edt_name.getText().toString());
                    userDetails.setAge(strAge);
                    userDetails.setHeight(edt_height.getText().toString());
                    userDetails.setHeightId(edt_height.getTransitionName());
                    userDetails.setWeight(edt_weight.getText().toString());
                    userDetails.setWeightId(edt_weight.getTransitionName());
                    userDetails.setMaritalStatus(edt_marital_status.getText().toString());
                    userDetails.setMaritalStatusId(edt_marital_status.getTransitionName());
                    userDetails.setNoOfChildren(edt_no_of_children.getText().toString());
                    userDetails.setBodyType(edt_body_type.getText().toString());
                    userDetails.setBodyTypeId(edt_body_type.getTransitionName());
                    userDetails.setComplexion(edt_complexion.getText().toString());
                    userDetails.setComplexionId(edt_complexion.getTransitionName());
                    userDetails.setPhysicalStatus(edt_physical_status.getText().toString());
                    userDetails.setPhysicalStatusId(edt_physical_status.getTransitionName());
                    userDetails.setMotherToungue(edt_mother_tongue.getText().toString());
                    userDetails.setMotherToungueId(edt_mother_tongue.getTransitionName());
                    userDetails.setEatingHabbits(edt_eating_habits.getText().toString());
                    userDetails.setEatingHabbitsId(edt_eating_habits.getTransitionName());
                    userDetails.setDrinkingHabbits(edt_drinking_habits.getText().toString());
                    userDetails.setDrinkingHabbitsId(edt_drinking_habits.getTransitionName());
                    userDetails.setSmokingHabbits(edt_smoking_habits.getText().toString());
                    userDetails.setSmokingHabbitsId(edt_smoking_habits.getTransitionName());

                    apiCall(Constants.PERSONAL_INSERT,userDetails,l_layout4,l_layout2);

                }


            }
        });

        btn_update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_religion)&&showErrorMessage(edt_caste)&&
                        showErrorMessage(edt_sub_caste)&&showErrorMessage(edt_gothram)&&
                        showErrorMessage(edt_star)&&showErrorMessage(edt_raasi)&&
                        showErrorMessage(edt_having_dosham)) {
                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setReligion(edt_religion.getText().toString());
                    userDetails.setCaste(edt_caste.getText().toString());
                    userDetails.setSubCaste(edt_sub_caste.getText().toString());
                    userDetails.setSubCasteId(edt_sub_caste.getTransitionName());
                    userDetails.setGothram(edt_gothram.getText().toString());
                    userDetails.setStar(edt_star.getText().toString());
                    userDetails.setStarId(edt_star.getTransitionName());
                    userDetails.setRaasi(edt_raasi.getText().toString());
                    userDetails.setRaasiId(edt_raasi.getTransitionName());
                    userDetails.setDhosham(edt_having_dosham.getText().toString());
                    userDetails.setDhoshamId(edt_having_dosham.getTransitionName());
                    apiCall(Constants.HOROSCOPE_INSERT, userDetails, l_layout6, l_layout3);
                }
            }
        });

        btn_update4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(showErrorMessage(edt_highest_education)&&showErrorMessage(edt_college_name)&&
                        showErrorMessage(edt_education_details)&&showErrorMessage(edt_occupation)&&
                        showErrorMessage(edt_occupation_details)&&showErrorMessage(edt_employed_in)&&
                        showErrorMessage(edt_annual_income)){

                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("professional");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setEducationCategory(edt_highest_education.getText().toString());
                    userDetails.setEducationCategoryId(edt_highest_education.getTransitionName());
                    userDetails.setEduCollege(edt_college_name.getText().toString());
                    userDetails.setEduDetails(edt_education_details.getText().toString());
                    userDetails.setOccupation(edt_occupation.getText().toString());
                    userDetails.setOccupationId(edt_occupation.getTransitionName());
                    userDetails.setOccuDetail(edt_occupation_details.getText().toString());
                    userDetails.setEmployedIn(edt_employed_in.getText().toString());
                    userDetails.setEmployedInId(edt_employed_in.getTransitionName());
                    userDetails.setAnnualIncome(edt_annual_income.getText().toString());
                    userDetails.setAnnualIncomeId(edt_annual_income.getTransitionName());

                    apiCall(Constants.OTHER_INSERT,userDetails,l_layout5,l_layout4);
                }
            }
        });

        btn_update5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_country)&&showErrorMessage(edt_state)&&
                        showErrorMessage(edt_city)&&showErrorMessage(edt_citizenship)) {

                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("location");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setCountry(edt_country.getText().toString());
                    userDetails.setCountryId(edt_country.getTransitionName());
                    userDetails.setState(edt_state.getText().toString());
                    userDetails.setStateId(edt_state.getTransitionName());
                    userDetails.setDistrict(edt_city.getText().toString());
                    userDetails.setDistrictId(edt_city.getTransitionName());
                    userDetails.setCitizenship(edt_citizenship.getText().toString());
                    userDetails.setCitizenshipId(edt_citizenship.getTransitionName());

                    apiCall(Constants.PERSONAL_INSERT,userDetails,l_layout3,l_layout5);
                }

            }
        });

        btn_update6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showErrorMessage(edt_family_value)&&showErrorMessage(edt_family_type)&&
                        showErrorMessage(edt_family_status)&&showErrorMessage(edt_father_occupation)&&
                        showErrorMessage(edt_mother_occupation)&&showErrorMessage(edt_no_of_children)&&
                        showErrorMessage(edt_brothers)&&showErrorMessage(edt_sisters)){

                    UserDetails userDetails=new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("family");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setFamilyValues(edt_family_value.getText().toString());
                    userDetails.setFamilyType(edt_family_type.getText().toString());
                    userDetails.setFamilyStatus(edt_family_status.getText().toString());
                    userDetails.setFatherOccupation(edt_father_occupation.getText().toString());
                    userDetails.setMotherOccupation(edt_mother_occupation.getText().toString());
                    userDetails.setNoOfBros(edt_brothers.getText().toString());
                    userDetails.setNoOfSis(edt_sisters.getText().toString());

                    userDetails.setFamilyValuesId(edt_family_value.getTransitionName());
                    userDetails.setFamilyTypeId(edt_family_type.getTransitionName());
                    userDetails.setFamilyStatusId(edt_family_status.getTransitionName());
                    userDetails.setFatherOccupationId(edt_father_occupation.getTransitionName());
                    userDetails.setMotherOccupationId(edt_mother_occupation.getTransitionName());
                    userDetails.setNoOfBrosId(edt_brothers.getTransitionName());
                    userDetails.setNoOfSisId(edt_sisters.getTransitionName());
                    userDetails.setAboutFamily("");
                    apiCall(Constants.OTHER_INSERT,userDetails,l_layout7,l_layout6);
                }
            }
        });

        btn_update7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showErrorMessage(edt_about_family)) {
                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("aboutFam");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setAboutFamily(edt_about_family.getText().toString());
                    apiCall(Constants.OTHER_INSERT,userDetails,l_layout8,l_layout7);
                }
            }
        });

        btn_update8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showErrorMessage(edt_hobbies)&&showErrorMessage(edt_interests)) {

                    UserDetails userDetails = new UserDetails();
                    userDetails.setFlag(strFlag);
                    userDetails.setKey("hobbies");
                    userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
                    userDetails.setHobbies(edt_hobbies.getText().toString());
                    userDetails.setInterest(edt_interests.getText().toString());
                    apiCall(Constants.PERSONAL_INSERT,userDetails,l_layout1,l_layout8);
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
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        strAge=ageS+","+format1.format(dob.getTime());
        return ageS + "Years / " + format.format(dob.getTime());
    }

    private void apiCall(String URL,UserDetails userDetails,LinearLayout view_layout,LinearLayout gone_layout) {
        progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Gson gson=new Gson();
        String data=gson.toJson(userDetails);
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
                        if(SharedPreferenceUtils.getCount(activity)==8){
                            Toast.makeText(activity,response.getMessage(),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(activity,PreferenceDetailActivity.class));
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

    void setValue(UserDetails userDetails){
        edt_own_words.setText(userDetails.getAboutMe());
        edt_name.setText(userDetails.getName());

        strAge=userDetails.getAge();

        edt_age.setText(userDetails.getAge());
        edt_height.setText(userDetails.getHeight());
        Log.e("tag",userDetails.getHeight());
        edt_weight.setText(userDetails.getWeight());
        edt_marital_status.setText(userDetails.getMaritalStatus());
        edt_mother_tongue.setText(userDetails.getMotherToungue());
        edt_physical_status.setText(userDetails.getPhysicalStatus());
        edt_body_type.setText(userDetails.getBodyType());
        edt_complexion.setText(userDetails.getComplexion());
        edt_eating_habits.setText(userDetails.getEatingHabbits());
        edt_drinking_habits.setText(userDetails.getDrinkingHabbits());
        edt_smoking_habits.setText(userDetails.getSmokingHabbits());
        edt_religion.setText(userDetails.getReligion());
        edt_caste.setText(userDetails.getCaste());
        edt_sub_caste.setText(userDetails.getSubCaste());
        edt_gothram.setText(userDetails.getGothram());
        edt_star.setText(userDetails.getStar());
        edt_raasi.setText(userDetails.getRaasi());
        edt_having_dosham.setText(userDetails.getDhosham());

        edt_highest_education.setText(userDetails.getEducationCategory());
        edt_college_name.setText(userDetails.getEduCollege());
        edt_education_details.setText(userDetails.getEduDetails());
        edt_occupation.setText(userDetails.getOccupation());
        edt_occupation_details.setText(userDetails.getOccuDetail());
        edt_employed_in.setText(userDetails.getEmployedIn());
        edt_annual_income.setText(userDetails.getAnnualIncome());

        edt_country.setText(userDetails.getCountry());
        edt_state.setText(userDetails.getState());
        edt_city.setText(userDetails.getDistrict());
        edt_citizenship.setText(userDetails.getCitizenship());
        edt_family_value.setText(userDetails.getFamilyValues());
        edt_family_type.setText(userDetails.getFamilyType());
        edt_family_status.setText(userDetails.getFamilyStatus());
        edt_father_occupation.setText(userDetails.getFatherOccupation());
        edt_mother_occupation.setText(userDetails.getMotherOccupation());
        edt_brothers.setText(userDetails.getNoOfBros());
        edt_sisters.setText(userDetails.getNoOfSis());
        edt_about_family.setText(userDetails.getAboutFamily());
        edt_hobbies.setText(userDetails.getHobbies());
        edt_interests.setText(userDetails.getInterest());

    }

    void setId(UserDetails userDetails){
        edt_height.setTransitionName(userDetails.getHeightId());
        edt_weight.setTransitionName(userDetails.getWeightId());
        edt_marital_status.setTransitionName(userDetails.getMaritalStatusId());
        edt_mother_tongue.setTransitionName(userDetails.getMotherToungueId());
        edt_physical_status.setTransitionName(userDetails.getPhysicalStatusId());
        edt_body_type.setTransitionName(userDetails.getBodyTypeId());
        edt_complexion.setTransitionName(userDetails.getComplexionId());
        edt_eating_habits.setTransitionName(userDetails.getEatingHabbitsId());
        edt_drinking_habits.setTransitionName(userDetails.getDrinkingHabbitsId());
        edt_smoking_habits.setTransitionName(userDetails.getSmokingHabbitsId());

        edt_sub_caste.setTransitionName(userDetails.getSubCasteId());
        edt_star.setTransitionName(userDetails.getStarId());
        edt_raasi.setTransitionName(userDetails.getRaasiId());
        edt_having_dosham.setTransitionName(userDetails.getDhoshamId());

        edt_highest_education.setTransitionName(userDetails.getEducationCategoryId());
        edt_occupation.setTransitionName(userDetails.getOccupationId());
        edt_employed_in.setTransitionName(userDetails.getEmployedInId());
        edt_annual_income.setTransitionName(userDetails.getAnnualIncomeId());

        edt_country.setTransitionName(userDetails.getCountryId());
        edt_state.setTransitionName(userDetails.getStateId());
        edt_city.setTransitionName(userDetails.getDistrictId());
        edt_citizenship.setTransitionName(userDetails.getCitizenshipId());
        edt_family_value.setTransitionName(userDetails.getFamilyValuesId());
        edt_family_type.setTransitionName(userDetails.getFamilyTypeId());
        edt_family_status.setTransitionName(userDetails.getFamilyStatusId());
        edt_father_occupation.setTransitionName(userDetails.getFatherOccupationId());
        edt_mother_occupation.setTransitionName(userDetails.getMotherOccupationId());
        edt_brothers.setTransitionName(userDetails.getNoOfBrosId());
        edt_sisters.setTransitionName(userDetails.getNoOfSisId());



    }

}