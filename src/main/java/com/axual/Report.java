package com.axual;

import com.axual.model.FailedTransaction;
import com.axual.model.Record;
import com.axual.parser.CSVParser;
import com.axual.parser.Parser;
import com.axual.parser.XMLParser;
import com.axual.validator.TransactionValidation;
import com.axual.validator.Validator;

import java.util.List;
import java.util.Set;

public class Report {
    public static boolean generateReport(String filePath, Parser.ParserType parserType) {
        Parser parser = parserType == Parser.ParserType.XML ? new XMLParser() : new CSVParser();
        List<Record> records = parser.parse(filePath);
        Validator validator = new TransactionValidation();
        Set<FailedTransaction> failedTransactions = validator.validate(records);
        return parser.write(filePath, failedTransactions);
    }
}
