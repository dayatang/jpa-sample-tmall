package yang.yu.tmall.domain;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "buyers")
public class Buyer extends BaseEntity {

    private String name;

    private String phoneNumber;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
