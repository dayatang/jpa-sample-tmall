package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.commons.BaseEntity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 订单状态迁移实体，记录某个时间点订单进入某个状态。
 */
@Entity
@Table(name = "order_status_transitions")
public class OrderStatusTransition extends BaseEntity {

    @ManyToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime occurredOn = LocalDateTime.now();

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

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(LocalDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

}
