package yang.yu.tmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.Product;
import yang.yu.tmall.domain.Products;

import javax.inject.Named;

@Named
public interface ProductRepository extends Products, JpaRepository<Product, Integer> {
}
