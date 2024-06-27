package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String file = readFileAsString(args[0]);
            System.out.println("The text is:");
            System.out.println(file);
            Text text = new Text(file);
            System.out.println();
            System.out.println(text);
            System.out.println();
            String scoreToCalculate = UserInterface.askForScoreToCalculate();
            System.out.println();
            switch (scoreToCalculate) {
                case "ARI":
                    System.out.println(new AriScore(text));
                    break;
                case "FK":
                    System.out.println(new FkScore(text));
                    break;
                case "SMOG":
                    System.out.println(new SmogScore(text));
                    break;
                case "CL":
                    System.out.println(new ClScore(text));
                    break;
                case "all":
                    Score[] scores = new Score[] {
                            new AriScore(text),
                            new FkScore(text),
                            new SmogScore(text),
                            new ClScore(text)
                    };
                    int totalAge = 0;
                    for (Score score : scores) {
                        totalAge += score.getAge();
                        System.out.println(score);
                    }
                    System.out.println();
                    System.out.printf("This text should be understood in average by %.2f-year-olds.\n", totalAge / 4.0);
                    break;
                default:
                    throw new ScoreException("Invalid score value");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
