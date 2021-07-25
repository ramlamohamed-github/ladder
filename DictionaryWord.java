public class DictionaryWord {
  private String Words;
  private String Length;
  private String WordID;
  
  public DictionaryWord(String Words, String Length, String WordID) {
    this.Words = Words;
    this.Length = Length;
    this.WordID = WordID;
  }
  
  public String getWord() {
    return this.Words;
  }
  
  public String getLength() {
    return this.Length;
  }
  
  public String getWordID() {
    return this.WordID;
  }
}
