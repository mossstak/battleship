
import java.util.*;

public class Player {

    //Fields
    private final String name;
    private final Grid oceanGrid;
    private Grid targetGrid;
    private List<Ship> fleet;

    //Constructor
    public Player(String name) {
        this.name = name;
        this.oceanGrid = new Grid();
        this.targetGrid = new Grid();
        this.fleet = new ArrayList<>();
        initializeFleet();
    }

    private void initializeFleet() {
        for (ShipType type : ShipType.values()) {
            Ship ship = new Ship(type);
            fleet.add(ship);
        }
    }

    //Getters and Setters
    public String getName() {
        return this.name;
    }

    public Grid getOceanGrid() {
        return this.oceanGrid;
    }

    public Grid getTargetGrid() {
        return this.targetGrid;
    }

    public List<Ship> getFleet() {
        return this.fleet;
    }

    //Methods
    public void takeTurn(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        Grid oppOcean = opponent.getOceanGrid();

        int row = -1;
        int col = -1;
        boolean validShot = false;

        System.out.println("\n Your Target Tracking Grid: ");
        this.targetGrid.displayGrid();

        while (!validShot) {
            System.out.println(this.name + ", enter target coordinates.");
            System.out.print("Enter Row (0-9): ");
            row = scanner.nextInt();
            System.out.print("Enter Column (0-9): ");
            col = scanner.nextInt();

            if (row < 0 || row > 9 || col < 0 || col > 10) {
                System.out.println("Error Out of Bounds! Please try again");
                continue;
            }

            char currentStatus = this.targetGrid.getCellStatus(row, col);
            if (currentStatus == 'X' || currentStatus == 'O') {
                System.out.println("Error: You've already fired at these coordinates! Try somewhere else.");
                continue; // Restarts the while loop
            }

            validShot = true;
        }

        Ship targetShip = oppOcean.getShipAt(row, col);

        if (targetShip != null) {
            // HIT!
            targetShip.recordHit();

            // Update both maps with a hit marker
            this.targetGrid.updateCell(row, col, 'X');
            oppOcean.updateCell(row, col, 'X');

            System.out.println("\n DIRECT HIT! You struck their " + targetShip.getName() + "!");

            // Bonus feedback: Tell them if they completely sank it
            if (targetShip.IsSunk()) {
                System.out.println(" You completely SUNK their " + targetShip.getName() + "!");
            }
        } else {
            // MISS!
            this.targetGrid.updateCell(row, col, 'O');
            oppOcean.updateCell(row, col, 'O');

            System.out.println("\n Splash... It's a miss.");
        }
    }

    public boolean hasLost() {
        for (Ship ship : fleet) {
            if (!ship.IsSunk()) {
                return false;
            }
        }
        return true;
    }

}
