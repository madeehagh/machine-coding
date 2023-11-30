package com.splitwise.respository;

import com.splitwise.entity.UserBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBalanceRepository extends CrudRepository<UserBalance, Double> {
     Optional<UserBalance> findByUserId(long parseLong);
}
