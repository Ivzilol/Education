package bakery.io;


import bakery.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
