package yang.yu.tmall.repository;

import org.springframework.data.repository.CrudRepository;
import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.Buyers;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface BuyerRepository extends Buyers, CrudRepository<Buyer, Integer> {

    @Override
    default void remove(Buyer buyer) {
        delete(buyer);
    }

}
