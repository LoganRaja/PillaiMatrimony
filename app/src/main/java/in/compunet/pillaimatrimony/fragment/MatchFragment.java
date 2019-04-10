package in.compunet.pillaimatrimony.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import in.compunet.pillaimatrimony.GalleryActivity;
import in.compunet.pillaimatrimony.PreferenceDetailActivity;
import in.compunet.pillaimatrimony.R;
import in.compunet.pillaimatrimony.adapter.RecyclerMatchListAdapter;
import in.compunet.pillaimatrimony.adapter.RecyclerViewPhotoAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.model.ResultMessageMatch;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;
import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;
import in.compunet.pillaimatrimony.volley.GSONDateRequest;


public class MatchFragment extends Fragment {
    RecyclerMatchListAdapter recyclerMatchListAdapter;
    RecyclerView recycler_view;
    Fragment fragment;
    ProgressDialog progress;
    boolean isApiCallFinish=false;
    ArrayList<ResultMessage> resultMessageArrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.fragment_match, container, false);
        fragment=MatchFragment.this;
        recycler_view = view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(linearLayoutManager);
        recyclerMatchListAdapter = new RecyclerMatchListAdapter(fragment,resultMessageArrayList);
        recycler_view.setAdapter(recyclerMatchListAdapter);
        if(!isApiCallFinish)
        apiCall(Constants.GET_MATCHES);
        return view;
    }
    private void apiCall(String URL) {
        progress = new ProgressDialog(fragment.getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        UserDetails userDetails=new UserDetails();
        userDetails.setUserId(SharedPreferenceUtils.getUserId(fragment.getActivity()));
        userDetails.setStarId(SharedPreferenceUtils.getStarId(fragment.getActivity()));
        userDetails.setGender(SharedPreferenceUtils.getGender(fragment.getActivity()));
        Gson gson=new Gson();
        String data=gson.toJson(userDetails);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.DATA,data);
        RequestQueue mRequestQueue = Volley.newRequestQueue(fragment.getActivity());
        GSONDateRequest<ResultMessageMatch> gsonRequest = new GSONDateRequest<>(com.android.volley.Request.Method.POST, URL,
                ResultMessageMatch.class,params,
                createRequestSuccessListener(),
                createRequestErrorListener());
        mRequestQueue.add(gsonRequest);
    }

    private Response.Listener<ResultMessageMatch> createRequestSuccessListener() {

        return new Response.Listener<ResultMessageMatch>() {

            @Override
            public void onResponse(ResultMessageMatch response) {
                progress.dismiss();
                isApiCallFinish=true;
                recyclerMatchListAdapter.setResultMessageArrayList(response.getResultMessageArrayList());
                recyclerMatchListAdapter.notifyDataSetChanged();

            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                isApiCallFinish=true;
                Toast.makeText(fragment.getActivity(),"Something went wrong please try again after some time ",Toast.LENGTH_LONG).show();

                Log.e("ResponseListener",error.getMessage());
            }
        };
    }
}
