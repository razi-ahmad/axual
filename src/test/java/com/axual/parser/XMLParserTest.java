package com.axual.parser;

import com.axual.Data;
import com.axual.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class XMLParserTest {

    public static final String FILE_PATH = "src/test/resources/records.xml";
    private Parser underTest;

    @BeforeEach
    void setUp() {
        underTest = new XMLParser();
    }

    @Test
    void test_parse() {
        List<Record> records = underTest.parse(FILE_PATH);
        Assertions.assertArrayEquals(Data.buildRecordsXML().toArray(), records.toArray());
    }

    @Test
    void test_write() {
        boolean result = underTest.write(FILE_PATH, Data.buildFailedXML());
        Assertions.assertEquals(Boolean.TRUE, result);
    }
}