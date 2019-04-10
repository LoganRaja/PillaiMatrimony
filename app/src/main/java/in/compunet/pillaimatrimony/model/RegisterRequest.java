package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    @SerializedName("createdBy")
    public String ProfileCreatedFor ;
    @SerializedName("name")
    public String Name;
    @SerializedName("gender")
    public String Gender;
    @SerializedName("dob")
    public String DOB;
    @SerializedName("mobile")
    public String Mobile;
    @SerializedName("email")
    public String Email;
    @SerializedName("password")
    public String Password;

    public String getFireBaseId() {
        return fireBaseId;
    }

    public void setFireBaseId(String fireBaseId) {
        this.fireBaseId = fireBaseId;
    }

    @SerializedName("firebaseId")
    public String fireBaseId;

    public String getProfileCreatedFor() {
        return ProfileCreatedFor;
    }

    public void setProfileCreatedFor(String profileCreatedFor) {
        ProfileCreatedFor = profileCreatedFor;
    }

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

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
