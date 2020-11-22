package yang.yu.tmall.domain;

import java.util.stream.Stream;

public interface OrderStatusTransitions {

    Stream<OrderStatusTransition> findByOrder(Order order);
}
