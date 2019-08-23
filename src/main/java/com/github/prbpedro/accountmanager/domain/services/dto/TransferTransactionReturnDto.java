package com.github.prbpedro.accountmanager.domain.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.prbpedro.accountmanager.domain.enums.TransferTransactionStatusEnum;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountTransactionRecord;

/**
 * Class responsible for containing the data returned for a transfer transaction.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class TransferTransactionReturnDto {
	
	private AccountTransactionRecord transferInformation;
	
	private TransferTransactionStatusEnum status;
	
	private List<String> messages;
	
	/**
	 * Constructor
	 */
	public TransferTransactionReturnDto() {
		messages = new ArrayList<String>();
	}

	/**
	 * transferInformation getter
	 * 
	 * @return AccountTransactionRecord
	 */
	public AccountTransactionRecord getTransferInformation() {
		return transferInformation;
	}

	/**
	 * transferInformation setter
	 * 
	 * @param transferInformation
	 */
	public void setTransferInformation(AccountTransactionRecord transferInformation) {
		this.transferInformation = transferInformation;
	}

	/**
	 * status getter
	 * 
	 * @return TransferTransactionStatusEnum
	 */
	public TransferTransactionStatusEnum getStatus() {
		return status;
	}

	/**
	 * status setter
	 * 
	 * @param status
	 */
	public void setStatus(TransferTransactionStatusEnum status) {
		this.status = status;
	}

	/**
	 * Method responsible for adding a message.
	 * 
	 * @param msg
	 */
	public void addMessage(String msg) {
		messages.add(msg);
	}
	
	/**
	 * status messages
	 * 
	 * @return List<String>
	 */
	public List<String> getMessages() {
		return messages;
	}
}
