package yang.yu.tmall.repository.spring.products;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.products.Products;

import javax.inject.Named;

/**
 * 产品仓储的实现
 */
@Named
public interface ProductRepository extends Products, JpaRepository<Product, Integer> {
}
