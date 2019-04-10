package in.compunet.pillaimatrimony.utils;

import android.content.Context;
import android.content.SharedPreferences;

import in.compunet.pillaimatrimony.constants.Constants;


public class SharedPreferenceUtils {

    public static void setProfilePicUri(Context context, String imagePath) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.IMAGE_PATH,imagePath);
        editor.apply();
    }
    public static String getProfilePicUri(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getString(Constants.IMAGE_PATH, "");
    }

    public static void setCount(Context context, int iDetailCount) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(Constants.COUNT,iDetailCount);
        editor.apply();
    }



    public static void setUserId(Context context, String strUserId) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.USER_ID,strUserId);
        editor.apply();
    }

    public static void setUserName(Context context, String strUserName) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.USER_NAME,strUserName);
        editor.apply();
    }
    public static void setStarId(Context context, String strStarId) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.STAR_ID,strStarId);
        editor.apply();
    }

    public static void setIsUpdated(Context context, boolean isUpdated) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Constants.IS_UPDATED,isUpdated);
        editor.apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getString(Constants.USER_ID, "");
    }
    public static String getUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getString(Constants.USER_NAME, "");
    }
    public static int getCount(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getInt(Constants.COUNT, 0);
    }


    public static boolean getIsUpdated(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getBoolean(Constants.IS_UPDATED, false);
    }
    public static void setGender(Context context, String gender) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.GENDER,gender);
        editor.apply();
    }

    public static String getGender(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getString(Constants.GENDER, "");
    }

    public static String getStarId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return pref.getString(Constants.STAR_ID, "");
    }

    public static void clearSharedPreferenceUtils(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        editor.apply();
    }
}
