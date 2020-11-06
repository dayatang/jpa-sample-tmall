package yang.yu.tmall.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "buyers")
public class Buyer extends BaseEntity {

    private String name;

    Buyer() {
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
