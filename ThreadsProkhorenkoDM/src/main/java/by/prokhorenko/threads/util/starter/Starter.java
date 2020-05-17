package by.prokhorenko.threads.util.starter;

import java.util.List;

public interface Starter <T>{
    void start(List<T> threads);
}
