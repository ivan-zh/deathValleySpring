package z.ivan.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import z.ivan.model.entity.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "select * from account where userId = ?1", nativeQuery = true)
    List<Account> findByUserIdEquals(Long userId);
}
