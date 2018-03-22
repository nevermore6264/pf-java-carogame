import java.util.Scanner;

public class Caro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice2;
        System.out.println(" Do you want to play ? <y/n>");
        choice2 = scanner.next().charAt(0);
        switch (choice2) {
            case 'y':
                playGame();
                break;
            default:
                System.exit(0);
        }
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        char xo;
        int choice;
        String plr1;
        String plr2;
        char a = ' ';
        char b = ' ';
        char c = ' ';
        char d = ' ';
        char e = ' ';
        char f = ' ';
        char g = ' ';
        char h = ' ';
        char i = ' ';

        System.out.println("3x3 - 2 PLAYERS - CARO BOARD (Use NumPad for optimal gameplay)");

        System.out.println("Insert player 1's name (No space between characters)");
        plr1 = scanner.nextLine();
        System.out.println("Insert player 2's name (No space between characters)");
        plr2 = scanner.nextLine();
        //GAMEPLAY

        System.out.println(" ___________________");
        System.out.println(" | (7) | (8) | (9) |");
        System.out.println(" |_____|_____|_____|");
        System.out.println(" | (4) | (5) | (6) |");
        System.out.println(" |_____|_____|_____|");
        System.out.println(" | (1) | (2) | (3) |");
        System.out.println(" |_____|_____|_____|");
        System.out.println(" ___________________");
        for (int k = 1; k < 10; k++) {

            if (k % 2 == 0) {
                System.out.println(plr1 + "'s turn ");
                xo = 'X';
            } else {
                System.out.println(plr2 + "'s turn ");
                xo = 'O';
            }
            System.out.println("Please make your choice by selecting the appropriate number (Use NumPad for optimal gameplay)");
            choice = scanner.nextInt();
            switch (choice) {
                case 7:
                    a = xo;
                    break;
                case 8:
                    b = xo;
                    break;
                case 9:
                    c = xo;
                    break;
                case 4:
                    d = xo;
                    break;
                case 5:
                    e = xo;
                    break;
                case 6:
                    f = xo;
                    break;
                case 1:
                    g = xo;
                    break;
                case 2:
                    h = xo;
                    break;
                case 3:
                    i = xo;
                    break;
                default:
                    System.out.println("ERROR: Pick the number within range");
                    break;
            }

            System.out.println(" ___________________");
            System.out.println(" |  " + a + "  |  " + b + "  |  " + c + "  |");
            System.out.println(" |_____|_____|_____|");
            System.out.println(" |  " + d + "  |  " + e + "  |  " + f + "  |");
            System.out.println(" |_____|_____|_____|");
            System.out.println(" |  " + g + "  |  " + h + "  |  " + i + "  |");
            System.out.println(" |_____|_____|_____|");
            System.out.println(" ___________________");

            if (a == 'X' && b == 'X' && c == 'X' || d == 'X' && e == 'X' && f == 'X' || g == 'X' && h == 'X' && i == 'X' ||
                    a == 'X' && d == 'X' && g == 'X' || b == 'X' && e == 'X' && h == 'X' || c == 'X' && f == 'X' && i == 'X' ||
                    a == 'X' && e == 'X' && i == 'X' || g == 'X' && e == 'X' && c == 'X') {
                System.out.println(plr1 + " WON!!! ");
                break;
            } else if (a == 'O' && b == 'O' && c == 'O' || d == 'O' && e == 'O' && f == 'O' || g == 'O' && h == 'O' && i == 'O' ||
                    a == 'O' && d == 'O' && g == 'O' || b == 'O' && e == 'O' && h == 'O' || c == 'O' && f == 'O' && i == 'O' ||
                    a == 'O' && e == 'O' && i == 'O' || g == 'O' && e == 'O' && c == 'O') {
                System.out.println(plr2 + " WON!!! ");
                break;
            } else {
                System.out.println("It's a Draw");
            }
        }

    }
}
