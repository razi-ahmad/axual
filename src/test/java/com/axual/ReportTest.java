package com.axual;

import com.axual.exception.APIException;
import com.axual.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReportTest {

    @Test
    void test_generate_report_default() {
        boolean result = Report.generateReport("src/test/resources/records.csv", null);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void test_generate_report_xml() {
        boolean result = Report.generateReport("src/test/resources/records.xml", Parser.ParserType.XML);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void test_generate_report_csv() {
        boolean result = Report.generateReport("src/test/resources/records.csv", Parser.ParserType.CSV);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void test_generate_report_parameter_is_null_csv() {
        Assertions.assertThrows(APIException.class, () -> Report.generateReport(null, Parser.ParserType.CSV));
    }

    @Test
    void test_generate_report_parameter_is_null_xml() {
        Assertions.assertThrows(APIException.class, () -> Report.generateReport(null, Parser.ParserType.XML));
    }
}