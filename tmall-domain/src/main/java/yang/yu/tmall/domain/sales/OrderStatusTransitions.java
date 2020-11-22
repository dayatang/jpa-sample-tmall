package yang.yu.tmall.domain.sales;

import java.util.stream.Stream;

public interface OrderStatusTransitions {

    Stream<OrderStatusTransition> findByOrder(Order order);
}
