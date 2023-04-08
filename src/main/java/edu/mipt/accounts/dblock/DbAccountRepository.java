package edu.mipt.accounts.dblock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbAccountRepository extends JpaRepository<Account, Long>, AccountRepository {

}
