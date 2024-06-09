package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    private final int wordCount;
    private final int characterCount;
    private final int sentenceCount;
    private final int syllableCount;
    private final int polysyllableCount;
    private final double ariScore;
    private final double fkScore;
    private final double smogScore;
    private final double clScore;
    private final int ariAge;
    private final int fkAge;
    private final int smogAge;
    private final int clAge;
    private final double ageAverage;

    public Text(String text) {
        String[] words = text.replaceAll("[.?!,](?=\\s|$)", "").split("\\s");
        int syllableCount = 0;
        int polysyllableCount = 0;
        Pattern pattern = Pattern.compile("[aeiouy]+");
        for (String word : words) {
            String formattedWord = word.replaceAll("e(?=\\s|$)", "");
            Matcher matcher = pattern.matcher(formattedWord);
            int count = (int) matcher.results().count();
            if (count > 2) {
                polysyllableCount++;
            }
            syllableCount += count <= 0 ? 1 : count;
        }
        this.wordCount = words.length;
        this.characterCount = text.replaceAll("\\s+", "").length();
        this.sentenceCount = text.split("[.?!]\\s").length;
        this.syllableCount = syllableCount;
        this.polysyllableCount = polysyllableCount;
        this.ariScore = 4.71 * ((double) this.characterCount / this.wordCount) + 0.5 * ((double) this.wordCount / this.sentenceCount) - 21.43;
        this.fkScore = 0.39 * ((double) this.wordCount / this.sentenceCount) + 11.8 * ((double) this.syllableCount / this.wordCount) - 15.59;
        this.smogScore = 1.043 * Math.sqrt(this.polysyllableCount * ((double) 30 / this.sentenceCount)) + 3.1291;
        this.clScore = 0.0588 * (((double) this.characterCount / this.wordCount) * 100) - 0.296 * (((double) this.sentenceCount / this.wordCount) * 100) - 15.8;
        this.ariAge = this.getAgeForReadabilityScore(this.ariScore);
        this.fkAge = this.getAgeForReadabilityScore(this.fkScore);
        this.smogAge = this.getAgeForReadabilityScore(this.smogScore);
        this.clAge = this.getAgeForReadabilityScore(this.clScore);
        this.ageAverage = (this.ariAge + this.fkAge + this.smogAge + this.clAge) / 4.0;
    }

    private int getAgeForReadabilityScore(double score) throws IllegalStateException {
        return switch ((int) score) {
            case 1 -> 7;
            case 2 -> 8;
            case 3 -> 9;
            case 4 -> 10;
            case 5 -> 11;
            case 6 -> 12;
            case 7 -> 13;
            case 8 -> 14;
            case 9 -> 15;
            case 10 -> 16;
            case 11 -> 17;
            case 12 -> 18;
            case 13 -> 19;
            case 14 -> 23;
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
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

    public double getAriScore() {
        return this.ariScore;
    }

    public double getFkScore() {
        return this.fkScore;
    }

    public double getSmogScore() {
        return this.smogScore;
    }

    public double getClScore() {
        return this.clScore;
    }

    public int getAriAge() {
        return this.ariAge;
    }

    public int getFkAge() {
        return this.fkAge;
    }

    public int getSmogAge() {
        return this.smogAge;
    }

    public int getClAge() {
        return this.clAge;
    }

    public double getAgeAverage() {
        return this.ageAverage;
    }
}
