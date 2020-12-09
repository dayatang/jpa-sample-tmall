package yang.yu.tmall.domain.pricing;

import yang.yu.tmall.domain.products.Product;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 定价仓储接口
 */
public interface Pricings {

    /**
     * 保存定价信息
     * @param pricing 要保存的定价信息
     * @return 持久化后的定价信息
     */
    Pricing save(Pricing pricing);

    /**
     * 获得指定产品在指定时间的定价信息
     * @param product 产品
     * @param time 时间
     * @return 当时的产品价格
     */
    Optional<Pricing> getPricingAt(Product product, LocalDateTime time);

    /**
     * 查找指定商品的定价历史，按生效时间排序。
     * @param product 产品
     * @return 指定产品按时间排序的定价历史列表
     */
    Stream<Pricing> findPricingListByProduct(Product product);

}
