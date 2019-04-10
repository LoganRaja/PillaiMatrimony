package in.compunet.pillaimatrimony.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class CheckboxModel implements Serializable {
        @SerializedName("isTitleChecked")
        public boolean isTitleChecked=false;
        @SerializedName("title")
        public String strTitle;

    public boolean isTitleChecked() {
        return isTitleChecked;
    }

    public void setTitleChecked(boolean titleChecked) {
        isTitleChecked = titleChecked;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

     public CheckboxModel(String strTitle,boolean isTitleChecked){
        this.strTitle= strTitle;
        this.isTitleChecked=isTitleChecked;
    }
     public CheckboxModel(String strTitle){
        this.strTitle= strTitle;
    }
    CheckboxModel(){ }

}

