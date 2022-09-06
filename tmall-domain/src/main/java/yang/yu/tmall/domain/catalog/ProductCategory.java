package yang.yu.tmall.domain.catalog;

import yang.yu.tmall.domain.commons.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_categories")
public class ProductCategory extends BaseEntity {

    private String name;

    @ManyToOne
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent")
    private Set<ProductCategory> children = new HashSet<>();

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(String name, ProductCategory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }

    public Set<ProductCategory> getChildren() {
        return new HashSet<>(children);
    }

    public void addChild(ProductCategory child) {
        child.setParent(this);
        children.add(child);
    }

    public void removeChild(ProductCategory child) {
        child.setParent(null);
        children.remove(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCategory)) {
            return false;
        }
        ProductCategory that = (ProductCategory) o;
        return getName().equals(that.getName()) &&
                Objects.equals(getParent(), that.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getParent());
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
