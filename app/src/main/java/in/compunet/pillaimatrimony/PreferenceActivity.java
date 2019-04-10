package in.compunet.pillaimatrimony;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ResultGetPreference;
import in.compunet.pillaimatrimony.model.basic_details.PreferenceDetails;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class PreferenceActivity extends AppCompatActivity {

    ImageView img_basic_preference,img_religious_preference,img_professional_preference,img_location_preference,img_looking_for;
    Activity activity;
    CardView card_view1,card_view2,card_view3,card_view4,card_view5;
    TextView txt_age,txt_height,txt_marital_status,
            txt_mother_tongue,txt_physical_status,txt_eating_habits,txt_drinking_habits,txt_smoking_habits,
            txt_religion,txt_caste,txt_sub_caste,txt_star, txt_dosham,
            txt_education_category, txt_occupation, txt_annual_income, txt_country, txt_state,
            txt_city, txt_citizenship;


    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    View rootLayout;
    private int revealX;
    private int revealY;

    ProgressDialog progress;
    ResultGetPreference resultGetPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        activity=PreferenceActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = findViewById(R.id.root_layout);
        Intent intent = getIntent();
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }


        apiCall();


        card_view1=findViewById(R.id.card_view1);
        card_view2=findViewById(R.id.card_view2);
        card_view3=findViewById(R.id.card_view3);
        card_view4=findViewById(R.id.card_view4);
        card_view5=findViewById(R.id.card_view5);

        img_basic_preference=findViewById(R.id.img_basic_preference);
        img_religious_preference=findViewById(R.id.img_religious_preference);
        img_professional_preference=findViewById(R.id.img_professional_preference);
        img_location_preference=findViewById(R.id.img_location_preference);
        img_looking_for=findViewById(R.id.img_looking_for);


        txt_age=findViewById(R.id.txt_age);
        txt_height=findViewById(R.id.txt_height);
        txt_marital_status=findViewById(R.id.txt_marital_status);
        txt_mother_tongue=findViewById(R.id.txt_mother_tongue);
        txt_physical_status=findViewById(R.id.txt_physical_status);
        txt_eating_habits=findViewById(R.id.txt_eating_habits);
        txt_drinking_habits=findViewById(R.id.txt_drinking_habits);
        txt_smoking_habits=findViewById(R.id.txt_smoking_habits);
        txt_religion=findViewById(R.id.txt_religion);
        txt_caste=findViewById(R.id.txt_caste);
        txt_sub_caste=findViewById(R.id.txt_sub_caste);
        txt_star=findViewById(R.id.txt_star);
        txt_dosham=findViewById(R.id.txt_dosham);
        txt_education_category=findViewById(R.id.txt_education_category);
        txt_occupation=findViewById(R.id.txt_occupation);
        txt_annual_income=findViewById(R.id.txt_annual_income);
        txt_country=findViewById(R.id.txt_country);
        txt_state=findViewById(R.id.txt_state);
        txt_city=findViewById(R.id.txt_city);
        txt_citizenship=findViewById(R.id.txt_citizenship);

        if(getIntent().getStringExtra("view")!=null){
            img_basic_preference.setVisibility(View.GONE);
            img_religious_preference.setVisibility(View.GONE);
            img_professional_preference.setVisibility(View.GONE);
            img_location_preference.setVisibility(View.GONE);
            img_looking_for.setVisibility(View.GONE);
        }


        img_basic_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, PreferenceDetailActivity.class);
                intent.putExtra("card_number","1");
                intent.putExtra("preference_details",resultGetPreference);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view1, "card_view1");
                startActivity(intent, options.toBundle());
             // startActivity(new Intent(activity,DetailActivity.class));
            }
        });


        img_religious_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, PreferenceDetailActivity.class);
                intent.putExtra("card_number","2");
                intent.putExtra("preference_details",resultGetPreference);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view2, "card_view2");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });
        img_professional_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, PreferenceDetailActivity.class);
                intent.putExtra("card_number","3");
                intent.putExtra("preference_details",resultGetPreference);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view3, "card_view3");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_location_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, PreferenceDetailActivity.class);
                intent.putExtra("card_number","4");
                intent.putExtra("preference_details",resultGetPreference);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view4, "card_view4");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_looking_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, PreferenceDetailActivity.class);
                intent.putExtra("card_number","5");
                intent.putExtra("preference_details",resultGetPreference);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view5, "card_view5");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });


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

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(500);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                    rootLayout, revealX, revealY, finalRadius, 0);

            circularReveal.setDuration(500);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
            });


            circularReveal.start();
        }
    }

    private void apiCall() {
        progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        UserDetails userDetails=new UserDetails();
        userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
        Gson gson=new Gson();
        String data=gson.toJson(userDetails);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.DATA,data);
        RequestQueue mRequestQueue = Volley.newRequestQueue(activity);
        GSONDateRequest<ResultGetPreference> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, Constants.GET_PREFERENCE,
                ResultGetPreference.class,params,
                createRequestSuccessListener(),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<ResultGetPreference> createRequestSuccessListener() {

        return new Response.Listener<ResultGetPreference>() {

            @Override
            public void onResponse(ResultGetPreference response) {
                progress.dismiss();
                if(response.getResult()!=null&&response.getResult().equals("1")){
                    resultGetPreference=response;
                    ArrayList<PreferenceDetails> preferenceDetailsArrayList=response.getPreferenceDetailsArrayList();
                    PreferenceDetails preferenceDetails=preferenceDetailsArrayList.get(0);
                    setValue(preferenceDetails);
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
                        .make(rootLayout , "Something went wrong please try again after some time ", Snackbar.LENGTH_LONG);
                snackbar.show();
                Log.e("ResponseListener",error.getMessage());
            }
        };
    }

    void setValue(PreferenceDetails preferenceDetails){

        txt_age.setText(preferenceDetails.getAge());
        txt_height.setText(preferenceDetails.getHeight());
        txt_marital_status.setText(preferenceDetails.getMaritalStatus());
        txt_mother_tongue.setText(preferenceDetails.getMotherToungue());
        txt_physical_status.setText(preferenceDetails.getPhysicalStatus());
        txt_eating_habits.setText(preferenceDetails.getEatingHabbits());
        txt_drinking_habits.setText(preferenceDetails.getDrinkingHabbits());
        txt_smoking_habits.setText(preferenceDetails.getSmokingHabbits());

        txt_religion.setText(preferenceDetails.getReligion());
        txt_caste.setText(preferenceDetails.getCaste());
        txt_sub_caste.setText(preferenceDetails.getSubCaste());
        txt_star.setText(preferenceDetails.getStar());
        txt_dosham.setText(preferenceDetails.getDhosham());

        txt_education_category.setText(preferenceDetails.getEducationCategory());
        txt_occupation.setText(preferenceDetails.getOccupation());
        txt_annual_income.setText(preferenceDetails.getAnnualIncome());

        txt_country.setText(preferenceDetails.getCountry());
        txt_state.setText(preferenceDetails.getState());
        txt_city.setText(preferenceDetails.getDistrict());
        txt_citizenship.setText(preferenceDetails.getCitizenship());

    }

    @Override
    public void onResume() {
        super.onResume();
        if(SharedPreferenceUtils.getIsUpdated(activity)){
            apiCall();
            SharedPreferenceUtils.setIsUpdated(activity,false);
        }

    }
    @Override
    public void onBackPressed(){
       unRevealActivity();
    }
}
