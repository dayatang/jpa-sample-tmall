package yang.yu.tmall.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import yang.yu.tmall.domain.*;
import yang.yu.tmall.spring.JpaSpringConfig;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JpaSpringConfig.class)
@Transactional
class OrdersTest implements WithAssertions {

    @Inject
    private Orders orders;

    @Inject
    private EntityManager entityManager;

    private Order order1, order2;

    private OrderLine lineItem1, lineItem2, lineItem3;

    private Product product1, product2;

    private PersonalBuyer buyer1;

    private OrgBuyer buyer2;

    @BeforeEach
    void beforeEach() {
        product1 = entityManager.merge(new Product("电冰箱", null));
        product2 = entityManager.merge(new Product("电视机", null));
        buyer1 = entityManager.merge(new PersonalBuyer("张三"));
        buyer2 = entityManager.merge(new OrgBuyer("华为公司"));
        lineItem1 = new OrderLine(product1, 3, Money.valueOf(3500));
        lineItem2 = new OrderLine(product1, 5, Money.valueOf(3500));
        lineItem3 = new OrderLine(product2, 3, Money.valueOf(8500));
        order1 = new Order();
        order1.setBuyer(buyer1);
        order1.addLineItem(lineItem1);
        order1.addLineItem(lineItem3);
        order1 = orders.save(order1);
        order2 = new Order();
        order2.setBuyer(buyer1);
        order2.addLineItem(lineItem2);
        order2 = orders.save(order2);
    }

    @AfterEach
    void afterEach() {
        Arrays.asList(product1, product2, buyer1, buyer2, order1, order2)
                .forEach(entityManager::remove);
    }

    @Test
    void getById() {
    }

    @Test
    void getByOrderNo() {
    }

    @Test
    void findByBuyer() {
    }

    @Test
    void findByCreatedBetween() {
    }

    @Test
    void findByBuyerAndCreatedBetween() {
    }

    @Test
    void findByBuyerAndStatus() {
    }

    @Test
    void findByProduct() {
    }
}