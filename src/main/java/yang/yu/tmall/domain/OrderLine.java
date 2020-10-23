package yang.yu.tmall.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
public class OrderLine {

    @ManyToOne
    private Product product;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
