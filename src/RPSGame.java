import java.util.Scanner;

public class RPSGame {
  private static final int SAME = 0;
  private static final int PLAYER1_WIN = 1;
  private static final int PLAYER2_WIN = 2;
  private Scanner mScan;

  public static void main(String[] args) {
    RPSGame game = RPSGame.getInstance();
    game.startGame();
  }

  private static RPSGame getInstance() {
    return new RPSGame();
  }

  private void startGame() {
    mScan = new Scanner(System.in);
    try {
      do {
        System.out.println("********************************************");
        System.out.println("じゃんけんゲーム");
        System.out.println("　m:参加する（マニュアル）");
        System.out.println("　a:観戦する（オート）");
        System.out.println("　e:やめる");
        System.out.println("********************************************");

        String input = mScan.next();

        switch (input) {
          case "m":
            manual();
            break;
          case "a":
            auto();
            break;
          case "e":
            System.out.println("-----終了-----");
            return;
          default:
            System.out.println("m/a/eのいずれかを入力してください");
            break;
        }

      } while (true);
    } finally {
      mScan.close();
    }
  }

  private void manual() {

    //NPCの生成
    RPSPlayerBase npc = new RPSPlayer();

    System.out.println("********************************************");
    System.out.println("★マニュアルモード★");
    int count = 1;
    int player1WinCount = 0;
    int player2WinCount = 0;
    do {
      System.out.println("★第"+count+"回戦★");
      System.out.println("　1:グー");
      System.out.println("　2:チョキ");
      System.out.println("　3:パー");
      System.out.println("　e:やめる");
      System.out.println("********************************************");

      // ユーザからの入力
      String input = mScan.next();
      RPSType userResult;
      switch (input) {
        case "1":
          userResult = RPSType.ROCK;
          break;
        case "2":
          userResult = RPSType.SCISSORS;
          break;
        case "3":
          userResult = RPSType.PAPER;
          break;
        case "e":
          System.out.println("-----マニュアルモード終了-----");
          return;
        default:
          System.out.println("1/2/3/eのいずれかを入力してください");
          System.out.println("********************************************");
          continue;
      }

      // NPCの出し手の決定
      RPSType npcResult = npc.go();

      System.out.println("********************************************");
      System.out.println("        あなた:"+userResult.toString());
      System.out.println("        　相手:"+npcResult.toString());

      // 結果確認
      int result = checkResult(userResult, npcResult);
      if (result == SAME) {
        System.out.println("あいこ！");
        continue;
      } else if (result == PLAYER1_WIN) {
        player1WinCount++;
        System.out.println("        あなたの勝ち");
      } else if (result == PLAYER2_WIN) {
        player2WinCount++;
        System.out.println("        あなたの負け");
      }
      // 勝率表示
      System.out.println("********************************************");
      System.out.println("現在"+player1WinCount+"勝"+(player2WinCount)+"敗");
      npc.onResult(result == PLAYER2_WIN);
      System.out.println("********************************************");
      count++;
    } while(true);
  }

  private void auto() {

    //NPC1の生成
    RPSPlayerBase npc1 = new RPSPlayerHSK();
    //NPC2の生成
    RPSPlayerBase npc2 = new RPSPlayerPaper();

    System.out.println("********************************************");
    System.out.println("★オートモード★");
    int count = 0;
    int player1WinCount = 0;
    int player2WinCount = 0;
    do {
      // NPC1の出し手の決定
      RPSType npc1Result = npc1.go();
      // NPC2の出し手の決定
      RPSType npc2Result = npc2.go();

      System.out.println("********************************************");
      System.out.println("  じゃんけんぽん！");
      System.out.println("        NPC1:"+npc1Result.toString());
      System.out.println("        NPC2:"+npc2Result.toString());

      // 結果確認
      int result = checkResult(npc1Result, npc2Result);
      if (result == SAME) {
        System.out.println("あいこ！");
        continue;
      } else if (result == PLAYER1_WIN) {
        player1WinCount++;
        System.out.println("        NPC1の勝ち");
      } else if (result == PLAYER2_WIN) {
        player2WinCount++;
        System.out.println("        NPC2の勝ち");
      }
      // 勝率表示
      System.out.println("********************************************");
      System.out.println("現在 NPC1:"+player1WinCount+"勝");
      System.out.println("     NPC2:"+player2WinCount+"勝");
      npc1.onResult(result == PLAYER1_WIN);
      npc2.onResult(result == PLAYER2_WIN);
      System.out.println("********************************************");
      count++;
    } while(count < 50);

    // 最終結果表示
    if (player1WinCount > player2WinCount) {
      System.out.println("★★★NPC1の勝利！★★★");
    } else if(player1WinCount < player2WinCount) {
      System.out.println("★★★NPC2の勝利！★★★");
    } else {
      System.out.println("★★★引き分け！★★★");
    }

  }

  /*
   * 0：あいこ
   * 1：プレイヤー1の勝ち
   * 2：プレイヤー2の勝ち
   * -1: エラー
   */
  private int checkResult(RPSType player1, RPSType player2) {
    if (player1 == player2) {
      return SAME;
    }

    int result = -1;
    switch(player1) {
      case PAPER:
        if (player2 == RPSType.ROCK) {
          result = PLAYER1_WIN;
        } else if (player2 == RPSType.SCISSORS) {
          result = PLAYER2_WIN;
        }
        break;
      case ROCK:
        if (player2 == RPSType.SCISSORS) {
          result = PLAYER1_WIN;
        } else if (player2 == RPSType.PAPER) {
          result = PLAYER2_WIN;
        }
        break;
      case SCISSORS:
        if (player2 == RPSType.PAPER) {
          result = PLAYER1_WIN;
        } else if (player2 == RPSType.ROCK) {
          result = PLAYER2_WIN;
        }
        break;
      default:
        break;

    }
    return result;
  }
}
