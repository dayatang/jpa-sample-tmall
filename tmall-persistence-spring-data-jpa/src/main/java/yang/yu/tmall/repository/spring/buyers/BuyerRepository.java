package yang.yu.tmall.repository.spring.buyers;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.Buyers;

import javax.inject.Named;

/**
 * 买家仓储实现类。
 */
@Named
public interface BuyerRepository extends Buyers, JpaRepository<Buyer, Integer>, BuyerOperations {

}
