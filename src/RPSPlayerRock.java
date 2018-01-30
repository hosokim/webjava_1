public class RPSPlayerRock extends RPSPlayerBase {
  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayerRock();
    player.go();
  }

  public RPSPlayerRock() {

  }

  @Override
  public RPSType go() {
    return RPSType.ROCK;
  }

  @Override
  public void onResult(boolean isWinner) {
    // nothing to do..
  }

}
