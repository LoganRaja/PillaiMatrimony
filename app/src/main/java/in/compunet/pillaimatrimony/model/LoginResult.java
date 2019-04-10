package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Compunet-1 on 09-12-2018.
 */

public class LoginResult implements Serializable {
        @SerializedName("createdFor")
        public String createdFor ;
        @SerializedName("name")
        public String Name;
    @SerializedName("result")
    public String result;
    @SerializedName("id")
    public String id;
        @SerializedName("gender")
        public String Gender;
        @SerializedName("dob")
        public String DOB;
    @SerializedName("starId")
    public String starId;
        @SerializedName("email")
        public String Email;
    @SerializedName("regCount")
    public String regCount;
    @SerializedName("msg")
    public String msg;

    public String getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(String createdFor) {
        this.createdFor = createdFor;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFireBaseId() {
        return fireBaseId;
    }

    public void setFireBaseId(String fireBaseId) {
        this.fireBaseId = fireBaseId;
    }

    @SerializedName("firebaseId")
    public String fireBaseId;



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRegCount() {
        return regCount;
    }

    public void setRegCount(String regCount) {
        this.regCount = regCount;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }
}

