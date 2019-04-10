package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import in.compunet.pillaimatrimony.model.basic_details.UserDetails;

/**
 * Created by Compunet-1 on 08-12-2018.
 */

public class ResultMessage implements Serializable {


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
    public ArrayList<UserDetails> userDetailsArrayList=new ArrayList<>();

    @SerializedName("ids")
    public ArrayList<UserDetails> userDetailsIdArrayList=new ArrayList<>();

    public ArrayList<UserDetails> getUserDetailsArrayList() {
        return userDetailsArrayList;
    }

    public void setUserDetailsArrayList(ArrayList<UserDetails> userDetailsArrayList) {
        this.userDetailsArrayList = userDetailsArrayList;
    }

    public ArrayList<UserDetails> getUserDetailsIdArrayList() {
        return userDetailsIdArrayList;
    }

    public void setUserDetailsIdArrayList(ArrayList<UserDetails> userDetailsIdArrayList) {
        this.userDetailsIdArrayList = userDetailsIdArrayList;
    }
}
