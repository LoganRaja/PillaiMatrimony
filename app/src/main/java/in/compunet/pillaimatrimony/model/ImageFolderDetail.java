package in.compunet.pillaimatrimony.model;

import java.util.ArrayList;

/**
 * Created by Compunet-1 on 15-12-2018.
 */

public class ImageFolderDetail {
    String strFolder;

    boolean isSelected=false;

    public String getStrFolder() {
        return strFolder;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setStrFolder(String strFolder) {
        this.strFolder = strFolder;
    }

    public ArrayList<ImageDetail> getImageDetailArrayList() {
        return imageDetailArrayList;
    }

    public void setImageDetailArrayList(ArrayList<ImageDetail> imageDetailArrayList) {
        this.imageDetailArrayList = imageDetailArrayList;
    }

    ArrayList<ImageDetail> imageDetailArrayList;


}