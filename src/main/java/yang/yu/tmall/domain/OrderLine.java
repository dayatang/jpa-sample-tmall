package yang.yu.tmall.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class OrderLine {
    private Product product;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
