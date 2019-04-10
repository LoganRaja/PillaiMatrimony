package in.compunet.pillaimatrimony;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import in.compunet.pillaimatrimony.adapter.RecyclerViewGalleryAdapter;
import in.compunet.pillaimatrimony.adapter.RecyclerViewPhotoAdapter;
import in.compunet.pillaimatrimony.constants.Constants;
import in.compunet.pillaimatrimony.model.ImageDetail;
import in.compunet.pillaimatrimony.model.ImageFolderDetail;

public class GalleryActivity extends AppCompatActivity {
    public static ArrayList<ImageFolderDetail> imageFolderDetailArrayList = new ArrayList<>();
    boolean boolean_folder;
    RecyclerViewGalleryAdapter recyclerViewGalleryAdapter;
    RecyclerView recycler_view;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recycler_view = findViewById(R.id.recycler_view);
        activity=GalleryActivity.this;

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(gridLayoutManager);

        getImageFolder();

    }

    public ArrayList<ImageFolderDetail> getImageFolder() {

            imageFolderDetailArrayList.clear();
            int int_position = 0;
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;

            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
            cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                Log.e("Column", absolutePathOfImage);
                Log.e("Folder", cursor.getString(column_index_folder_name));

                for (int i = 0; i < imageFolderDetailArrayList.size(); i++) {
                    if (imageFolderDetailArrayList.get(i).getStrFolder().equals(cursor.getString(column_index_folder_name))) {
                        boolean_folder = true;
                        int_position = i;
                        break;
                    } else {
                        boolean_folder = false;
                    }
                }


                if (boolean_folder) {
                    ImageDetail imageDetail = new ImageDetail();
                    imageDetail.setStrImagePath(absolutePathOfImage);
                    ArrayList<ImageDetail> imageDetailArrayList = new ArrayList<>();
                    imageDetailArrayList.addAll(imageFolderDetailArrayList.get(int_position).getImageDetailArrayList());
                    imageDetailArrayList.add(imageDetail);
                    imageFolderDetailArrayList.get(int_position).setImageDetailArrayList(imageDetailArrayList);

                } else {
                    ImageDetail imageDetail = new ImageDetail();
                    imageDetail.setStrImagePath(absolutePathOfImage);
                    ArrayList<ImageDetail> imageDetailArrayList = new ArrayList<>();
                    imageDetailArrayList.add(imageDetail);
                    ImageFolderDetail imageFolderDetail = new ImageFolderDetail();
                    imageFolderDetail.setStrFolder(cursor.getString(column_index_folder_name));
                    imageFolderDetail.setImageDetailArrayList(imageDetailArrayList);
                    imageFolderDetailArrayList.add(imageFolderDetail);

                }


            }


            for (int i = 0; i < imageFolderDetailArrayList.size(); i++) {
                Log.e("FOLDER", imageFolderDetailArrayList.get(i).getStrFolder());
                for (int j = 0; j < imageFolderDetailArrayList.get(i).getImageDetailArrayList().size(); j++) {
                    Log.e("FILE", imageFolderDetailArrayList.get(i).getImageDetailArrayList().get(j).getStrImagePath());
                }
            }
            recyclerViewGalleryAdapter = new RecyclerViewGalleryAdapter(activity, imageFolderDetailArrayList);
            recycler_view.setAdapter(recyclerViewGalleryAdapter);

        return imageFolderDetailArrayList;

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
