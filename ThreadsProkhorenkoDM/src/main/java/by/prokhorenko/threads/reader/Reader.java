package by.prokhorenko.threads.reader;

import by.prokhorenko.threads.exception.ReaderException;

import java.util.List;

public interface Reader {
    List<String> read(String path) throws ReaderException;
}
