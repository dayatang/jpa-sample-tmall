package yang.yu.tmall.domain.commons;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 所有实体的共同基类。定义实体公共属性和行为
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private LocalDateTime created;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Transient
    private boolean isNew = true;

    /**
     * 获取ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 设置ID
     * @param id 要设置的ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取版本号。该属性用于辅助实现乐观锁
     * @return
     */
    public int getVersion() {
        return version;
    }

    /**
     * 设置版本号
     * @param version 要设置的版本号
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 获取实体创建时间
     * @return 实体的创建时间
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * 获取实体最后一次修改时间
     * @return 实体的最后修改时间
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 判断实体是不是全新的（在数据库中没有对应记录）
     * @return 如果实体是新的，返回true；否则返回false
     */
    public boolean isNew() {
        return isNew;
    }

    /**
     * 生命周期回调方法。在实体将被第一次保存到数据库中前调用
     */
    @PrePersist
    public void beforeCreate() {
        this.isNew = false;
        created = LocalDateTime.now();
    }

    /**
     * 生命周期回调方法。在实体每次保存到数据库中前调用
     */
    @PreUpdate
    public void afterSave() {
        this.isNew = false;
        lastUpdated = LocalDateTime.now();
    }

    /**
     * 生命周期回调方法。在实体每次从数据库装载后调用
     */
    @PostLoad
    void afterLoad() {
        this.isNew = false;
    }
}
