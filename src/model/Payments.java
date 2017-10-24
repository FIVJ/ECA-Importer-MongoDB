package model;

import java.io.Serializable;

/**
 *
 * @author tassio
 */
public class Payments implements Serializable {

    private String id;
    private int action;
    private String nis;
    private String beneficiary;
    private String siafi;
    private String city;
    private String state;
    private String region;
    private String file;
    private String month;
    private String year;
    private int function;
    private int program;
    private String source;
    private int subFunction;
    private Double value;

    public Payments() {
        super();
    }

    public Payments(int action, String nis, String beneficiary, String siafi, String city, String state, String region, String file, String month, String year, int function, int program, String source, int subFunction, Double value) {
        this.action = action;
        this.nis = nis;
        this.beneficiary = beneficiary;
        this.siafi = siafi;
        this.city = city;
        this.state = state;
        this.region = region;
        this.file = file;
        this.month = month;
        this.year = year;
        this.function = function;
        this.program = program;
        this.source = source;
        this.subFunction = subFunction;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSubFunction() {
        return subFunction;
    }

    public void setSubFunction(int subFunction) {
        this.subFunction = subFunction;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Payments{" + "id=" + id + ", action=" + action + ", nis=" + nis + ", beneficiary=" + beneficiary + ", siafi=" + siafi + ", city=" + city + ", state=" + state + ", region=" + region + ", file=" + file + ", month=" + month + ", year=" + year + ", function=" + function + ", program=" + program + ", source=" + source + ", subFunction=" + subFunction + ", value=" + value + '}';
    }

}
