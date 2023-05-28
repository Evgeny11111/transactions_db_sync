package edu.mipt.accounts.dblock;

import java.util.List;

public class Command {
    private final AccountRepository accountRepository;
    private final Account fromAcc;
    private final Account toAcc;
    private final long value;

    public Command(AccountRepository accountRepository, Account fromAcc, Account toAcc, long value) {
        this.accountRepository = accountRepository;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.value = value;
    }


    void doTransfer(){
        fromAcc.withdraw(value);
        toAcc.deposit(value);

        accountRepository.saveAllAndFlush(List.of(fromAcc, toAcc));
    }

}
