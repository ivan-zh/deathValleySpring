package z.ivan.model.dao;

import org.springframework.data.repository.CrudRepository;
import z.ivan.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
