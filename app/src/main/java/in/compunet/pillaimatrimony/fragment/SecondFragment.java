package in.compunet.pillaimatrimony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.compunet.pillaimatrimony.PreferenceActivity;
import in.compunet.pillaimatrimony.ProfileActivity;
import in.compunet.pillaimatrimony.R;

public class SecondFragment extends Fragment {
    Button btn_edit_profile;
    CardView card_view1,card_view2,card_view3,card_view4;
    TextView txt_edit_profile,txt_view_profile,txt_edit_preference,txt_view_preference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.fragment_second, container, false);
                card_view1=view.findViewById(R.id.card_view1);
        card_view2=view.findViewById(R.id.card_view2);
        card_view3=view.findViewById(R.id.card_view3);
        card_view4=view.findViewById(R.id.card_view4);


        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v,ProfileActivity.class,"false");
            }
        });

        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, ProfileActivity.class,"true");
            }
        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, PreferenceActivity.class,"false");
            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, PreferenceActivity.class,"true");
            }
        });*/
        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.test, container, false);
        txt_edit_profile=view.findViewById(R.id.txt_edit_profile);
        txt_view_profile=view.findViewById(R.id.txt_view_profile);
        txt_edit_preference=view.findViewById(R.id.txt_edit_preference);
        txt_view_preference=view.findViewById(R.id.txt_view_preference);

        txt_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v,ProfileActivity.class,"false");
            }
        });

        txt_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, ProfileActivity.class,"true");
            }
        });

        txt_edit_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, PreferenceActivity.class,"false");
            }
        });

        txt_view_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), ProfileActivity.class));
                presentActivity(v, PreferenceActivity.class,"true");
            }
        });
        return view;
    }
    public void presentActivity(View view,Class className,String isView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(getActivity(), className);
        intent.putExtra(ProfileActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(ProfileActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        if(isView.equals("true"))
        intent.putExtra("view",isView);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }

}