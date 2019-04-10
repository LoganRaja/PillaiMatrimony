package in.compunet.pillaimatrimony.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Compunet-1 on 15-12-2018.
 */

public class ImageDetail implements Serializable{
    String strImagePath;

    boolean isSelected=false;

    public String getStrImagePath() {
        return strImagePath;
    }

    public void setStrImagePath(String strImagePath) {
        this.strImagePath = strImagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}