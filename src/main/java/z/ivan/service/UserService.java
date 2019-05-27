package z.ivan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import z.ivan.model.dao.AccountRepository;
import z.ivan.model.dao.UserRepository;
import z.ivan.model.dao.exception.DaoException;
import z.ivan.model.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static z.ivan.service.utilites.SumAccounts.sumAccounts;

@Service
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    public UserService() {
    }

    public String getRichest() {
        List<User> users;
        try {
            users = getAll();
            User richest = users.get(0);
            for (int i = 1; i < users.size(); i++) {
                User user = users.get(i);
                Long currentSum = sumAccounts(accountRepository.findByUserIdEquals(user.getId()));
                if (currentSum > sumAccounts(accountRepository.findByUserIdEquals(richest.getId()))) {
                    richest = user;
                }
            }
            return richest.getFullName();
        } catch (DaoException e) {
            LOG.warning(e.getMessage());
        }
        return "";
    }

    public User get(final Long id) throws DaoException {
        User user = userRepository.findById(id).orElse(new User());
        return user;
    }

    public List<User> getAll() throws DaoException {
        return (List<User>) userRepository.findAll();
    }

    public void save(final User user) throws DaoException {
        userRepository.save(user);
    }

    public void save(Collection<User> users) throws DaoException {
        userRepository.saveAll(users);
    }

    public void update(final User user) throws DaoException {
        save(user);
    }

    public void delete(final Long id) throws DaoException {
        userRepository.deleteById(id);
    }


}
