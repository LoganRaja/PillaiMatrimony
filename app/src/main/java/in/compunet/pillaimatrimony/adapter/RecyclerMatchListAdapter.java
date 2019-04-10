package in.compunet.pillaimatrimony.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.compunet.pillaimatrimony.PreferenceActivity;
import in.compunet.pillaimatrimony.ProfileActivity;
import in.compunet.pillaimatrimony.R;
import in.compunet.pillaimatrimony.model.CheckboxModel;
import in.compunet.pillaimatrimony.model.ResultMessage;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;


public class RecyclerMatchListAdapter extends RecyclerView.Adapter<RecyclerMatchListAdapter.ViewHolder> {


    private ArrayList<ResultMessage> resultMessageArrayList;
    private Fragment fragment;
    private UserDetails userDetails;

    public void setResultMessageArrayList(ArrayList<ResultMessage> resultMessageArrayList) {
        this.resultMessageArrayList = resultMessageArrayList;
    }

    public ArrayList<ResultMessage> getResultMessageArrayList() {
        return resultMessageArrayList;
    }

    public RecyclerMatchListAdapter(Fragment fragment, ArrayList<ResultMessage> resultMessageArrayList) {
        this.fragment = fragment;
        this.resultMessageArrayList = resultMessageArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        try {
            final UserDetails userDetails = resultMessageArrayList.get(position).getUserDetailsArrayList().get(0);
            viewHolder.rc_card_view.setVisibility(View.VISIBLE);
            viewHolder.txt_name.setText(userDetails.getName());
            String strAge = userDetails.getAge();

            if (strAge != null && !strAge.isEmpty()) {
                List<String> ageList = Arrays.asList(strAge.split(","));
                strAge = ageList.get(0);

            }
            viewHolder.txt_details.setText(
                    strAge + " Yrs, "
                            + userDetails.getHeight() + ", "
                            + userDetails.getEduDetails() + ",\n"
                            + userDetails.getOccuDetail() + ", "
                            + userDetails.getReligion() + ", "
                            + userDetails.getCaste() + ", \n"
                            + userDetails.getSubCaste() + ", "
                            + userDetails.getStar() + ", "
                            + userDetails.getDistrict() + ", \n"
                            + userDetails.getState() + ", "
                            + userDetails.getCountry() + "."
            );

            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user);

            Glide.with(fragment.getActivity())
                    .load(userDetails.getProfilePic() != null ? userDetails.getProfilePic() : "")
                    .apply(options)
                    .into(viewHolder.img_profile_pic);

            viewHolder.rc_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final UserDetails userDetails = resultMessageArrayList.get(position).getUserDetailsArrayList().get(0);
                    presentActivity(v, ProfileActivity.class, userDetails);
                }
            });


        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }


    }


    public void presentActivity(View view, Class className, UserDetails userDetails) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(fragment.getActivity(), view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(fragment.getActivity(), className);
        intent.putExtra(ProfileActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(ProfileActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        intent.putExtra("view", "true");
        intent.putExtra("user_detail", userDetails);
        ActivityCompat.startActivity(fragment.getActivity(), intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return resultMessageArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_profile_pic;
        private TextView txt_name;
        private TextView txt_details;
        private CardView rc_card_view;

        ViewHolder(View view) {
            super(view);
            img_profile_pic = view.findViewById(R.id.img_profile_pic);
            txt_name = view.findViewById(R.id.txt_name);
            txt_details = view.findViewById(R.id.txt_details);
            rc_card_view = view.findViewById(R.id.rc_card_view);
        }
    }

}
