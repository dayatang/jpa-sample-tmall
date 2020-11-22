package yang.yu.tmall.domain.buyers;

import javax.persistence.*;

@Entity
@DiscriminatorValue("O")
public class OrgBuyer extends Buyer {

    @Column(name = "business_license_no")
    private String businessLicenseNo;

    private String taxNo;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "contact_name")),
            @AttributeOverride(name = "gender", column = @Column(name = "contact_gender")),
            @AttributeOverride(name = "mobileNo", column = @Column(name = "contact_mobile_no")),
            @AttributeOverride(name = "email", column = @Column(name = "contact_email"))
    })
    private ContactInfo contactInfo;

    public OrgBuyer() {
    }

    public OrgBuyer(String name) {
        super(name);
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
