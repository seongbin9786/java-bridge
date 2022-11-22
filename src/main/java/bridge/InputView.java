package bridge;

import bridge.io.InputDevice;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private InputDevice device;

    public InputView(InputDevice device) {
        this.device = device;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        var userInput = device.readLine();
        try {
            return this.bridgeSizeValidated(userInput);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 다리의 길이는 [3, 20]이어야 합니다.");
        }
    }

    private int bridgeSizeValidated(String userInput) throws Exception {
        var bridgeSize = Integer.parseInt(userInput);
        if (bridgeSize < 3 || bridgeSize > 20) {
            throw new Exception();
        }
        return bridgeSize;
    }

    /**
     * 사용자가 이동할 다음 칸이 Up인지 반환한다.
     *
     * @return Up 여부를 반환한다.
     */
    public boolean nextMoveUpward() {
        var userInput = device.readLine();
        if (userInput.equals("U")) {
            return true;
        }
        if (userInput.equals("D")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] 칸 선택은 U, D만 입력 가능합니다.");
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     *
     * @return 재시작 시 true, 종료 시 false
     */
    public boolean userWantsToRestart() {
        var userInput = device.readLine();
        if (userInput.equals("R")) {
            return true;
        }
        if (userInput.equals("Q")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] 게임 재시작 여부는 R, Q만 입력 가능합니다.");
    }
}
