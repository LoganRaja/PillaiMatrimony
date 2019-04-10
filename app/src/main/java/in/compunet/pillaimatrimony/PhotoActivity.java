package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import in.compunet.pillaimatrimony.adapter.RecyclerViewPhotoAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ImageDetail;
import in.compunet.pillaimatrimony.model.ImageFolderDetail;

public class PhotoActivity extends AppCompatActivity {
    RecyclerViewPhotoAdapter recyclerViewPhotoAdapter;
    RecyclerView recycler_view;
    Activity activity;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recycler_view = findViewById(R.id.recycler_view);
        activity=PhotoActivity.this;

        fab=findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(gridLayoutManager);
        recyclerViewPhotoAdapter = new RecyclerViewPhotoAdapter(activity,GalleryActivity.imageFolderDetailArrayList.get(getIntent().getIntExtra("iFolderPosition", 0)).getImageDetailArrayList(),false);
        recycler_view.setAdapter(recyclerViewPhotoAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageDetail> imageDetailArrayList=new ArrayList<>();
                ArrayList<ImageDetail> imageDetailTempArrayList=recyclerViewPhotoAdapter.getImageDetailArrayList();
                for(int i=0;i<imageDetailTempArrayList.size();i++){
                    if(imageDetailTempArrayList.get(i).isSelected()){
                        imageDetailArrayList.add(imageDetailTempArrayList.get(i));
                    }
                }
                Intent intent = new Intent(activity, SelectedPhotosActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ImagePath",imageDetailArrayList);
                intent.putExtra("BUNDLE",args);
                startActivityForResult(intent,Constants.REQUEST_PERMISSION_CODE);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_PERMISSION_CODE){
            setResult(resultCode);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
