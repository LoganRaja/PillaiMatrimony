package in.compunet.pillaimatrimony.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.compunet.pillaimatrimony.PhotoActivity;
import in.compunet.pillaimatrimony.R;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ImageFolderDetail;


public class RecyclerViewGalleryAdapter extends RecyclerView.Adapter<RecyclerViewGalleryAdapter.ViewHolder> {


    private ArrayList<ImageFolderDetail> imageFolderDetailArrayList;
    private Activity activity;
    private ImageFolderDetail imageFolderDetail;

    public RecyclerViewGalleryAdapter(Activity activity, ArrayList<ImageFolderDetail> imageFolderDetailArrayList) {
        this.activity = activity;
        this.imageFolderDetailArrayList = imageFolderDetailArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

            imageFolderDetail = imageFolderDetailArrayList.get(position);

            viewHolder.txt_folder_name.setText(imageFolderDetail.getStrFolder());
            viewHolder.txt_image_count.setText(imageFolderDetail.getImageDetailArrayList().size()+"");
            viewHolder.checkbox.setVisibility(View.GONE);
            Glide.with(activity)
                    .load("file://" + imageFolderDetailArrayList.get(position).getImageDetailArrayList().get(0).getStrImagePath())
                    .into(viewHolder.img_folder);
            viewHolder.rc_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(activity, PhotoActivity.class);
                    intent.putExtra("iFolderPosition",position);
                    activity.startActivityForResult(intent, Constants.REQUEST_PERMISSION_CODE);

                }
            });

    }

    @Override
    public int getItemCount() {
           return imageFolderDetailArrayList.size();
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
