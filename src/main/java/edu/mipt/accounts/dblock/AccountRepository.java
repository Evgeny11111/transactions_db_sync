package edu.mipt.accounts.dblock;

public interface AccountRepository {
    Account findById(long id);
}