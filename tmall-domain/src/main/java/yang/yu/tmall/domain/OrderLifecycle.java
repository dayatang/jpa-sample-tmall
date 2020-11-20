package yang.yu.tmall.domain;

import javax.persistence.*;

/**
 * 订单生命周期实体，记录某个时间点订单进入某个状态。
 */
@Entity
@Table(name = "order_lifecycles")
public class OrderLifecycle extends BaseEntity {

    @ManyToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "seq_no")
    private int seqNo;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}
