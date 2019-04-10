package in.compunet.pillaimatrimony.model.basic_details;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import in.compunet.pillaimatrimony.model.RegisterRequest;

@DatabaseTable(tableName = "height_table")
public class HeightTable implements Serializable {
    @DatabaseField( columnName = "id" , unique = true,id = true)
    int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField(columnName = "height")
    @SerializedName("height")
    String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}



