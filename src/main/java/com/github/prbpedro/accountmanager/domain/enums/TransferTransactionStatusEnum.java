package com.github.prbpedro.accountmanager.domain.enums;

/**
 * Enum that contains the possible status for the transfer transaction.
 * 
 * @author prbpe
 */
public enum TransferTransactionStatusEnum {
	/**
	 * Processed transfer's status.
	 */
	PROCESSED,
	
	/**
	 * Not processed transfer's status.
	 */
	NOT_PROCESSED,
	
	/**
	 * Transfer's that generated erros while beeing processed.
	 */
	ERROR_PROCESSING
}
