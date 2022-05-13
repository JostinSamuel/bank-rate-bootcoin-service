package com.nttdata.bank.rate.bootcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.rate.bootcoin.entity.BankRateBootcoin;
import com.nttdata.bank.rate.bootcoin.service.BankRateBootcoinService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/api/v1/bank/rate")
public class BankRateBootcoinController {
	
	@Autowired
	private BankRateBootcoinService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<BankRateBootcoin> findAll() {
		return service.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ResponseEntity<BankRateBootcoin>> save(@RequestBody BankRateBootcoin bankRateBootcoin) {
		return service.save(bankRateBootcoin).map(_bankRateBootcoin -> ResponseEntity.ok().body(_bankRateBootcoin)).onErrorResume(e -> {
			log.info("Error:" + e.getMessage());
			return Mono.just(ResponseEntity.badRequest().build());
		});
	}
	
	@GetMapping("/{idBankRateBootcoin}")
	public Mono<ResponseEntity<BankRateBootcoin>> findById(@PathVariable(name = "idBankRateBootcoin") Long idBankRateBootcoin) {
		return service.findById(idBankRateBootcoin).map(bankRateBootcoin -> ResponseEntity.ok().body(bankRateBootcoin)).onErrorResume(e -> {
			log.info("Error:" + e.getMessage());
			return Mono.just(ResponseEntity.badRequest().build());
		}).defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PutMapping
	public Mono<ResponseEntity<BankRateBootcoin>> update(@RequestBody BankRateBootcoin bankRateBootcoin) {
		Mono<BankRateBootcoin> mono = service.findById(bankRateBootcoin.getIdRateBootcoin()).flatMap(objBankRateBootcoin -> {
			return service.update(bankRateBootcoin);
		});
		return mono.map(_bankRateBootcoin -> {
			return ResponseEntity.ok().body(_bankRateBootcoin);
		}).onErrorResume(e -> {
			log.info("Error:" + e.getMessage());
			return Mono.just(ResponseEntity.badRequest().build());
		}).defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@DeleteMapping("/{idBankRateBootcoin}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(name = "idBankRateBootcoin") Long idBankRateBootcoin) {
		Mono<BankRateBootcoin> _bankRateBootcoin = service.findById(idBankRateBootcoin);
		_bankRateBootcoin.subscribe();
		BankRateBootcoin bankRateBootcoin=_bankRateBootcoin.toFuture().join();
		if (bankRateBootcoin != null) {
			return service.delete(idBankRateBootcoin).map(r -> ResponseEntity.ok().<Void>build());
		} else {
			return Mono.just(ResponseEntity.noContent().build());
		} 
	}
}
