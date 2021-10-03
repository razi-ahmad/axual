package com.axual.validator;

import com.axual.model.FailedTransaction;
import com.axual.model.Record;

import java.util.List;
import java.util.Set;

public interface Validator {

    /**
     * @param records from the input file
     * @return list of failed transactions
     */
    Set<FailedTransaction> validate(List<Record> records);
}
