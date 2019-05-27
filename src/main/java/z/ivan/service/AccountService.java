package z.ivan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import z.ivan.model.dao.AccountRepository;
import z.ivan.model.dao.exception.DaoException;
import z.ivan.model.entity.Account;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static z.ivan.service.utilites.SumAccounts.sumAccounts;

@Service
public class AccountService {
    private static final Logger LOG = Logger.getLogger(AccountService.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    public AccountService() {
    }

    public Account get(Long id) throws DaoException {
        return accountRepository.findById(id).orElse(new Account());
    }

    public List<Account> getAll() throws DaoException {
        return (List<Account>) accountRepository.findAll();
    }

    public void save(Account account) throws DaoException {
        accountRepository.save(account);
    }

    public void save(Collection<Account> accounts) throws DaoException {
        accountRepository.saveAll(accounts);
    }

    public void update(Account account) throws DaoException {
        save(account);
    }

    public void delete(Long id) throws DaoException {
        accountRepository.deleteById(id);
    }

    public Long getTotal() {
        try {
            return sumAccounts(getAll());
        } catch (DaoException e) {
            LOG.warning(e.getMessage());
        }
        return 0L;
    }
}
