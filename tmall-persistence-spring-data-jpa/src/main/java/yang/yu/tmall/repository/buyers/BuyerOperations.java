package yang.yu.tmall.repository.buyers;

import yang.yu.tmall.domain.buyers.PersonalBuyer;

import java.util.Optional;

public interface BuyerOperations {
    Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq);
}
