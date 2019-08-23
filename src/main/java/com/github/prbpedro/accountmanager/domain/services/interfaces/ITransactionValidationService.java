package com.github.prbpedro.accountmanager.domain.services.interfaces;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.jooq.DSLContext;

import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionalDataDto;

/**
 * Transaction validation service interface.
 * 
 * @author prbpe
 */
public interface ITransactionValidationService {

	/**
	 * Method responsible for validating a transfer transaction.
	 * 
	 * @param idAccountSender
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param codeCurrency
	 * @param ammount
	 * @param context
	 * @return TransferTransactionalDataDto
	 * @throws SQLException
	 */
	TransferTransactionalDataDto validateTransfer(
			String idAccountSender, 
			String idAccountBeneficiary, 
			String codeBankBeneficiary, 
			String codeCurrency, 
			BigDecimal ammount, 
			DSLContext context) throws SQLException ;
}
