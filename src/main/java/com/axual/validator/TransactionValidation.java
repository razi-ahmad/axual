package com.axual.validator;

import com.axual.model.FailedTransaction;
import com.axual.model.Record;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransactionValidation implements Validator {

    public static final String DUPLICATE_RECORD = "This transaction has duplicate records";
    public static final String INVALID_END_BALANCE = "Invalid end balance of the transaction";

    @Override
    public Set<FailedTransaction> validate(List<Record> records) {
        Set<Record> references = new HashSet<>();
        Set<FailedTransaction> failedTransactions = new HashSet<>();

        for (Record record : records) {
            boolean isAlreadyExist = references.add(record);
            if (!isAlreadyExist) {
                failedTransactions.add(
                        FailedTransaction
                                .builder()
                                .reference(record.getTrxReference())
                                .description(DUPLICATE_RECORD)
                                .build()
                );

            } else if (BigDecimal.valueOf(record.getStartBalance()).add(BigDecimal.valueOf(record.getMutation()))
                    .compareTo(BigDecimal.valueOf(record.getEndBalance())) != 0) {
                failedTransactions.add(
                        FailedTransaction
                                .builder()
                                .reference(record.getTrxReference())
                                .description(INVALID_END_BALANCE)
                                .build()
                );
            }
        }

        return failedTransactions;
    }


}
