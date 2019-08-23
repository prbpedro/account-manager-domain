package com.github.prbpedro.accountmanager.domain.services.dto;

import com.github.prbpedro.accountmanager.domain.tables.records.AccountBalanceRecord;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountRecord;
import com.github.prbpedro.accountmanager.domain.tables.records.BankRecord;
import com.github.prbpedro.accountmanager.domain.tables.records.CurrencyRecord;

/**
 * Class that contains the data used during a transfer execution.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class TransferTransactionalDataDto {

	private AccountRecord senderAccount;
	
	private AccountRecord beneficiaryAccount;
	
	private CurrencyRecord currency;
	
	private BankRecord beneficiaryBank;
	
	private AccountBalanceRecord senderAcountBalance;
	
	private AccountBalanceRecord beneficiaryAccountBalance;
	
	private TransferTransactionReturnDto returnDto;

	/**
	 * senderAccount getter
	 * 
	 * @return AccountRecord
	 */
	public AccountRecord getSenderAccount() {
		return senderAccount;
	}

	/**
	 * senderAccount setter
	 * 
	 * @param senderAccount
	 */
	public void setSenderAccount(AccountRecord senderAccount) {
		this.senderAccount = senderAccount;
	}

	/**
	 * beneficiaryAccount getter
	 * 
	 * @return AccountRecord
	 */
	public AccountRecord getBeneficiaryAccount() {
		return beneficiaryAccount;
	}

	/**
	 * beneficiaryAccount setter
	 * 
	 * @param beneficiaryAccount
	 */
	public void setBeneficiaryAccount(AccountRecord beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}

	/**
	 * currency getter
	 * 
	 * @return CurrencyRecord
	 */
	public CurrencyRecord getCurrency() {
		return currency;
	}

	/**
	 * currency setter
	 * 
	 * @param currency
	 */
	public void setCurrency(CurrencyRecord currency) {
		this.currency = currency;
	}

	/**
	 * beneficiaryBank getter
	 * 
	 * @return BankRecord
	 */
	public BankRecord getBeneficiaryBank() {
		return beneficiaryBank;
	}

	/**
	 * beneficiaryBank setter
	 * 
	 * @param beneficiaryBank
	 */
	public void setBeneficiaryBank(BankRecord beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}

	/**
	 * senderAcountBalance getter
	 * 
	 * @return AccountBalanceRecord
	 */
	public AccountBalanceRecord getSenderAcountBalance() {
		return senderAcountBalance;
	}

	/**
	 * senderAcountBalance setter
	 * 
	 * @param senderAcountBalance
	 */
	public void setSenderAcountBalance(AccountBalanceRecord senderAcountBalance) {
		this.senderAcountBalance = senderAcountBalance;
	}

	/**
	 * beneficiaryAccountBalance getter
	 * 
	 * @return AccountBalanceRecord
	 */
	public AccountBalanceRecord getBeneficiaryAccountBalance() {
		return beneficiaryAccountBalance;
	}

	/**
	 * beneficiaryAccountBalance setter
	 * 
	 * @param beneficiaryAccountBalance
	 */
	public void setBeneficiaryAccountBalance(AccountBalanceRecord beneficiaryAccountBalance) {
		this.beneficiaryAccountBalance = beneficiaryAccountBalance;
	}
	
	/**
	 * returnDto getter
	 * 
	 * @return TransferTransactionReturnDto
	 */
	public TransferTransactionReturnDto getReturnDto() {
		return returnDto;
	}
	
	/**
	 * returnDto setter
	 * 
	 * @param returnDto
	 */
	public void setReturnDto(TransferTransactionReturnDto returnDto) {
		this.returnDto = returnDto;
	}
}
