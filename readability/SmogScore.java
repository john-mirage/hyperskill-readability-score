package readability;

public class SmogScore extends Score {
    public SmogScore(Text text) {
        super("Simple Measure of Gobbledygook", text);
    }

    @Override
    protected double calculateScore(Text text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        int polysyllableCount = text.getPolysyllableCount();
        int sentenceCount = text.getSentenceCount();
        return 1.043 * Math.sqrt(polysyllableCount * ((double) 30 / sentenceCount)) + 3.1291;
    }
}
