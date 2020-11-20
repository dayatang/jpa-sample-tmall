package yang.yu.tmall.domain;

import java.util.List;
import java.util.stream.Stream;

public interface OrderStatusTransitions {

    Stream<OrderStatusTransition> findByOrder(Order order);
}
