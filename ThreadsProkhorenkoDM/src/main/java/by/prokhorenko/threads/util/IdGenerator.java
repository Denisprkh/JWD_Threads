package by.prokhorenko.threads.util;

public class IdGenerator {
    private static long id;
    private static int intId;

    public static long getId(){
        return ++id;
    }

    public static int getIntId(){
        return ++intId;
    }
}
