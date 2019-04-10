package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import in.compunet.pillaimatrimony.model.basic_details.PreferenceDetails;
import in.compunet.pillaimatrimony.model.basic_details.UserDetails;

/**
 * Created by Compunet-1 on 08-12-2018.
 */

public class ResultGetPreference implements Serializable {


    @SerializedName("result")
    public String result;
    public String getResult() {
        return result;

    }

    public void setResult(String result) {
        this.result = result;
    }

    @SerializedName("msg")
    public String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("data")
    public ArrayList<PreferenceDetails> preferenceDetailsArrayList=new ArrayList<>();

    public ArrayList<PreferenceDetails> getPreferenceDetailsArrayList() {
        return preferenceDetailsArrayList;
    }

    public void setPreferenceDetailsArrayList(ArrayList<PreferenceDetails> preferenceDetailsArrayList) {
        this.preferenceDetailsArrayList = preferenceDetailsArrayList;
    }
}
