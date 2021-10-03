package com.axual.parser;

import com.axual.exception.APIException;
import com.axual.exception.Error;
import com.axual.model.FailedTransaction;
import com.axual.model.Record;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface Parser {

    enum ParserType {
        XML,
        CSV
    }

    /**
     * @param filePath of the input data
     */
    default void validateFile(String filePath) throws APIException {
        if (Objects.isNull(filePath)) {
            throw new APIException(Error.FILE_NAME_IS_MISSING);
        }
    }

    /**
     * @param filePath of the input data
     * @return the list of records
     * @throws APIException in case of parsing
     */
    List<Record> parse(String filePath) throws APIException;

    /**
     * @param filePath           of the output data
     * @param failedTransactions in the input file
     * @return ture if file is write successfully
     */
    boolean write(String filePath, Set<FailedTransaction> failedTransactions);
}