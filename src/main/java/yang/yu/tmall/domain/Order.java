package yang.yu.tmall.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private int version;

    private String orderNn;

    private LocalDateTime createdOn = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderLine> lineItems = new ArrayList<>();

    @ManyToMany
    private Buyer buyer;
}
