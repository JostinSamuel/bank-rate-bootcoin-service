package com.nttdata.bank.rate.bootcoin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bank.rate.bootcoin.entity.BankRateBootcoin;

@Repository
public interface BankRateBootcoinRepository extends ReactiveMongoRepository<BankRateBootcoin, Long>{

}
