package bridge.io;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputDevice implements InputDevice {

    @Override
    public String readLine() {
        return Console.readLine();
    }
}
