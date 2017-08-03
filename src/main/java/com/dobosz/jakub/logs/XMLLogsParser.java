package com.dobosz.jakub.logs;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
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
    private XMLEventReader eventReader;
    private Log log;

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
     * @throws XMLStreamException    if XML processing error occurred
     */
    public void parseLogs(String file) throws FileNotFoundException, XMLStreamException {
        this.eventReader = XMLInputFactory.newInstance().createXMLEventReader(new FileInputStream(file));

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) analyzeStartElement(event);
            if (event.isEndElement()) analyzeEndElement(event);
        }
    }

    private void analyzeStartElement(XMLEvent event) throws XMLStreamException {
        String elementName = event.asStartElement().getName().getLocalPart();

        switch (elementName) {
            case LOG:
                log = new Log();
                break;
            case LEVEL:
                log.setLevel(Level.valueOf(eventReader.getElementText()));
                break;
            case FILE:
                log.setFile(eventReader.getElementText());
                break;
            case CODE:
                log.setCode(Integer.parseInt(eventReader.getElementText()));
                break;
        }
    }

    private void analyzeEndElement(XMLEvent event) {
        String elementName = event.asEndElement().getName().getLocalPart();

        if (LOG.equals(elementName)) {
            listenable.setLog(log);
            log = null;
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