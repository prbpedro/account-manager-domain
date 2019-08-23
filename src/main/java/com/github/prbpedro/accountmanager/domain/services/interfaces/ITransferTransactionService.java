package com.github.prbpedro.accountmanager.domain.services.interfaces;

import java.math.BigDecimal;

import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionReturnDto;

/**
 * Transfer transaction service interface.
 * 
 * @author prbpe
 */
public interface ITransferTransactionService {
	
	/**
	 * Method responsible for executing a transfer.
	 * 
	 * @param idAccountSender
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param codeCurrency
	 * @param ammount
	 * @return TransferTransactionReturnDto
	 */
	TransferTransactionReturnDto doTransferTransaction(String idAccountSender, String idAccountBeneficiary, String codeBankBeneficiary, String codeCurrency, BigDecimal ammount);
}
