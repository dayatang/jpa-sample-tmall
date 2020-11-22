package yang.yu.tmall.repository.buyers;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.Buyers;

import javax.inject.Named;

@Named
public interface BuyerRepository extends Buyers, JpaRepository<Buyer, Integer>, BuyerOperations {

}
