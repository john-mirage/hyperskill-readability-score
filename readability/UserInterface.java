package readability;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static String askForScoreToCalculate() {
        while (true) {
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
            String score = scanner.nextLine();
            if (score.matches("^(ARI|FK|SMOG|CL|all)$")) {
                return score;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
