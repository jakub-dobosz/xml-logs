package com.dobosz.jakub;

import com.dobosz.jakub.logs.XMLLogsParser;

import java.io.FileNotFoundException;

public class XMLParserApplication {
    public static void main(String[] args) {

        if (args.length > 0) {
            String file = args[0];
            XMLLogsParser parser = new XMLLogsParser();

            try {
                parser.parseLogs(file);
                parser.displayResults();
            } catch (FileNotFoundException e) {
                System.out.println("Could not find file: " + file);
            }

        } else {
            System.out.println("No path to file specified");
        }
    }
}
