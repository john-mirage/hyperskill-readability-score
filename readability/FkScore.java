package readability;

public class FkScore extends Score {
    public FkScore(Text text) {
        super("Flesch–Kincaid readability tests", text);
    }

    @Override
    protected double calculateScore(Text text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        int wordCount = text.getWordCount();
        int sentenceCount = text.getSentenceCount();
        int syllableCount = text.getSyllableCount();
        return 0.39 * ((double) wordCount / sentenceCount) + 11.8 * ((double) syllableCount / wordCount) - 15.59;
    }
}
