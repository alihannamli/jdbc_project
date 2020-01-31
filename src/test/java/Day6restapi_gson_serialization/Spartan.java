package Day6restapi_gson_serialization;

import com.google.gson.annotations.SerializedName;
/*
{
    "region_id": 9,
    "region_name": "Europe"
 }
 */

public class Spartan {

    private Integer region_id;
    private String region_name;

    @Override
    public String toString() {
        return "Spartan{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                '}';
    }


    public Spartan(){

    }

    public Spartan(Integer region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }
    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public String getRegion_name() {
        return region_name;
    }


}
