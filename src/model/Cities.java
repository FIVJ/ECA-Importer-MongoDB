package model;

import java.io.Serializable;

/**
 *
 * @author tassio
 */
public class Cities implements Serializable {

    private String id;
    private String siafi;
    private String city;
    private String state;
    private String region;

    public Cities() {
        super();
    }

    public Cities(String siafi, String city, String state, String region) {
        this.siafi = siafi;
        this.city = city;
        this.state = state;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Cities{" + "id=" + id + ", siafi=" + siafi + ", city=" + city + ", state=" + state + ", region=" + region + '}';
    }

}
