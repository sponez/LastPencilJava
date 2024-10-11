package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static String[] PLAYERS = { "John", "Jack" };
    private final static Random RANDOM = new Random();

    private static int pencilsAmount;
    private static int currentNumOfPlayer;

    private static void displayPencils() {
        System.out.println("|".repeat(pencilsAmount));
    }

    private static int botMakeMove() {
        int turnPencils = switch (pencilsAmount % 4) {
            case 1 -> pencilsAmount == 1 ? 1 : RANDOM.nextInt(3) + 1;
            case 2 -> 1;
            case 3 -> 2;
            default -> 3;
        };

        System.out.println(turnPencils);
        currentNumOfPlayer = 0;

        return turnPencils;
    }

    private static int playerMakeMove() {
        int turnPencils;

        for (;;) {
            String turnPencilsString;

            turnPencilsString = SCANNER.nextLine();

            try {
                turnPencils = Integer.parseInt(turnPencilsString);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            }

            if (turnPencils <= 0 || turnPencils > 3) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            }

            if (turnPencils > pencilsAmount) {
                System.out.println("Too many pencils were taken");
                continue;
            }

            break;
        }

        currentNumOfPlayer = 1;

        return turnPencils;
    }

    private static void makeMove() {
        System.out.printf("%s's turn!\n", PLAYERS[currentNumOfPlayer]);
        pencilsAmount -= currentNumOfPlayer == 0 ? playerMakeMove() : botMakeMove();
    }

    private static void initGame() {
        for (;;) {
            String pencilsAmountString;

            System.out.println("How many pencils would you like to use:");
            pencilsAmountString = SCANNER.nextLine();

            try {
                pencilsAmount = Integer.parseInt(pencilsAmountString);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }

            if (pencilsAmount <= 0) {
                System.out.println("The number of pencils should be positive");
                continue;
            }

            break;
        }

        for (;;) {
            String nameOfFirst;

            System.out.printf("Who will be the first (%s, %s):\n", PLAYERS[0], PLAYERS[1]);
            nameOfFirst = SCANNER.nextLine();

            currentNumOfPlayer = 0;
            while (currentNumOfPlayer < PLAYERS.length && !nameOfFirst.equals(PLAYERS[currentNumOfPlayer])) {
                ++currentNumOfPlayer;
            }

            if (currentNumOfPlayer >= PLAYERS.length) {
                System.out.printf("Choose between %s and %s\n", PLAYERS[0], PLAYERS[1]);
                continue;
            }

            break;
        }
    }

    public static void main(String[] args) {
        initGame();

        while (pencilsAmount > 0) {
            displayPencils();
            makeMove();
        }

        System.out.printf("%s won!\n", PLAYERS[currentNumOfPlayer]);

        SCANNER.close();
    }
}
