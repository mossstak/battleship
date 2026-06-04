
/* 
Keycodes:
~ = Water
S = Ship
X = Hit
O = Miss
 */
public class Grid {

    // Fields
    private final char[][] matrix;
    private final Ship[][] shipMatrix;

    // Constructor
    public Grid() {
        //Create a matrix array - 10 by 10
        matrix = new char[10][10];
        shipMatrix = new Ship[10][10];

        //Fill the matrix with '~' which is Water
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                matrix[row][col] = '~';
            }
        }
    }

    //Methods
    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        if (isValidPlacement(ship, row, col, horizontal)) {
            if (horizontal) {
                for (int i = 0; i < ship.getSize(); i++) {
                    matrix[row][col + i] = 'S';
                    shipMatrix[row][col + i] = ship;
                }
            } else {
                for (int i = 0; i < ship.getSize(); i++) {
                    matrix[row + i][col] = 'S';
                    shipMatrix[row + i][col] = ship;
                }
            }
            return true; // Placement succeeded!
        } else {
            System.out.println("Error: Invalid ship placement coordinates!");
            return false; // Placement failed!
        }
    }

    public boolean isValidPlacement(Ship ship, int row, int col, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < ship.getSize(); i++) {
                int targetCol = col + i;

                if (row < 0 || row > 9 || targetCol < 0 || targetCol > 9) {
                    return false;
                }

                if (matrix[row][targetCol] != '~') {
                    return false;
                }
            }
            return true;
        } else {
            // Vertical placement
            for (int i = 0; i < ship.getSize(); i++) {
                int targetRow = row + i;

                if (col < 0 || col > 9 || targetRow < 0 || targetRow > 9) {
                    return false;
                }

                if (matrix[targetRow][col] != '~') {
                    return false;
                }
            }
            return true;
        }
    }

    public void updateCell(int row, int col, char status) {
        matrix[row][col] = status;
    }

    public void displayGrid() {
        // Print column headers
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        for (int row = 0; row < 10; row++) {
            // Print row header at the start of each line
            System.out.print(row + " ");

            for (int col = 0; col < 10; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Getter
    public Ship getShipAt(int row, int col){
        return shipMatrix[row][col];
    }

    public char getCellStatus(int row, int col) {
        return matrix[row][col];
    }
}
