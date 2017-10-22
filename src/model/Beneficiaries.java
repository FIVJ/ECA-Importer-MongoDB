package model;

import java.io.Serializable;

/**
 *
 * @author tassio
 */
public class Beneficiaries implements Serializable {

    private String id;
    private String nis;
    private String beneficiary;

    public Beneficiaries() {
        super();
    }

    public Beneficiaries(String nis, String beneficiary) {
        this.nis = nis;
        this.beneficiary = beneficiary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Beneficiaries{" + "id=" + id + ", nis=" + nis + ", beneficiary=" + beneficiary + '}';
    }

}
