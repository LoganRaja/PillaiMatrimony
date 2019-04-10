package in.compunet.pillaimatrimony.utils;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String loadJSONFromAsset(Activity activity,String strFileName) {
        Log.e("language","json");
        String json = null;
        try {
            InputStream is = activity.getAssets().open(strFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
