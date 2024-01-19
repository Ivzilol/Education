import java.util.ArrayList;
import java.util.List;

public class ValidSudoku_36 {
    public static void main(String[] args) {
        ValidSudoku_36 cl = new ValidSudoku_36();
        char[][] exampleInput = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        cl.isValidSudoku(exampleInput);
//        char[][] exampleInput2 = {
//                {'8','3','.','.','7','.','.','.','.'},
//                {'.','.','.','8','6','5','.','.','.'},
//                {'.','1','.','2','.','.','.','.','.'},
//                {'.','.','.','.','.','9','.','.','.'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}
//        };
//        cl.isValidSudoku(exampleInput2);
        char[][] exampleInput3 = {
                {'.','4','.','.','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        cl.isValidSudoku(exampleInput3);
    }

    public boolean isValidSudoku(char[][] board) {
        boolean isValid = true;
        int rowMatrix = 9;
        int colMatrix = 9;
        for (int row = 0; row < rowMatrix; row++) {
            if (!isValid) {
                break;
            }
            List<Integer> digits = new ArrayList<>();
            for (int col = 0; col < colMatrix; col++) {
                if (!Character.isDigit(board[row][col])) {
                    continue;
                }
                int currentDigit = Integer.parseInt(String.valueOf(board[row][col]));
                if (!digits.contains(currentDigit)) {
                    digits.add(currentDigit);
                } else {
                    isValid = false;
                    break;
                }
            }
            if (row == 0) {
                List<Integer> firstBox = new ArrayList<>();
                List<Integer> secondBox = new ArrayList<>();
                List<Integer> thirdBox = new ArrayList<>();
                for (int rowForBox = row; rowForBox <= 2; rowForBox++) {
                    if (!isValid) {
                        break;
                    }
                    for (int colForBox = 0; colForBox <= 2; colForBox++) {
                       if (!Character.isDigit(board[rowForBox][colForBox])) {
                           continue;
                       }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForBox]));
                        if (!firstBox.contains(currentDigit)) {
                            firstBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colForSecondBox = 3; colForSecondBox <= 5 ; colForSecondBox++) {
                        if (!Character.isDigit(board[rowForBox][colForSecondBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForSecondBox]));
                        if (!secondBox.contains(currentDigit)) {
                            secondBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colThirdBox = 6; colThirdBox <= 8; colThirdBox++) {
                        if (!Character.isDigit(board[rowForBox][colThirdBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colThirdBox]));
                        if (!thirdBox.contains(currentDigit)) {
                            thirdBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
            if (row == 3) {
                List<Integer> firstBox = new ArrayList<>();
                List<Integer> secondBox = new ArrayList<>();
                List<Integer> thirdBox = new ArrayList<>();
                for (int rowForBox = row; rowForBox <= 5; rowForBox++) {
                    if (!isValid) {
                        break;
                    }
                    for (int colForBox = 0; colForBox <= 2; colForBox++) {
                        if (!Character.isDigit(board[rowForBox][colForBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForBox]));
                        if (!firstBox.contains(currentDigit)) {
                            firstBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colForSecondBox = 3; colForSecondBox <= 5 ; colForSecondBox++) {
                        if (!Character.isDigit(board[rowForBox][colForSecondBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForSecondBox]));
                        if (!secondBox.contains(currentDigit)) {
                            secondBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colThirdBox = 6; colThirdBox <= 8; colThirdBox++) {
                        if (!Character.isDigit(board[rowForBox][colThirdBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colThirdBox]));
                        if (!thirdBox.contains(currentDigit)) {
                            thirdBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
            if (row == 6) {
                List<Integer> firstBox = new ArrayList<>();
                List<Integer> secondBox = new ArrayList<>();
                List<Integer> thirdBox = new ArrayList<>();
                for (int rowForBox = row; rowForBox <= 8; rowForBox++) {
                    if (!isValid) {
                        break;
                    }
                    for (int colForBox = 0; colForBox <= 2; colForBox++) {
                        if (!Character.isDigit(board[rowForBox][colForBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForBox]));
                        if (!firstBox.contains(currentDigit)) {
                            firstBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colForSecondBox = 3; colForSecondBox <= 5 ; colForSecondBox++) {
                        if (!Character.isDigit(board[rowForBox][colForSecondBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colForSecondBox]));
                        if (!secondBox.contains(currentDigit)) {
                            secondBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    for (int colThirdBox = 6; colThirdBox <= 8; colThirdBox++) {
                        if (!Character.isDigit(board[rowForBox][colThirdBox])) {
                            continue;
                        }
                        int currentDigit = Integer.parseInt(String.valueOf(board[rowForBox][colThirdBox]));
                        if (!thirdBox.contains(currentDigit)) {
                            thirdBox.add(currentDigit);
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
        }
        int numRows = board.length;
        int numCols = board[0].length;
        for (int col = 0; col < numCols; col++) {
            if (!isValid) {
                break;
            }
            List<Integer> digits = new ArrayList<>();
            for (int row = 0; row < numRows; row++) {
                if (!Character.isDigit(board[row][col])) {
                    continue;
                }
                int currentDigit = Integer.parseInt(String.valueOf(board[row][col]));
                if (!digits.contains(currentDigit)) {
                    digits.add(currentDigit);
                } else {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }
}
