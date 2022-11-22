package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final String UP = "U";

    private List<String> bridge;
    private List<Boolean> inputs;
    private int currentStage;
    private int retries;
    private boolean dead;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.dead = false;
        this.currentStage = 0;
        this.retries = 0;
        this.inputs = new ArrayList<>();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(boolean upOrDown) {
        // do sth else?
        if (bridge.get(currentStage).equals(UP) && !upOrDown) {
            this.dead = true;
        }
        if (!bridge.get(currentStage).equals(UP) && upOrDown) {
            this.dead = true;
        }
        this.inputs.add(upOrDown);
        this.currentStage++;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.retries++;
        this.currentStage = 0;
        this.dead = false;
    }

    public boolean alive() {
        return !this.dead;
    }

    public boolean succeeded() {
        if (this.dead) {
            return false;
        }
        if (this.bridge.size() > this.currentStage) {
            return false;
        }
        return true;
    }

    public int retries() {
        return this.retries;
    }

    /**
     * hitmap.at(i) == [1] [2] [3] [4]
     *                  O       X
     *                      O       X
     * @return
     */
    public List<Integer> hitmap() {
        var hitmap = new ArrayList<Integer>();
        for (int stage = 0; stage < currentStage; stage++) {
            String answer = this.bridge.get(stage);
            boolean userChosenUp = this.inputs.get(stage);
            boolean lastStage = stage == this.currentStage;

            int hit;
            if (lastStage) {
                if (this.dead) {
                    hit = answer.equals("U") ? 4 : 3;
                } else {
                    hit = answer.equals("U") ? 1 : 2;
                }
            }
            // 마지막이 아닌 경우
            if (answer.equals("U")) {
                if (userChosenUp) {
                    hit = 1;
                } else {
                    hit = 2;
                }
            } else {
                if (!userChosenUp) {
                    hit = 3;
                } else {
                    hit = 4;
                }
            }
            hitmap.add(hit);
        }
        return hitmap;
    }

    public boolean finished() {
        if (this.bridge.size() <= this.currentStage) {
            return true;
        }
        return !this.alive();
    }
}
