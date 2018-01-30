
public enum RPSType {
  ROCK(1),      // グー
  SCISSORS(2),  // チョキ
  PAPER(3);     // パー

  private final int id;

  /**
   * コンストラクタ
   * @param id ID
   */
  private RPSType(final int id) {
    this.id = id;
  }

  /*
   * 列挙型のID取得
   * @return ID
   */
  public int getId() {
    return id;
  }

}
