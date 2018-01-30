import java.util.Random;

public class RPSPlayerHSK extends RPSPlayerBase {

  private RPSType mType;
  private int mGameCount = 0;
  private int[] mWinTypeCountArray;

  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayerHSK();
    player.go();
  }

  public RPSPlayerHSK() {
    mWinTypeCountArray = new int[3];
    // 初期化
    for (int i = 0; i < mWinTypeCountArray.length; i++) {
      mWinTypeCountArray[i] = 0;
    }
  }

  @Override
  public RPSType go() {
    if (mGameCount < 10) {
      // データ取り期間
      Random random = new Random();
      int intType = random.nextInt(3) + 1;
      mType = intToRPSType(intType);
    } else {
      mType = getNextRPSType();
    }
    return mType;
  }

  @Override
  public void onResult(boolean isWinner) {
    mGameCount++;
    if (isWinner) {
      mWinTypeCountArray[mType.getId()-1]++;
    } else {
      mWinTypeCountArray[mType.getId()-1]--;
    }

//    // for Debug
//    for (int i = 0; i < mWinTypeCountArray.length; i++) {
//      System.out.println(intToRPSType(i+1).toString()+" :: "+mWinTypeCountArray[i]);
//    }
  }

  private RPSType getNextRPSType() {
    // 一番勝率の高い手を探す
    int bestTypeIndex = 0;
    int secondTypeIndex = 1;
    int thirdTypeIndex = 2;
    if (mWinTypeCountArray[0] >= mWinTypeCountArray[1]) {
      bestTypeIndex = 0;
      secondTypeIndex = 1;
    } else {
      bestTypeIndex = 1;
      secondTypeIndex = 0;
    }

    if (mWinTypeCountArray[bestTypeIndex] < mWinTypeCountArray[2]) {
      thirdTypeIndex = bestTypeIndex;
      bestTypeIndex = 2;
    }

    if (mWinTypeCountArray[secondTypeIndex] < mWinTypeCountArray[thirdTypeIndex]) {
      int tmp = secondTypeIndex;
      secondTypeIndex = thirdTypeIndex;
      thirdTypeIndex = tmp;
    }

    // 1 ~ 100の値をランダムで取得
    Random random = new Random();
    int intType = random.nextInt(100) + 1;

    RPSType nextType;
    if (intType <= 60) {
      nextType = intToRPSType(bestTypeIndex+1);
    } else if (intType <= 80) {
      nextType = intToRPSType(secondTypeIndex+1);
    } else {
      nextType = intToRPSType(thirdTypeIndex+1);
    }
    return nextType;
  }

  private RPSType intToRPSType(int intType) {
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
}
