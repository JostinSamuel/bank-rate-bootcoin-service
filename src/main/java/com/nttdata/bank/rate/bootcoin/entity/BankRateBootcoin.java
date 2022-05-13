package com.nttdata.bank.rate.bootcoin.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "rateBootcoin")
public class BankRateBootcoin {

	@Id
	private Long idRateBootcoin;
	private Double purchaseRate;
	private Double sellingRate;
	private Double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;
	
	
}
