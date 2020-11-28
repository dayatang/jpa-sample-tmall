package yang.yu.tmall.repository.spring.buyers;

import yang.yu.tmall.domain.buyers.PersonalBuyer;

import java.util.Optional;

/**
 * 买家仓储特殊接口
 */
public interface BuyerOperations {

    /**
     * 根据QQ号码查找员工
     * @param qq QQ号码
     * @return 员工
     */
    Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq);
}
