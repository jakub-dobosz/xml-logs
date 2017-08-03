package com.dobosz.jakub.logs;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * XMLLogsParser is a class that parses XML document with logs and displays its results.
 *
 * @author Jakub Dobosz
 * @version 1.0
 */
public class XMLLogsParser {
    private static final String LOG = "log";
    private static final String LEVEL = "level";
    private static final String FILE = "file";
    private static final String CODE = "code";
    private LogListenable listenable;
    private List<Listener> listeners;

    /**
     * Default constructor
     */
    public XMLLogsParser() {
        listenable = new LogListenable();
        listeners = new ArrayList<>();
        listeners.add(new LogLevelListener(listenable));
        listeners.add(new LogSourceFileListener(listenable));
        listeners.add(new LogCodeListener(listenable));
    }

    /**
     * Parse the given XML document with logs
     *
     * @param file path to XML document with logs
     * @throws FileNotFoundException if the given path is not a file
     */
    public void parseLogs(String file) throws FileNotFoundException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(file));

            Log log = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (LOG.equals(startElement.getName().getLocalPart())) {
                        log = new Log();
                    }

                    if (event.isStartElement()) {
                        String elementName = event.asStartElement().getName().getLocalPart();
                        if (LEVEL.equals(elementName)) {
                            log.setLevel(Level.valueOf(eventReader.getElementText()));
                            continue;
                        }

                        if (FILE.equals(elementName)) {
                            log.setFile(eventReader.getElementText());
                            continue;
                        }

                        if (CODE.equals(elementName)) {
                            log.setCode(Integer.parseInt(eventReader.getElementText()));
                            continue;
                        }
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (LOG.equals(endElement.getName().getLocalPart())) {
                        listenable.setLog(log);
                        log = null;
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display results of parsed XML document with logs:
     * <ul>
     * <li>Number of occurrences for each log level
     * <li>Top 5 most common files
     * <li>Top 5 most common error codes
     * </ul>
     */
    public void displayResults() {
        System.out.println("Logs parsing results:\n");
        listeners.forEach(Listener::display);
    }
}
