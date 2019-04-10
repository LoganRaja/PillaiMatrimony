package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.compunet.pillaimatrimony.adapter.RecyclerViewPhotoAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ImageDetail;
import in.compunet.pillaimatrimony.model.ImageFolderDetail;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.volley.VolleyMultipartRequest;

public class SelectedPhotosActivity extends AppCompatActivity {
    public static ArrayList<ImageFolderDetail> imageFolderDetailArrayList = new ArrayList<>();
    boolean boolean_folder;
    RecyclerViewPhotoAdapter recyclerViewPhotoAdapter;
    RecyclerView recycler_view;
    private static final int REQUEST_PERMISSIONS = 100;
    Activity activity;
    FloatingActionButton fab;

    Bitmap bitmap;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recycler_view = findViewById(R.id.recycler_view);
        activity=SelectedPhotosActivity.this;
        fab=findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        final Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<ImageDetail> imageDetailArrayList = (ArrayList<ImageDetail>) args.getSerializable("ImagePath");
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(gridLayoutManager);
        recyclerViewPhotoAdapter = new RecyclerViewPhotoAdapter(activity,imageDetailArrayList,true);
        recycler_view.setAdapter(recyclerViewPhotoAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageDetail> imageDetailArrayList=recyclerViewPhotoAdapter.getImageDetailArrayList();
                for(int i=0;i<imageDetailArrayList.size();i++){
                    if(imageDetailArrayList.get(i).isSelected()){
                        SharedPreferenceUtils.setProfilePicUri(activity,imageDetailArrayList.get(i).getStrImagePath());

                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                         bitmap = BitmapFactory.decodeFile(imageDetailArrayList.get(i).getStrImagePath(),bmOptions);
                         if(bitmap!=null)
                         imageUpload();
                    }
                }
                // recyclerViewPhotoAdapter.setImageDetailArrayList(imageDetailArrayList);
                // recyclerViewPhotoAdapter.notifyDataSetChanged();
            }
        });
    }

void imageUpload() {
    progress = new ProgressDialog(activity);
    progress.setTitle("Loading");
    progress.setMessage("Wait while uploading...");
    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
    progress.show();
    //our custom volley request
    VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Constants.INSERT_PROFILE_PIC,
            new Response.Listener<NetworkResponse>() {
                @Override
                public void onResponse(NetworkResponse nresponse) {
                    progress.dismiss();
                    setResult(Constants.REQUEST_PERMISSION_CODE);
                    Toast.makeText(activity , "Image Upload Successfully ", Snackbar.LENGTH_LONG).show();
                    finish();

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                            Toast.makeText(activity , "Something went wrong please try again after some time ", Snackbar.LENGTH_LONG).show();
                }
            }) {



        protected Map<String, String> getParams() throws AuthFailureError {
            UserDetails userDetails=new UserDetails();
            userDetails.setUserId(SharedPreferenceUtils.getUserId(activity));
            userDetails.setProfilePic(SharedPreferenceUtils.getUserId(activity) + "profile_pic.png");
            Gson gson=new Gson();
            String data=gson.toJson(userDetails);
            HashMap<String, String> params = new HashMap<>();
            params.put(Constants.DATA,data);
            Log.e("data", params.toString());
            return params;
        }


        @Override
        protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
            Map<String, DataPart> params = new HashMap<>();

            params.put("profile_pic", new DataPart(SharedPreferenceUtils.getUserId(activity) + "profile_pic.png", getFileDataFromDrawable(bitmap)));

            return params;

        }
    };

    volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(100000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    //AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request,cancel_request);

    //adding the request to volley
    Volley.newRequestQueue(this).add(volleyMultipartRequest);
}
    public byte[] getFileDataFromDrawable(Bitmap bit) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {

            byteArrayOutputStream = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);


        }catch (Exception e){

        }
        return byteArrayOutputStream.toByteArray();
    }




}
