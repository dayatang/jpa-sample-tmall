package yang.yu.tmall.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * 定价服务
 */
public interface PricingService {

    /**
     * 设定价格
     * @param productId 要定价的商品ID
     * @param unitPrice 单价
     * @param effectiveTime 生效时间
     */
    void setPrice(int productId, BigDecimal unitPrice, LocalDateTime effectiveTime);

    /**
     * 按百分比上调或下调价格
     * @param productId 要定价的商品ID
     * @param percentage 调整百分比。正数是上调，负数是下调
     * @param effectiveTime 生效时间
     */
    void adjustPriceByPercentage(int productId, int percentage, LocalDateTime effectiveTime);

    /**
     * 按百分比上调或下调价格
     * @param productIds 要定价的商品ID的集合
     * @param percentage 调整百分比。正数是上调，负数是下调
     * @param effectiveTime 生效时间
     */
    void adjustPriceByPercentage(Set<Integer> productIds, int percentage, LocalDateTime effectiveTime);

    /**
     * 获取商品在某个时刻的价格
     * @param productId 商品ID
     * @param time 时刻
     * @return 商品单价
     */
    BigDecimal priceAt(int productId, LocalDateTime time);

    /**
     * 批量获取商品在某个时刻的价格
     * @param productIds 要定价的商品ID的集合
     * @param time 时刻
     * @return 商品价格的映射，以商品ID为键，单价为值
     */
    Map<Integer, BigDecimal> priceAt(Set<Integer> productIds, LocalDateTime time);

    /**
     * 获取商品当前的单价
     * @param productId 商品ID
     * @return 商品单价
     */
    BigDecimal currentPrice(int productId);

    /**
     * 批量获取商品当前单价
     * @param productIds 要定价的商品ID的集合
     * @return 商品价格的映射，以商品ID为键，单价为值
     */
    Map<Integer, BigDecimal> currentPrice(Set<Integer> productIds);
}
