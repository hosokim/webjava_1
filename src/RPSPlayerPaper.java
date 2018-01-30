public class RPSPlayerPaper extends RPSPlayerBase {
  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayerPaper();
    player.go();
  }

  public RPSPlayerPaper() {

  }

  @Override
  public RPSType go() {
    return RPSType.PAPER;
  }

  @Override
  public void onResult(boolean isWinner) {
    // nothing to do..
  }

}
