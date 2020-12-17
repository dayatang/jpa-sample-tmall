package yang.yu.tmall.repository.spring.buyers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.Buyers;
import yang.yu.tmall.domain.buyers.ImType;
import yang.yu.tmall.domain.buyers.PersonalBuyer;

import javax.inject.Named;
import java.util.Optional;

/**
 * 买家仓储实现类。
 */
@Named
public interface BuyerRepository extends Buyers, JpaRepository<Buyer, Integer> {

    @Override
    default Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq) {
        return findPersonalBuyerByImInfo(ImType.QQ, qq);
    }

    @Query("select o from PersonalBuyer o join o.imInfos i where KEY(i) = :key and VALUE(i) = :value")
    Optional<PersonalBuyer> findPersonalBuyerByImInfo(@Param("key") ImType key, @Param("value") String value);

}
