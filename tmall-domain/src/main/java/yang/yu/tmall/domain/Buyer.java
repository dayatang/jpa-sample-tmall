package yang.yu.tmall.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "buyers")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Buyer extends BaseEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String name;

    private String mobileNo;

    private String wiredNo;

    private String email;

    @ElementCollection
    @CollectionTable(name = "shipping_addresses")
    private Set<Address> shippingAddresses = new HashSet<>();

    protected Buyer() {
    }

    public Buyer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getWiredNo() {
        return wiredNo;
    }

    public void setWiredNo(String wiredNo) {
        this.wiredNo = wiredNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getShippingAddresses() {
        return new HashSet<>(shippingAddresses);
    }

    public void addShippingAddresses(Address address) {
        shippingAddresses.add(address);
    }

    public void removeShippingAddresses(Address address) {
        shippingAddresses.remove(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buyer)) {
            return false;
        }
        Buyer buyer = (Buyer) o;
        return Objects.equals(getName(), buyer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                '}';
    }
}
