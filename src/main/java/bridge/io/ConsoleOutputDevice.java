package bridge.io;

public class ConsoleOutputDevice implements OutputDevice {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}
