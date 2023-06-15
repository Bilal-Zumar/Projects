public class WordFrequency implements Comparable<WordFrequency> {
    private String word;
    private int count;

    public WordFrequency(String word) {
        this.word = word;
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public int compareTo(WordFrequency wRef) {
        if (count == wRef.count) {
            return word.compareTo(wRef.word);
        }
        else {
            return count - wRef.count;
        }
    }

    public String toString() {
        return String.format("%20s: %5d", word, count);
    }
}
