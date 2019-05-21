package bakribrahim.com.moveappdemo.Models;

import com.google.gson.annotations.SerializedName;

public class Video {


    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @SerializedName("id")
           String id ;
    @SerializedName("key")
           String key ;
    @SerializedName("name")
           String name ;

    public Video(String id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

}
