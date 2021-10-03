package com.axual.validator;

import com.axual.Data;
import com.axual.model.FailedTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class TransactionValidationTest {

    private Validator underTest;

    @BeforeEach
    void setUp() {
        underTest = new TransactionValidation();
    }

    @Test
    void test_validate_csv() {
        Set<FailedTransaction> result = underTest.validate(Data.buildRecordsCSV());
        List<FailedTransaction> failedTransactions = new ArrayList<>(result);

        Assertions.assertEquals(1, failedTransactions.size());
        Assertions.assertEquals(112806, failedTransactions.get(0).getReference());
        Assertions.assertEquals(TransactionValidation.DUPLICATE_RECORD, failedTransactions.get(0).getDescription());
    }

    @Test
    void test_validate_xml() {
        Set<FailedTransaction> result = underTest.validate(Data.buildRecordsXML());
        List<FailedTransaction> failedTransactions = new ArrayList<>(result);

        Assertions.assertEquals(2, failedTransactions.size());
        Assertions.assertEquals(TransactionValidation.INVALID_END_BALANCE, failedTransactions.get(0).getDescription());
        Assertions.assertEquals(TransactionValidation.INVALID_END_BALANCE, failedTransactions.get(0).getDescription());
    }


}