import java.util.Scanner;

/**
 * Tic-Tac-Toe: Two-player console, non-graphics, non-OO version.
 * All variables/methods are declared as static (belong to the class)
 * in the non-OO version.
 */
public class GameCaro {
    // Trạng thái của các ô chơi
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    // CROSS - X, NOUGHT - O

    // Trạng thái trò chơi
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int CROSS_WON = 2;
    public static final int NOUGHT_WON = 3;

    // Ô chơi và trạng thái trò chơi
    public static final int ROWS = 5, COLS = 5; // số lượng dòng và cột
    public static int[][] board = new int[ROWS][COLS]; // ô chơi trong mảng 2 chiều
    //  bao gồm (EMPTY, CROSS, NOUGHT)
    public static int currentState;  // trạng thái hiện tại của game
    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON) - Đang chơi, Hòa, X thắng, O thắng
    public static int currentPlayer; // người chơi hiện tại - X hoặc O
    public static int currntRow, currentCol; // hàng và cột của ô hiện tại

    public static Scanner in = new Scanner(System.in); // the input Scanner

    /**
     * The entry main method (the program starts here)
     */
    public static void main(String[] args) {
        // Khởi tạo trò chơi và trạng thái hiện tại
        initGame();
        // Play the game once
        do {
            playerMove(currentPlayer); // cập nhật currentRow và currentCol
            updateGame(currentPlayer, currntRow, currentCol); // cập nhật currentState
            printBoard();
            // In thông báo nếu kết thúc trò chơi
            if (currentState == CROSS_WON) {
                System.out.println("'X' thắng!");
            } else if (currentState == NOUGHT_WON) {
                System.out.println("'O' thắng!");
            } else if (currentState == DRAW) {
                System.out.println("Hòa!");         // hiển thị khi tất cả các ô đều đã đánh và ko phân thắng thua
            }
            // Đổi người chơi
            currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
        } while (currentState == PLAYING); // lặp lại nếu chưa kết thúc trò chơi
    }

    /**
     * Khởi tạo nội dung các ô chơi và trạng thái hiện tại
     */
    public static void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;  // khởi tạo gamne, tất cả các ô đều RỖNG
            }
        }
        currentState = PLAYING; // bắt đầu chơi
        currentPlayer = CROSS;  // X chơi trước
    }

    /**
     * Player with the "theSeed" makes one move, with input validation.
     * Update global variables "currentRow" and "currentCol".
     */
    public static void playerMove(int theSeed) {
        boolean validInput = false;  // dành cho việc xác nhận thông tin nhập vào
        do {
            if (theSeed == CROSS) {
                System.out.print("Người chơi 'X', nhập (dòng[1-" + ROWS + "] cột[1-" + COLS + "]): ");
            } else {
                System.out.print("Người chơi 'O', nhập (dòng[1-" + ROWS + "] cột[1-" + COLS + "]): ");
            }
            int row = in.nextInt() - 1;  // vị trí mảng bắt đầu từ 0 thay vì 1
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                currntRow = row;
                currentCol = col;
                board[currntRow][currentCol] = theSeed;  // cập nhật nội dung game
                validInput = true;  // nhập vào đúng, thoát vòng lặp
            } else {
                System.out.println("Vị trí (" + (row + 1) + "," + (col + 1)
                        + ") không hợp lệ, hãy thử lại");
            }
        } while (!validInput);  // lặp lại cho tới khi giá trị nhập vào là đúng
    }

    /**
     * Update the "currentState" after the player with "theSeed" has placed on
     * (currentRow, currentCol).
     */
    public static void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
            currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
        } else if (isDraw()) {  // check for draw
            currentState = DRAW;
        }
        // Otherwise, no change to currentState (still PLAYING).
    }

    /**
     * Return true if it is a draw (no more empty cell)
     */
    // TODO: Shall declare draw if no player can "possibly" win
    public static boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // không còn trống, trả về kết quả Hòa
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     */
    public static boolean hasWon(int theSeed, int currentRow, int currentCol) {


        // kiểm tra thắng 3 ô - 1 hàng - linh động
        for (int i = 0; i + 2 < COLS; i++) {
            if (board[currentRow][i] == theSeed
                    && board[currentRow][i + 1] == theSeed
                    && board[currentRow][i + 2] == theSeed) {
                return true;
            }
        }
        // bên trên for cho ngắn code, còn nếu muốn gia tăng hiệu suất thì có thể sử dụng if else với 2 ô 2 bên, hoặc 2 ô bên trái, 2 ô bên phải khi mà bảng quá to


        // kiểm tra thắng 3 ô - một cột - linh động
        for (int i = 0; i + 2 < ROWS; i++) {
            if (board[i][currentCol] == theSeed
                    && board[i + 1][currentCol] == theSeed
                    && board[i + 2][currentCol] == theSeed) {
                return true;
            }
        }


        // kiểm tra thắng 3 ô - hàng chéo xuống - đường chéo phụ - fix cứng cho bàn chơi có kích thước 5x5
        // chéo phụ bên dưới trong bảng 5x5
        if ((board[1][0] == theSeed
                && board[2][1] == theSeed
                && board[3][2] == theSeed)
                ||
                (board[2][1] == theSeed
                        && board[3][2] == theSeed
                        && board[4][3] == theSeed)
                ||
                (board[2][0] == theSeed
                        && board[3][1] == theSeed
                        && board[4][2] == theSeed)) {
            return true;
        }
        // chéo phụ bên trên trong bảng 5x5
        if ((board[0][1] == theSeed
                && board[1][2] == theSeed
                && board[2][3] == theSeed)
                ||
                (board[1][2] == theSeed
                        && board[2][3] == theSeed
                        && board[3][4] == theSeed)
                ||
                (board[0][2] == theSeed
                        && board[1][3] == theSeed
                        && board[2][4] == theSeed)) {
            return true;
        }


        // kiểm tra thắng 3 ô - hàng chéo xuống - đường chéo chính - linh động
        if (currentRow == currentCol) {
            if (currentRow == 0                                           // nếu chọn ô có tọa độ 0 0
                    && board[currentRow][currentCol] == theSeed
                    && board[currentRow + 1][currentCol + 1] == theSeed
                    && board[currentRow + 2][currentCol + 2] == theSeed) {
                return true;
            } else if ((currentRow == 1                                    // nếu chọn ô có tọa độ 1 1
                    && board[currentRow - 1][currentCol - 1] == theSeed
                    && board[currentRow][currentCol] == theSeed
                    && board[currentRow + 1][currentCol + 1] == theSeed)
                    ||
                    (currentRow == 1
                            && board[currentRow][currentCol] == theSeed
                            && board[currentRow + 1][currentCol + 1] == theSeed
                            && board[currentRow + 2][currentCol + 2] == theSeed)) {
                return true;
            } else if (currentRow == ROWS - 1                                           // nếu chọn ô có tọa độ ROWS-1 ROWS-1, lưu ý là tọa độ sẽ bị trừ đi 1 đơn vị so với số nhập vào
                    && board[currentRow][currentCol] == theSeed
                    && board[currentRow - 1][currentCol - 1] == theSeed
                    && board[currentRow - 2][currentCol - 2] == theSeed) {
                return true;
            } else if ((currentRow == ROWS - 2                                         // nếu chọn ô có tọa độ ROWS-2 ROWS-2
                    && board[currentRow + 1][currentCol + 1] == theSeed
                    && board[currentRow][currentCol] == theSeed
                    && board[currentRow - 1][currentCol - 1] == theSeed)
                    ||
                    (currentRow == ROWS - 2
                            && board[currentRow][currentCol] == theSeed
                            && board[currentRow - 1][currentCol - 1] == theSeed
                            && board[currentRow - 2][currentCol - 2] == theSeed)) {
                return true;
            } else {                                                            // còn lại là các ô ở giữa
                if ((currentRow - 1 >= 0 && currentCol - 1 >= 0
                        && currentRow + 1 < ROWS && currentCol + 1 < COLS
                        && board[currentRow - 1][currentCol - 1] == theSeed
                        && board[currentRow][currentCol] == theSeed                 // ô được chọn và 2 ô bên cạnh nó
                        && board[currentRow + 1][currentCol + 1] == theSeed)
                        ||
                        (currentRow - 2 >= 0 && currentCol - 2 >= 0
                                && board[currentRow][currentCol] == theSeed             // ô được chọn và 2 ô bên trên nó
                                && board[currentRow - 1][currentCol - 1] == theSeed
                                && board[currentRow - 2][currentCol - 2] == theSeed)
                        ||
                        (currentRow + 2 < ROWS && currentCol + 2 < COLS
                                && board[currentRow][currentCol] == theSeed             // ô được chọn và 2 ô bên dưới nó
                                && board[currentRow + 1][currentCol + 1] == theSeed
                                && board[currentRow + 2][currentCol + 2] == theSeed)) {
                    return true;
                }
            }

        }


        // kiểm tra thắng 3 ô - hàng chéo lên - đường chéo phụ - fix cứng cho bàn chơi có kích thước 5x5
        // chéo phụ bên trên trong bảng 5x5
        if ((board[3][0] == theSeed
                && board[2][1] == theSeed
                && board[1][2] == theSeed)
                ||
                (board[2][1] == theSeed
                        && board[1][2] == theSeed
                        && board[0][3] == theSeed)
                ||
                (board[2][0] == theSeed
                        && board[1][1] == theSeed
                        && board[0][2] == theSeed)) {
            return true;
        }
        // chéo phụ bên dưới trong bảng 5x5
        if ((board[4][1] == theSeed
                && board[3][2] == theSeed
                && board[2][3] == theSeed)
                ||
                (board[3][2] == theSeed
                        && board[2][3] == theSeed
                        && board[1][4] == theSeed)
                ||
                (board[4][2] == theSeed
                        && board[3][3] == theSeed
                        && board[2][4] == theSeed)) {
            return true;
        }


        // kiểm tra thắng 3 ô - hàng chéo lên - đường chéo chính - cái này tạm thời chạy tốt cho bàn chơi có hình VUÔNG, hình chữ nhật chưa test
        if (currentRow + currentCol == ROWS - 1) {
            if (currentRow == ROWS - 1) {                    // nếu chọn ô có tọa độ 4 0
                if (board[4][0] == theSeed
                        && board[3][1] == theSeed
                        && board[2][2] == theSeed) {
                    return true;
                } else if (board[3][1] == theSeed
                        && board[2][2] == theSeed
                        && board[1][3] == theSeed) {
                    return true;
                } else if (board[2][2] == theSeed
                        && board[1][3] == theSeed
                        && board[0][4] == theSeed) {
                    return true;
                }
            } else if (currentRow == ROWS - 2) {             // nếu chọn ô có tọa độ 3 1
                if (board[currentRow + 1][currentCol - 1] == theSeed
                        && board[currentRow][currentCol] == theSeed
                        && board[currentRow - 1][currentCol + 1] == theSeed) {
                    return true;
                } else if (board[currentRow][currentCol] == theSeed
                        && board[currentRow - 1][currentCol + 1] == theSeed
                        && board[currentRow - 2][currentCol + 2] == theSeed) {
                    return true;
                }
            } else if (currentRow == 0) {          // nếu chọn ô có tọa độ 0 COLS-1
                if (board[currentRow][currentCol] == theSeed
                        && board[currentRow + 1][currentCol - 1] == theSeed
                        && board[currentRow + 2][currentCol - 2] == theSeed) {
                    return true;
                }
            } else if (currentRow == 1) {       // nếu chọn ô có tọa độ 1 COLS-2
                if (board[currentRow - 1][currentCol + 1] == theSeed
                        && board[currentRow][currentCol] == theSeed
                        && board[currentRow + 1][currentCol - 1] == theSeed) {
                    return true;
                } else if (board[currentRow][currentCol] == theSeed
                        && board[currentRow + 1][currentCol - 1] == theSeed
                        && board[currentRow + 2][currentCol - 2] == theSeed) {
                    return true;
                }
            } else {                                // còn lại là các ô ở giữa
                if (board[currentRow - 1][currentCol + 1] == theSeed
                        && board[currentRow][currentCol] == theSeed                 // ô được chọn và 2 ô bên cạnh nó
                        && board[currentRow + 1][currentCol - 1] == theSeed) {
                    return true;
                } else if (board[currentRow][currentCol] == theSeed             // ô được chọn và 2 ô bên trên nó
                        && board[currentRow - 1][currentCol + 1] == theSeed
                        && board[currentRow - 2][currentCol + 2] == theSeed) {
                    return true;
                } else if (board[currentRow][currentCol] == theSeed             // ô được chọn và 2 ô bên dưới nó
                        && board[currentRow + 1][currentCol - 1] == theSeed
                        && board[currentRow + 2][currentCol - 2] == theSeed) {
                    return true;
                }
            }

        }


        return false;
    }


    /**
     * Vẽ bàn chơi
     */
    public static void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]); // hiển thị giá trị cho mỗi ô
                if (col != COLS - 1) {
                    System.out.print("|");   // vẽ khoảng ngăn trái phải
                }
            }
            System.out.println();

            String printForEachColum = "----";      // tương ứng với mỗi ô sẽ có 4 dấu -
            String totalPrint = "";
            if (row != ROWS - 1) {
                for (int i = 1; i <= COLS; i++) {
                    totalPrint = totalPrint + printForEachColum;
                }
                System.out.println(totalPrint); // vẽ khoảng ngăn trên dưới
            }
        }
        System.out.println();
    }

    /**
     * Hiển thị nội dung cho Ô
     */
    public static void printCell(int content) {
        switch (content) {
            case EMPTY:
                System.out.print("   ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case CROSS:
                System.out.print(" X ");
                break;
        }
    }
}
/*
 * Nên để bảng là hình vuông :(, hiện tại đang để bảng là 5x5, hiện tại cũng đang fix cứng logic là chọn 3 ô liên tiếp là thắng
 * Đã xử lý được Tự động tính Thắng thua cho:
 * + 1 hàng
 * + 1 cột
 * + hàng chéo xuống, đường chéo chính
 * Còn lại thì đều sử dụng logic cứng cho:
 * + hàng chéo xuống, đường chéo phụ
 * + hàng chéo lên, đường chéo chính
 * + hàng chéo lên, đường chéo phụ
 */
