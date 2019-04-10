package in.compunet.pillaimatrimony;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import in.compunet.pillaimatrimony.utils.SharedPreferenceUtils;

public class PinchZoomActivity extends Activity {
    private TouchImageView img_profile_pic;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_zoom);
        activity=PinchZoomActivity.this;
        img_profile_pic = findViewById(R.id.img_profile_pic);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true)
                .placeholder(R.drawable.user)
                .error(R.drawable.user);

        Glide.with(activity)
                .load(getIntent().getStringExtra("profile_pic")!=null?getIntent().getStringExtra("profile_pic"):"")
                .apply(options)
                .into(img_profile_pic);
    }

}
