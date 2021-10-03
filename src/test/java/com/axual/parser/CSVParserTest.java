package com.axual.parser;

import com.axual.Data;
import com.axual.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CSVParserTest {

    public static final String FILE_PATH = "src/test/resources/records.csv";
    private Parser underTest;

    @BeforeEach
    void setUp() {
        underTest = new CSVParser();
    }

    @Test
    void test_parse() {
        List<Record> records = underTest.parse(FILE_PATH);
        Assertions.assertArrayEquals(Data.buildRecordsCSV().toArray(), records.toArray());
    }

    @Test
    void write() {
        boolean result = underTest.write(FILE_PATH, Data.buildFailedCSV());
        Assertions.assertEquals(Boolean.TRUE, result);
    }

}