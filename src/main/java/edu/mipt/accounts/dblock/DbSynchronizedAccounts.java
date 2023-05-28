package edu.mipt.accounts.dblock;

import edu.mipt.accounts.Accounts;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbSynchronizedAccounts implements Accounts {
    private final AccountRepository accountRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Retryable(maxAttempts = 20)
    public void transfer(long fromAccountId, long toAccountId, long amount) {
        var fromAccount = accountRepository.findById(fromAccountId);
        var toAccount = accountRepository.findById(toAccountId);

        var transaction = new Command(accountRepository, fromAccount, toAccount, amount);
        var lock = new Lock();

        lock.execute(fromAccount,toAccount,transaction);
    }

}