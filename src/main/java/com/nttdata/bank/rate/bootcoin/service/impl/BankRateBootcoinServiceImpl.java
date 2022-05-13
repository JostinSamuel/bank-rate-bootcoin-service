package com.nttdata.bank.rate.bootcoin.service.impl;

import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bank.rate.bootcoin.entity.BankRateBootcoin;
import com.nttdata.bank.rate.bootcoin.repository.BankRateBootcoinRepository;
import com.nttdata.bank.rate.bootcoin.service.BankRateBootcoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankRateBootcoinServiceImpl implements BankRateBootcoinService{
	
	@Autowired
	private BankRateBootcoinRepository rateBootcoinRepository;

	@Override
	public Flux<BankRateBootcoin> findAll() {
		// TODO Auto-generated method stub
		return rateBootcoinRepository.findAll();
	}

	@Override
	public Mono<BankRateBootcoin> findById(Long idBankRateBootcoin) {
		// TODO Auto-generated method stub
		return rateBootcoinRepository.findById(idBankRateBootcoin);
	}

	@Override
	public Mono<BankRateBootcoin> save(BankRateBootcoin bankRateBootcoin) {
		// TODO Auto-generated method stub
				Long count = this.findAll().collect(Collectors.counting()).blockOptional().get();
				Long idBankRateBootcoin;
				if (count != null) {
					if (count <= 0) {
						idBankRateBootcoin = Long.valueOf(0);
					} else {
						idBankRateBootcoin = this.findAll().collect(Collectors.maxBy(Comparator.comparing(BankRateBootcoin::getIdRateBootcoin)))
								.blockOptional().get().get().getIdRateBootcoin();
					}

				} else {
					idBankRateBootcoin = Long.valueOf(0);

				}
				bankRateBootcoin.setDate(Calendar.getInstance().getTime());
				bankRateBootcoin.setIdRateBootcoin(idBankRateBootcoin + 1);
				return rateBootcoinRepository.insert(bankRateBootcoin);
	}

	@Override
	public Mono<BankRateBootcoin> update(BankRateBootcoin bankRateBootcoin) {
		// TODO Auto-generated method stub
		return rateBootcoinRepository.save(bankRateBootcoin);
	}

	@Override
	public Mono<Void> delete(Long idBankRateBootcoin) {
		// TODO Auto-generated method stub
		return rateBootcoinRepository.deleteById(idBankRateBootcoin);
	}
}
