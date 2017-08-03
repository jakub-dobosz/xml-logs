package com.dobosz.jakub;

import com.dobosz.jakub.logs.XMLLogsParser;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class XMLParserTest {

    private XMLLogsParser parser;

    @Before
    public void setup() {
        parser = new XMLLogsParser();
    }

    @Test
    public void test() {
        String path = getClass().getClassLoader().getResource("logs.xml").getPath();
        try {
            parser.parseLogs(path);
            parser.displayResults();
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
