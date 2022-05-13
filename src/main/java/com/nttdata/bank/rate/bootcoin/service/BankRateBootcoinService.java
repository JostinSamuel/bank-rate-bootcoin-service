package com.nttdata.bank.rate.bootcoin.service;

import com.nttdata.bank.rate.bootcoin.entity.BankRateBootcoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankRateBootcoinService {

	/** Retorna todos los bankRateBootcoin registrados */
	Flux<BankRateBootcoin> findAll();

	/** Busqueda de un bankRateBootcoin por idBankRateBootcoin */
	Mono<BankRateBootcoin> findById(Long idBankRateBootcoin);

	/** Registra un nuevo bankRateBootcoin */
	Mono<BankRateBootcoin> save(BankRateBootcoin bankRateBootcoin);

	/** Actualiza un bankRateBootcoin */
	Mono<BankRateBootcoin> update(BankRateBootcoin bankRateBootcoin);

	/** Eliminacion de un bankRateBootcoin */
	Mono<Void> delete(Long idBankRateBootcoin);
	
}
