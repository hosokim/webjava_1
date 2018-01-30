import java.util.Random;

public class RPSPlayer extends RPSPlayerBase {
  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayer();
    player.go();
  }

  public RPSPlayer() {

  }

  @Override
  public RPSType go() {
    Random random = new Random();
    int intType = random.nextInt(3) + 1;
    switch(intType) {
      case 1:
        return RPSType.ROCK;
      case 2:
        return RPSType.SCISSORS;
      case 3:
        return RPSType.PAPER;
    }
    return RPSType.PAPER;
  }

  @Override
  public void onResult(boolean isWinner) {
    // nothing to do..
  }

}
