package com.axual.parser;

import com.axual.exception.APIException;
import com.axual.exception.Error;
import com.axual.model.FailedTransaction;
import com.axual.model.Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CSVParser implements Parser {

    @Override
    public List<Record> parse(String filePath) throws APIException {
        validateFile(filePath);

        ArrayList<Record> records = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            //skip the header of the rows
            csvReader.readNext();

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(buildRecord(values));
            }
        } catch (CsvValidationException ex) {
            throw new APIException(Error.INVALID_CSV_FILE_FORMAT, ex);
        } catch (IOException ex) {
            throw new APIException(Error.FILE_READING_EXCEPTION, ex);
        }

        return records;
    }

    public boolean write(String filePath, Set<FailedTransaction> failedTransactions) {
        String csv = filePath.replace(".csv", "_failed.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(csv))) {
            for (FailedTransaction failed : failedTransactions) {
                String[] values = failed.toString().split(",");
                writer.writeNext(values);
                System.out.println(Arrays.toString(Arrays.stream(values).toArray()));
            }
        } catch (IOException ex) {
            throw new APIException(Error.FILE_WRITE_EXCEPTION, ex);
        }

        return Boolean.TRUE;
    }

    private Record buildRecord(String[] values) {
        return Record
                .builder()
                .trxReference(Long.valueOf(values[0]))
                .accountNumber(values[1])
                .description(values[2])
                .startBalance(Double.valueOf(values[3]))
                .mutation(Double.valueOf(values[4]))
                .endBalance(Double.valueOf(values[5]))
                .build();
    }

}
