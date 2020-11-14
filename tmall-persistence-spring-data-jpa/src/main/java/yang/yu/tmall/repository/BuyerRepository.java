package yang.yu.tmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.Buyers;

import javax.inject.Named;

@Named
public interface BuyerRepository extends Buyers, JpaRepository<Buyer, Integer> {

}
