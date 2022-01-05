package yang.yu.tmall.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.OrgBuyer;
import yang.yu.tmall.domain.buyers.PersonalBuyer;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.pricing.PricingService;
import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.OrderFactory;
import yang.yu.tmall.domain.sales.OrderLine;
import yang.yu.tmall.domain.sales.Orders;
import yang.yu.tmall.repository.jpa.OrderRepository;
import yang.yu.tmall.repository.jpa.PricingRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;

@Transactional
public class OrderRepositoryTest extends BaseIntegrationTest {

    private Orders orders;

    private PricingService pricingService;

    private OrderFactory orderFactory;

    private Order order1, order2, order3;

    private OrderLine lineItem1, lineItem2, lineItem3, lineItem4;

    private Product product1, product2;

    private PersonalBuyer buyer1;

    private OrgBuyer buyer2;

    @BeforeEach
    void beforeEach() {
        orders = new OrderRepository(entityManager);
        PricingRepository pricingRepository = new PricingRepository(entityManager);
        pricingService = new PricingService(pricingRepository);
        orderFactory = new OrderFactory(pricingService);

        product1 = entityManager.merge(new Product("电冰箱", null));
        product2 = entityManager.merge(new Product("电视机", null));
        pricingService.setPrice(product1, Money.valueOf(3500), LocalDateTime.now());
        pricingService.setPrice(product2, Money.valueOf(8500), LocalDateTime.now());

        buyer1 = entityManager.merge(new PersonalBuyer("张三"));
        buyer2 = entityManager.merge(new OrgBuyer("华为公司"));
        lineItem1 = new OrderLine(product1, 3, Money.valueOf(3500));
        lineItem2 = new OrderLine(product1, 5, Money.valueOf(3500));
        lineItem3 = new OrderLine(product2, 3, Money.valueOf(8500));
        lineItem4 = new OrderLine(product2, 2, Money.valueOf(8500));
        order1 = createOrder("order1", buyer1, lineItem1, lineItem3);
        order2 = createOrder("order2", buyer1, lineItem2);
        order3 = createOrder("order3", buyer2, lineItem2, lineItem3);
    }

    private Order createOrder(String orderNo, Buyer buyer, OrderLine... orderLines) {
        Order order = new Order(orderNo, Arrays.asList(orderLines), buyer, null);
        //Arrays.stream(orderLines).forEach(order::addLineItem);
        return entityManager.merge(order);
    }

    @AfterEach
    void afterEach() {
        Arrays.asList(order1, order2, order3)
                .forEach(orders::delete);
        Arrays.asList(product1, product2, buyer1, buyer2)
                .forEach(entityManager::remove);
    }

    @Test
    void getById() {
        Arrays.asList(order1, order2).forEach(order -> {
            assertThat(orders.getById(order.getId())).containsSame(order);
        });
    }

    @Test
    void getByOrderNo() {
        Arrays.asList(order1, order2).forEach(order -> {
            assertThat(orders.getByOrderNo(order.getOrderNo())).containsSame(order);
        });
    }

    @Test
    void findByBuyer() {
        assertThat(orders.findByBuyer(buyer1)).hasSize(2).allMatch(order -> order.getBuyer().equals(buyer1));
    }

    @Test
    void findByProduct() {
        assertThat(orders.findByProduct(product2))
                .contains(order1)
                .doesNotContain(order2);
    }

    @Test
    void findByOrgBuyers() {
        assertThat(orders.findByOrgBuyers())
                .contains(order3)
                .doesNotContain(order1, order2);
    }
}