package yang.yu.tmall.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("P")
public class PersonalBuyer extends Buyer {

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "contact_infos")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "im_type")
    @Column(name = "im_value")
    private Map<ImType, String> imInfos = new HashMap<>();

    public PersonalBuyer() {
    }

    public PersonalBuyer(String name) {
        super(name);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Map<ImType, String> getImInfos() {
        return imInfos;
    }

    public void setImInfos(Map<ImType, String> imInfos) {
        this.imInfos = imInfos;
    }

    public String getImInfo(ImType type) {
        return imInfos.getOrDefault(type, "");
    }

    public void setImInfo(ImType type, String value) {
        imInfos.put(type, value);
    }
}
