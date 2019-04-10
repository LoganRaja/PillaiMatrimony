package in.compunet.pillaimatrimony.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.compunet.pillaimatrimony.R;
import in.compunet.pillaimatrimony.model.ImageDetail;


public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewPhotoAdapter.ViewHolder> {


    private ArrayList<ImageDetail> imageDetailArrayList;
    private Activity activity;
    private boolean isProfileSelected;
    int iSelectedPosition=0;
    public ArrayList<ImageDetail> getImageDetailArrayList() {
        return this.imageDetailArrayList;
    }
    public void setImageDetailArrayList(ArrayList<ImageDetail> imageDetailArrayList) {
        this.imageDetailArrayList = imageDetailArrayList;
    }
    public RecyclerViewPhotoAdapter(Activity activity, ArrayList<ImageDetail> imageDetailArrayList,boolean isProfileSelected) {
        this.activity = activity;
        this.imageDetailArrayList = imageDetailArrayList;
        this.isProfileSelected = isProfileSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

            viewHolder.txt_image_count.setVisibility(View.GONE);
            viewHolder.txt_folder_name.setVisibility(View.GONE);
            viewHolder.checkbox.setVisibility(View.VISIBLE);
            if(isProfileSelected &&iSelectedPosition!=position){
                imageDetailArrayList.get(position).setSelected(false);
            }
            else if(isProfileSelected &&iSelectedPosition==position){
                imageDetailArrayList.get(position).setSelected(true);
            }
            viewHolder.checkbox.setChecked(imageDetailArrayList.get(position).isSelected());


            Glide.with(activity)
                    .load("file://" + imageDetailArrayList.get(position).getStrImagePath())
                    .into(viewHolder.img_folder);

            viewHolder.checkbox.setTag(position);
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) viewHolder.checkbox.getTag();
                if(isProfileSelected){
                    if(pos!=iSelectedPosition){
                        imageDetailArrayList.get(iSelectedPosition).setSelected(false);
                        imageDetailArrayList.get(pos).setSelected(true);
                        iSelectedPosition=pos;
                        notifyDataSetChanged();
                    }
                }
                else {
                    if (imageDetailArrayList.get(pos).isSelected()) {
                        imageDetailArrayList.get(pos).setSelected(false);
                    } else {
                        imageDetailArrayList.get(pos).setSelected(true);
                    }
                }

            }
        });

               /* if(isTitleChecked)
                imageDetailArrayList.get(position).setSelected(true);
                else
                    imageDetailArrayList.get(position).setSelected(false);*/



    }

    @Override
    public int getItemCount() {
           return imageDetailArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_image_count;
        private TextView txt_folder_name;
        private ImageView img_folder;
        private CardView rc_card_view;
        private CheckBox checkbox;

        ViewHolder(View view) {
            super(view);
            rc_card_view=view.findViewById(R.id.rc_card_view);
            img_folder = view.findViewById(R.id.img_folder);
            txt_image_count=view.findViewById(R.id.txt_image_count);
            txt_folder_name=view.findViewById(R.id.txt_folder_name);
            checkbox=view.findViewById(R.id.checkbox);
        }
    }

}
