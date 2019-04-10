package in.compunet.pillaimatrimony;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;

public class ProfileActivity extends AppCompatActivity {
    FloatingActionButton fab;
    TextView txt_own_words,txt_name,txt_age,txt_height,txt_weight,txt_marital_status,
    txt_mother_tongue,txt_physical_status,txt_body_type,txt_complexion,txt_created_by,txt_eating_habits,txt_drinking_habits,txt_smoking_habits,
     txt_religion,txt_caste,txt_sub_caste,txt_gothram,txt_star, txt_raasi, txt_dosham,
      txt_education_category, txt_college_name,txt_education_detail, txt_occupation,
      txt_occupation_detail, txt_employed_in, txt_annual_income, txt_country, txt_state,
      txt_city, txt_citizenship, txt_family_value, txt_family_type,
      txt_family_status, txt_father_occupation, txt_mother_occupation, txt_brothers,txt_sisters, txt_about_family, txt_hobbies_interests;


    ImageView img_profile_pic,img_own_words,img_basic_details,img_religious_info,img_professional,img_location,img_family_details,img_about_my_family,img_hobbies_interests;
    Activity activity;
    CardView card_view1,card_view2,card_view3,card_view4,card_view5,card_view6,card_view7,card_view8;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    View rootLayout;
    private int revealX;
    private int revealY;

    ResultMessage resultMessage;

    private static final int REQUEST_PERMISSIONS = 100;

    ProgressDialog progress;

    String strProfilePicURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        activity=ProfileActivity.this;
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

        fab =  findViewById(R.id.fab);

        card_view1=findViewById(R.id.card_view1);
        card_view2=findViewById(R.id.card_view2);
        card_view3=findViewById(R.id.card_view3);
        card_view4=findViewById(R.id.card_view4);
        card_view5=findViewById(R.id.card_view5);
        card_view6=findViewById(R.id.card_view6);
        card_view7=findViewById(R.id.card_view7);
        card_view8=findViewById(R.id.card_view8);

        img_profile_pic=findViewById(R.id.img_profile_pic);
        img_own_words=findViewById(R.id.img_own_words);
        img_basic_details=findViewById(R.id.img_basic_details);
        img_religious_info=findViewById(R.id.img_religious_info);
        img_professional=findViewById(R.id.img_professional_info);
        img_location=findViewById(R.id.img_location);
        img_family_details=findViewById(R.id.img_family_details);
        img_about_my_family=findViewById(R.id.img_about_my_family);
        img_hobbies_interests=findViewById(R.id.img_hobbies_interests);

        txt_own_words=findViewById(R.id.txt_own_words);
        txt_name=findViewById(R.id.txt_name);
        txt_age=findViewById(R.id.txt_age);
        txt_height=findViewById(R.id.txt_height);
        txt_weight=findViewById(R.id.txt_weight);
        txt_marital_status=findViewById(R.id.txt_marital_status);
        txt_mother_tongue=findViewById(R.id.txt_mother_tongue);
        txt_physical_status=findViewById(R.id.txt_physical_status);
        txt_complexion=findViewById(R.id.txt_complexion);
        txt_body_type=findViewById(R.id.txt_body_type);
        txt_created_by=findViewById(R.id.txt_created_by);
        txt_eating_habits=findViewById(R.id.txt_eating_habits);
        txt_drinking_habits=findViewById(R.id.txt_drinking_habits);
        txt_smoking_habits=findViewById(R.id.txt_smoking_habits);
        txt_religion=findViewById(R.id.txt_religion);
        txt_caste=findViewById(R.id.txt_caste);
        txt_sub_caste=findViewById(R.id.txt_sub_caste);
        txt_gothram=findViewById(R.id.txt_gothram);
        txt_star=findViewById(R.id.txt_star);
        txt_raasi=findViewById(R.id.txt_raasi);
        txt_dosham=findViewById(R.id.txt_dosham);
        txt_education_category=findViewById(R.id.txt_education_category);
        txt_college_name=findViewById(R.id.txt_college_name);
        txt_education_detail=findViewById(R.id.txt_education_detail);
        txt_occupation=findViewById(R.id.txt_occupation);
        txt_occupation_detail=findViewById(R.id.txt_occupation_detail);
        txt_employed_in=findViewById(R.id.txt_employed_in);
        txt_annual_income=findViewById(R.id.txt_annual_income);
        txt_country=findViewById(R.id.txt_country);
        txt_state=findViewById(R.id.txt_state);
        txt_city=findViewById(R.id.txt_city);
        txt_citizenship=findViewById(R.id.txt_citizenship);
        txt_family_value=findViewById(R.id.txt_family_values);
        txt_family_type=findViewById(R.id.txt_family_type);
        txt_family_status=findViewById(R.id.txt_family_status);
        txt_father_occupation=findViewById(R.id.txt_father_occupation);
        txt_mother_occupation=findViewById(R.id.txt_mother_occupation);
        txt_brothers=findViewById(R.id.txt_brothers);
        txt_sisters=findViewById(R.id.txt_sisters);
        txt_about_family=findViewById(R.id.txt_about_family);
        txt_hobbies_interests=findViewById(R.id.txt_hobbies_interests);




        if(getIntent().getStringExtra("view")!=null){
            img_own_words.setVisibility(View.GONE);
            img_basic_details.setVisibility(View.GONE);
            img_religious_info.setVisibility(View.GONE);
            img_professional.setVisibility(View.GONE);
            img_location.setVisibility(View.GONE);
            img_family_details.setVisibility(View.GONE);
            img_about_my_family.setVisibility(View.GONE);
            img_hobbies_interests.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
        }

        UserDetails userDetails=(UserDetails) getIntent().getSerializableExtra("user_detail");

