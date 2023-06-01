package edu.mipt.accounts.dblock;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);
}
