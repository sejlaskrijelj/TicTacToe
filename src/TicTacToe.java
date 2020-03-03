
import java.util.*;
public class TicTacToe {

    static ArrayList<Integer> playersPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpusPositions = new ArrayList<Integer>();


    public static void main (String [] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);//bolje je velike metode stavljati izvan glčaven main funkcije da bi to na nesto licilo,zato moramo stavljati vijek public static


        while (true) {
            Scanner scan = new Scanner(System.in); //definisemo ulaz sa tastature
            System.out.println("Enter your placement: (1-9): ");
            int pos = scan.nextInt();
            while(playersPositions.contains(pos) || cpusPositions.contains(playersPositions)) {
                System.out.println("That place is taken, insert a correct place");
                pos= scan.nextInt();
            }
            placePiece(gameBoard, pos, "player");
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1; // ovo radi random brojeve od 1 do 9
            while(cpusPositions.contains(cpuPos) || playersPositions.contains(cpuPos)) {
                 rand = new Random();
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);
            String result = checkWinner();
            System.out.println(result);

        }
    }
            public static void printGameBoard(char [] [] gameBoard) {
        for( char [] row: gameBoard) {
            for (char c: row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece (char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol='X';
            playersPositions.add(pos);
        }else if (user.equals("cpu")) {
            symbol = 'O';
            cpusPositions.add(pos);
        }

        switch (pos) {
            case 1: gameBoard [0][0]=symbol;
            break;
            case 2: gameBoard [0][2]=symbol;
            break;
            case 3: gameBoard [0][4]=symbol;
            break;
            case 4: gameBoard [2][0]=symbol;
            break;
            case 5: gameBoard [2][2]=symbol;
            break;
            case 6: gameBoard [2][4]=symbol;
            break;
            case 7: gameBoard [4][0]=symbol;
            break;
            case 8: gameBoard [4][2]=symbol;
            break;
            case 9: gameBoard [4][4]=symbol;
            break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1 , 2, 3);
        List middleRow = Arrays.asList(4 , 5, 6);
        List bottomRow = Arrays.asList(7 , 8, 9);
        List leftCol = Arrays.asList(1, 4, 7 );
        List midCol = Arrays.asList(2, 5, 8 );
        List rightCol = Arrays.asList(3, 6 , 9 );
        List cross1 = Arrays.asList(1, 5, 9 );
        List cross2 = Arrays.asList(7, 5, 3 );

        List<List> winningCond = new ArrayList<List>();
        winningCond.add(topRow);
        winningCond.add(middleRow);
        winningCond.add(bottomRow);
        winningCond.add(leftCol);
        winningCond.add(midCol);
        winningCond.add(rightCol);
        winningCond.add(cross1);
        winningCond.add(cross2);

        for (List l: winningCond) {
            if(playersPositions.containsAll(l)) {
                return "Congrats, you won!";
            }else if (cpusPositions.containsAll(l)) {
                return "Sorry, cpu wins :( ";
            } else if( playersPositions.size() + cpusPositions.size() == 9) {
                return "CAT!";
            }
        }

        return "";
    }
}
