package readability;

public abstract class Score {
    private final String name;
    private final double score;
    private final int age;

    public Score(String name, Text text) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.name = name;
        this.score = this.calculateScore(text);
        this.age = this.calculateAgeForScore();
    }

    public int getAge() {
        return this.age;
    }

    protected abstract double calculateScore(Text text);

    private int calculateAgeForScore() {
        int roundedScore = (int) Math.round(this.score);
        if (roundedScore >= 1 && roundedScore <= 13) {
            return roundedScore + 6;
        } else if (roundedScore == 14) {
            return 23;
        } else {
            throw new IllegalStateException("Unexpected value: " + this.score);
        }
    }

    @Override
    public String toString() {
        return "%s: %.2f (about %d-year-olds).".formatted(this.name, this.score, this.age);
    }
}
