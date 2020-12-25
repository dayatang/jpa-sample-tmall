package yang.yu.tmall.domain.products;

import java.util.Optional;
import java.util.stream.Stream;

public interface Products {
    Optional<Product> getById(int id);
    Optional<Product> getByName(String name);
    Stream<Product> findByCategory(ProductCategory category);
}
