package bridge;

import bridge.io.ConsoleInputDevice;
import bridge.io.ConsoleOutputDevice;

public class Application {

    private BridgeGame game;
    private BridgeMaker maker;
    private InputView input;
    private OutputView output;

    public Application() {
        this.input = new InputView(new ConsoleInputDevice());
        this.output = new OutputView(new ConsoleOutputDevice());
        this.maker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public void run() {
        this.output.promptBridgeSize();
        var bridgeSize = this.input.readBridgeSize();
        var bridge = this.maker.makeBridge(bridgeSize);
        this.game = new BridgeGame(bridge);

        retryLoop();
    }

    private void retryLoop() {
        while (true) {
            play();

            this.output.promptRetry();
            var restarting = this.input.userWantsToRestart();
            if (!restarting) {
                var succeeded = this.game.succeeded();
                var hitmap = this.game.hitmap();
                var retries = this.game.retries();
                this.output.printResult(succeeded, hitmap, retries + 1);
                break;
            }
        }
    }

    private void play() {
        while (!this.game.finished()) {
            this.output.promptNextMove();
            var nextMove = this.input.nextMoveUpward();
            this.game.move(nextMove);
            var hitmap = this.game.hitmap();
            this.output.printMap(hitmap);
        }
    }

    public static void main(String[] args) {
        var app = new Application();
        app.run();
    }
}
