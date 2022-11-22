package bridge;

import bridge.io.ConsoleOutputDevice;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @Test
    public void printMap() {
        var view = new OutputView(new ConsoleOutputDevice());

        var hitmap = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4);
        view.printMap(hitmap);
    }

    @Test
    public void printResult() {
        var view = new OutputView(new ConsoleOutputDevice());

        var hitmap = Arrays.asList(1, 2);
        view.printResult(true, hitmap, 1);
    }
}
