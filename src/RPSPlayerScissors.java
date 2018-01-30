public class RPSPlayerScissors extends RPSPlayerBase {
  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayerScissors();
    player.go();
  }

  public RPSPlayerScissors() {

  }

  @Override
  public RPSType go() {
    return RPSType.SCISSORS;
  }

  @Override
  public void onResult(boolean isWinner) {
    // nothing to do..
  }

}
