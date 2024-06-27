package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    private final int wordCount;
    private final int characterCount;
    private final int sentenceCount;
    private final int syllableCount;
    private final int polysyllableCount;

    public Text(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        String[] words = text.split("\\s+");
        int syllableCount = 0;
        int polysyllableCount = 0;
        Pattern pattern = Pattern.compile("[aeiouyAEIOUY]+");
        for (String word : words) {
            String formattedWord = word.replaceAll("e\\b", "");
            Matcher matcher = pattern.matcher(formattedWord);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            syllableCount += Math.max(count, 1);
            if (count > 2) {
                polysyllableCount++;
            }
        }
        this.wordCount = words.length;
        this.characterCount = text.replaceAll("\\s+", "").length();
        this.sentenceCount = text.split("[.?!]+").length;
        this.syllableCount = syllableCount;
        this.polysyllableCount = polysyllableCount;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public int getCharacterCount() {
        return this.characterCount;
    }

    public int getSentenceCount() {
        return this.sentenceCount;
    }

    public int getSyllableCount() {
        return this.syllableCount;
    }

    public int getPolysyllableCount() {
        return this.polysyllableCount;
    }

    @Override
    public String toString() {
        return """
            Words: %d
            Sentences: %d
            Characters: %d
            Syllables: %d
            Polysyllables: %d
            """.formatted(
                this.wordCount,
                this.sentenceCount,
                this.characterCount,
                this.syllableCount,
                this.polysyllableCount
        );
    }
}
