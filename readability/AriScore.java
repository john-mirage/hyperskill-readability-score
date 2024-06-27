package readability;

public class AriScore extends Score {
    public AriScore(Text text) {
        super("Automated Readability Index", text);
    }

    @Override
    protected double calculateScore(Text text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        int characterCount = text.getCharacterCount();
        int wordCount = text.getWordCount();
        int sentenceCount = text.getSentenceCount();
        return 4.71 * ((double) characterCount / wordCount) + 0.5 * ((double) wordCount / sentenceCount) - 21.43;
    }
}
