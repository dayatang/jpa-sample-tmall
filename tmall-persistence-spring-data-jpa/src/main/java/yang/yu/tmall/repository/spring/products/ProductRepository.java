package yang.yu.tmall.repository.spring.products;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.catalogue.Product;
import yang.yu.tmall.domain.catalogue.Products;

import javax.inject.Named;

/**
 * 商品仓储的实现
 */
@Named
public interface ProductRepository extends Products, JpaRepository<Product, Integer> {
}
