package com.dobosz.jakub;

import com.dobosz.jakub.logs.XMLLogsParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLParserTest {

    private static final ByteArrayOutputStream OUTPUT = new ByteArrayOutputStream();
    private static final int NUMBER_OF_OCCURRENCES = 5;

    @BeforeClass
    public static void setup() throws FileNotFoundException, XMLStreamException {
        XMLLogsParser parser = new XMLLogsParser();
        System.setOut(new PrintStream(OUTPUT));
        String path = XMLParserTest.class.getClassLoader().getResource("logs.xml").getPath();
        parser.parseLogs(path);
        parser.displayResults();
    }

    @AfterClass
    public static void clean() {
        System.setOut(null);
    }

    @Test
    public void should_contain_exactly_5_levels() {
        assertThat(StringUtils.countMatches(OUTPUT.toString(), "Level:")).isEqualTo(NUMBER_OF_OCCURRENCES);
    }

    @Test
    public void should_contain_2064_trace_logs() {
        assertThat(OUTPUT.toString()).contains("TRACE - 2064");
    }

    @Test
    public void should_contain_1993_debug_logs() {
        assertThat(OUTPUT.toString()).contains("DEBUG - 1993");
    }

    @Test
    public void should_contain_2078_info_logs() {
        assertThat(OUTPUT.toString()).contains("INFO - 2078");
    }

    @Test
    public void should_contain_1947_warning_logs() {
        assertThat(OUTPUT.toString()).contains("WARNING - 1947");
    }

    @Test
    public void should_contain_1918_error_logs() {
        assertThat(OUTPUT.toString()).contains("ERROR - 1918");
    }

    @Test
    public void should_contain_exactly_5_files() {
        assertThat(StringUtils.countMatches(OUTPUT.toString(), "File:")).isEqualTo(NUMBER_OF_OCCURRENCES);
    }

    @Test
    public void should_contain_699_facade_user_files() {
        assertThat(OUTPUT.toString()).contains("com/dobosz/jakub/xmllogs/facade/User.java - 699");
    }

    @Test
    public void should_contain_698_dao_company_files() {
        assertThat(OUTPUT.toString()).contains("com/dobosz/jakub/xmllogs/dao/Company.java - 698");
    }

    @Test
    public void should_contain_695_queue_user_files() {
        assertThat(OUTPUT.toString()).contains("com/dobosz/jakub/xmllogs/queue/User.java - 695");
    }

    @Test
    public void should_contain_689_facade_department_files() {
        assertThat(OUTPUT.toString()).contains("com/dobosz/jakub/xmllogs/facade/Department.java - 689");
    }

    @Test
    public void should_contain_676_entity_user_files() {
        assertThat(OUTPUT.toString()).contains("com/dobosz/jakub/xmllogs/entity/User.java - 676");
    }

    @Test
    public void should_contain_exactly_5_codes() {
        assertThat(StringUtils.countMatches(OUTPUT.toString(), "Code:")).isEqualTo(NUMBER_OF_OCCURRENCES);
    }

    @Test
    public void should_contain_1699_codes_number_182() {
        assertThat(OUTPUT.toString()).contains("182 - 1699");
    }

    @Test
    public void should_contain_883_codes_number_119() {
        assertThat(OUTPUT.toString()).contains("119 - 883");
    }

    @Test
    public void should_contain_866_codes_number_100() {
        assertThat(OUTPUT.toString()).contains("100 - 866");
    }

    @Test
    public void should_contain_846_codes_number_133() {
        assertThat(OUTPUT.toString()).contains("133 - 846");
    }

    @Test
    public void should_contain_841_codes_number_187() {
        assertThat(OUTPUT.toString()).contains("187 - 841");
    }
}
