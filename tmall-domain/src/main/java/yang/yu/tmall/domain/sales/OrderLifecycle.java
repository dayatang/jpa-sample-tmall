package yang.yu.tmall.domain.sales;

import yang.yu.lang.IoC;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderLifecycle {

    private Order order;

    private OrderStatusTransitions transitions;

    private OrderLifecycle(Order order) {
        this.order = order;
    }

    public void setTransitions(OrderStatusTransitions transitions) {
        this.transitions = transitions;
    }

    private OrderStatusTransitions getTransitions() {
        return Optional.ofNullable(transitions)
                .orElse(IoC.getInstance(OrderStatusTransitions.class));
    }

    public OrderStatusTransition getCurrentTransition() {
        List<OrderStatusTransition> transitionList = getTransitionList();
        return transitionList.get(transitionList.size() - 1);
    }

    public OrderStatus getCurrentStatus() {
        return getCurrentTransition().getStatus();
    }

    public List<OrderStatusTransition> getTransitionList() {
        return getTransitions().findByOrder(order)
                .sorted(Comparator.comparing(OrderStatusTransition::getSeqNo))
                .collect(Collectors.toList());
    }

    public static OrderLifecycle of(Order order) {
        return new OrderLifecycle(order);
    }
}
