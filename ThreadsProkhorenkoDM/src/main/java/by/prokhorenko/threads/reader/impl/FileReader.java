package by.prokhorenko.threads.reader.impl;

import by.prokhorenko.threads.exception.ReaderException;
import by.prokhorenko.threads.reader.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader implements Reader {


    private static final Logger LOG = LogManager.getLogger();

    @Override
    public List<String> read(String link) throws ReaderException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceURL = classLoader.getResource(link);
        URI resourceURI;
        try {
            resourceURI = resourceURL.toURI();
        } catch (URISyntaxException uEx) {
            LOG.error("converting resource URL to URI error");
            throw new ReaderException(uEx);
        }
        List<String> lines;
        try {
            Path path = Paths.get(resourceURI);
            Stream<String> stream = Files.lines(path);
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("File reading error ");
            throw new ReaderException("File reading error");
        }
        LOG.info("Lines were read");
        return lines;
    }
}
