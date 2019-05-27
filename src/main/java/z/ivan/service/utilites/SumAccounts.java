package z.ivan.service.utilites;

import z.ivan.model.entity.Account;

import java.util.Collection;

public class SumAccounts {

    public static Long sumAccounts(Collection<Account> accounts) {
        return accounts.stream().map(Account::getAmount).reduce(0L, Long::sum);
    }
}
