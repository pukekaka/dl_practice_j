package deepDriver.dl.aml.lstm.apps.wordSegmentation;

public class WordSegment {
	String [] words; 
	int [] wordsInt;
	
	WordSegment previous;
	WordSegment next;
	
	public String[] getWords() {
		return words;
	}
	public void setWords(String[] words) {
		this.words = words;
	}
	public WordSegment getPrevious() {
		return previous;
	}
	public void setPrevious(WordSegment previous) {
		this.previous = previous;
	}
	public WordSegment getNext() {
		return next;
	}
	public void setNext(WordSegment next) {
		this.next = next;
	}
	public int[] getWordsInt() {
		return wordsInt;
	}
	public void setWordsInt(int[] wordsInt) {
		this.wordsInt = wordsInt;
	}	

}
