package bridge;

import bridge.io.OutputDevice;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private OutputDevice device;
    private Integer x;

    public OutputView(OutputDevice device) {
        this.device = device;
    }

    public void hello() {
        device.print("다리 건너기 게임을 시작합니다.");
    }

    public void a() {
        device.print("다리의 길이를 입력해주세요.");
    }

    public void b() {
        device.print("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void c() {
        device.print("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     *
     * hitmap.at(i) == [1] [2] [3] [4]
     *                  O       X
     *                      O       X
     */
    public void printMap(List<Integer> hitmap) {
        var firstLine = this.line(1, hitmap);
        var secondLine = this.line(2, hitmap);
        device.print(firstLine);
        device.print(secondLine);
    }

    private String line(int lineNumber, List<Integer> hitmap) {
        String result = "[";
        int stage;
        for (stage = 0; stage < hitmap.size() - 1; stage++) {
            String currentMark = this.currentMark(lineNumber, hitmap, stage);
            result += String.format(" %s |", currentMark);
        }
        String currentMark = this.currentMark(lineNumber, hitmap, stage);
        return result + String.format(" %s ]", currentMark);
    }

    private String currentMark(int lineNumber, List<Integer> hitmap, int stage) {
        int currentStageStatus = hitmap.get(stage);
        if (currentStageStatus == lineNumber) {
            return "O";
        }
        if (currentStageStatus == lineNumber + 2) {
            return "X";
        }
        return " ";
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean succeeded, List<Integer> hitmap, int retries) {
        var successMessage = this.successMessage(succeeded);

        device.print("최종 게임 결과");
        this.printMap(hitmap);
        device.print(String.format("게임 성공 여부: %s", successMessage));
        device.print(String.format("총 시도한 횟수: %d", retries));
    }

    private String successMessage(boolean succeeded) {
        if (succeeded) {
            return "성공";
        }
        return "실패";
    }
}
