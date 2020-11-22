package yang.yu.tmall.domain;

import jdk.jfr.Category;

import java.util.Optional;
import java.util.stream.Stream;

public interface Products {
    Optional<Product> getById(int id);
    Optional<Product> getByName(String name);
    Stream<Product> findByCategory(Category category);
}
