package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import in.compunet.pillaimatrimony.model.basic_details.UserDetails;

/**
 * Created by Compunet-1 on 08-12-2018.
 */

public class ResultMessageMatch implements Serializable {


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

    @SerializedName("users")
    public ArrayList<ResultMessage> resultMessageArrayList=new ArrayList<>();

    public ArrayList<ResultMessage> getResultMessageArrayList() {
        return resultMessageArrayList;
    }

    public void setResultMessageArrayList(ArrayList<ResultMessage> resultMessageArrayList) {
        this.resultMessageArrayList = resultMessageArrayList;
    }
}
