package yang.yu.tmall.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Pricings {

    Pricing save(Pricing pricing);

    Optional<Pricing> findLastByProduct(Product product, LocalDateTime time);

}