        if(userDetails!=null){
            setValue(userDetails);
        }else {
            apiCall();
        }


        img_own_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","1");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view1, "card_view1");
                startActivity(intent, options.toBundle());
             // startActivity(new Intent(activity,DetailActivity.class));
            }
        });


        img_basic_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","2");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view2, "card_view2");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });
        img_religious_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","3");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view3, "card_view3");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","4");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view4, "card_view4");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","5");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view5, "card_view5");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_family_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","6");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view6, "card_view6");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_about_my_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","7");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view7, "card_view7");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });

        img_hobbies_interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presentActivity(v);
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("card_number","8");
                intent.putExtra("user_detail",resultMessage);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, (View)card_view8, "card_view8");
                startActivity(intent, options.toBundle());
                // startActivity(new Intent(activity,DetailActivity.class));
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                }else {
                    startActivityForResult(new Intent(activity,GalleryActivity.class), Constants.REQUEST_PERMISSION_CODE);
                }

            }
        });

        img_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,PinchZoomActivity.class);
                if(strProfilePicURL!=null)
                intent.putExtra("profile_pic",strProfilePicURL);
                startActivity(intent);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Constants.REQUEST_PERMISSION_CODE){
            if(!SharedPreferenceUtils.getProfilePicUri(activity).equals("")){
                Glide.with(activity)
                        .load("file://" + SharedPreferenceUtils.getProfilePicUri(activity))
                        .into(img_profile_pic);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(new Intent(activity,GalleryActivity.class), Constants.REQUEST_PERMISSION_CODE);
                    } else {
                        Toast.makeText(activity, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
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
        GSONDateRequest<ResultMessage> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, Constants.GET_PROFILE,
                ResultMessage.class,params,
                createRequestSuccessListener(),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<ResultMessage> createRequestSuccessListener() {

        return new Response.Listener<ResultMessage>() {

            @Override
            public void onResponse(ResultMessage response) {
                progress.dismiss();
                if(response.getResult()!=null&&response.getResult().equals("1")){
                    resultMessage=response;
                    ArrayList<UserDetails> userDetailsArrayList=response.getUserDetailsArrayList();
                    UserDetails userDetails=userDetailsArrayList.get(0);
                    setValue(userDetails);
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

    void setValue(UserDetails userDetails){
        txt_own_words.setText(userDetails.getAboutMe()!=null?userDetails.getAboutMe():"Not Specified");
        txt_name.setText(userDetails.getName());
        txt_age.setText(userDetails.getAge());
        txt_height.setText(userDetails.getHeight());
        txt_weight.setText(userDetails.getWeight());
        txt_marital_status.setText(userDetails.getMaritalStatus());
        txt_mother_tongue.setText(userDetails.getMotherToungue());
        txt_physical_status.setText(userDetails.getPhysicalStatus());
        txt_body_type.setText(userDetails.getBodyType());
        txt_complexion.setText(userDetails.getComplexion());
        txt_created_by.setText(userDetails.getProfileCreatedBy());
        txt_eating_habits.setText(userDetails.getEatingHabbits());
        txt_drinking_habits.setText(userDetails.getDrinkingHabbits());
        txt_smoking_habits.setText(userDetails.getSmokingHabbits());

        txt_religion.setText(userDetails.getReligion());
        txt_caste.setText(userDetails.getCaste());
        txt_sub_caste.setText(userDetails.getSubCaste());
        txt_gothram.setText(userDetails.getGothram());
        txt_star.setText(userDetails.getStar());
        txt_raasi.setText(userDetails.getRaasi());
        txt_dosham.setText(userDetails.getDhosham());

        txt_education_category.setText(userDetails.getEducationCategory());
        txt_college_name.setText(userDetails.getEduCollege());
        txt_education_detail.setText(userDetails.getEduDetails());
        txt_occupation.setText(userDetails.getOccupation());
        txt_occupation_detail.setText(userDetails.getOccuDetail());
        txt_employed_in.setText(userDetails.getEmployedIn());
        txt_annual_income.setText(userDetails.getAnnualIncome());

        txt_country.setText(userDetails.getCountry());
        txt_state.setText(userDetails.getState());
        txt_city.setText(userDetails.getDistrict());
        txt_citizenship.setText(userDetails.getCitizenship());

        txt_family_value.setText(userDetails.getFamilyValues());
        txt_family_type.setText(userDetails.getFamilyType());
        txt_family_status.setText(userDetails.getFamilyStatus());
        txt_father_occupation.setText(userDetails.getFatherOccupation());
        txt_mother_occupation.setText(userDetails.getMotherOccupation());
        txt_brothers.setText(userDetails.getNoOfBros());
        txt_sisters.setText(userDetails.getNoOfSis());
        if(userDetails.getAboutFamily()!=null&&!userDetails.getAboutFamily().isEmpty()){
            txt_about_family.setText(userDetails.getAboutFamily());
        }
        else {
            txt_about_family.setText("Not Specified");
        }

        if(userDetails.getInterest()!=null&&!userDetails.getInterest().isEmpty()
                &&userDetails.getHobbies()!=null&&!userDetails.getHobbies().isEmpty()){
            txt_hobbies_interests.setText("Hobbies \n\t\t\t\t"+userDetails.getHobbies()+"\n\n"+
            "Interest \n\t\t\t\t"+userDetails.getInterest());
        }
        else{
            txt_hobbies_interests.setText("Not Specified");
        }
        strProfilePicURL=userDetails.getProfilePic();

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true)
                .placeholder(R.drawable.user)
                .error(R.drawable.user);
        Glide.with(activity)
                .load(userDetails.getProfilePic()!=null?userDetails.getProfilePic():"")
                .apply(options)
                .into(img_profile_pic);
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
