package readability;

public class ClScore extends Score {
    public ClScore(Text text) {
        super("Coleman–Liau index", text);
    }

    @Override
    protected double calculateScore(Text text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        int characterCount = text.getCharacterCount();
        int wordCount = text.getWordCount();
        int sentenceCount = text.getSentenceCount();
        return 0.0588 * (((double) characterCount / wordCount) * 100) - 0.296 * (((double) sentenceCount / wordCount) * 100) - 15.8;
    }
}
