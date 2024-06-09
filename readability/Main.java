package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String file = readFileAsString(args[0]);
            Text text = new Text(file);
            System.out.println("Words: " + text.getWordCount());
            System.out.println("Sentences: " + text.getSentenceCount());
            System.out.println("Characters: " + text.getCharacterCount());
            System.out.println("Syllables: " + text.getSyllableCount());
            System.out.println("Polysyllables: " + text.getPolysyllableCount());
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all");
            System.out.println();
            System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).\n", text.getAriScore(), text.getAriAge());
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).\n", text.getFkScore(), text.getFkAge());
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).\n", text.getSmogScore(), text.getSmogAge());
            System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).\n", text.getClScore(), text.getClAge());
            System.out.println();
            System.out.printf("This text should be understood in average by %.2f-year-olds.\n", text.getAgeAverage());
        } catch (IOException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
