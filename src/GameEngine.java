
import java.util.Scanner;

public class GameEngine {

    //Fields
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player opponentPlayer;

    //Constructor
    public GameEngine() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.activePlayer = player1;
        this.opponentPlayer = player2;
    }

    //Methods
    public void start() {
        System.out.println("==========================");
        System.out.println("  Welcome to Battleship! ");
        System.out.println("==========================");

        setupPhase();
        playLoop();
        // checkGameOver();

    }

    private void setupPhase() {
        System.out.println("\n--- FLEET PLACEMENT PHASE ---");
        placeFleetsManually(player1);

        showTurnSwitchScreen(player2.getName());
        placeFleetsManually(player2);
    }

    private void placeFleetsManually(Player player) {
        Scanner scanner = new Scanner(System.in);

        for (Ship ship : player.getFleet()) {
            boolean isPlaced = false;

            while (!isPlaced) {
                player.getOceanGrid().displayGrid();
                System.out.println("\n" + player.getName() + " - Placing: " + ship.getName() + " (Size: " + ship.getSize() + ")");

                System.out.println("Enter starting Row (0-9): ");
                int row = scanner.nextInt();

                System.out.println("Enter starting Column (0-9): ");
                int col = scanner.nextInt();

                System.out.print("Direction (0 for Horizontal, 1 for Vertical): ");
                int dirChoice = scanner.nextInt();

                boolean horizontal = (dirChoice == 0);

                isPlaced = player.getOceanGrid().placeShip(ship, row, col, horizontal);
            }
            clearConsole();
        }

    }

    private void playLoop() {
        boolean gameRunning = true;

        while (gameRunning) {
            showTurnSwitchScreen(activePlayer.getName());

            System.out.println("" + activePlayer.getName().toUpperCase() + "'S BATTLE TURN");

            // Let the active player look at their status maps and fire a shot!
            activePlayer.takeTurn(opponentPlayer);

            // Check if that shot just ended the game
            if (opponentPlayer.hasLost()) {
                System.out.println("\n GAME OVER! " + activePlayer.getName() + " HAS SUNK THE ENEMY FLEET AND WON THE WAR!");
                gameRunning = false;
                break;
            }

            // Pause so they can read the hit/miss result before the screen clears
            waitForEnter();

            // Switch who is attacking and who is defending
            switchTurns();
        }
    }

    private void switchTurns() {
        if (activePlayer == player1) {
            activePlayer = player2;
            opponentPlayer = player1;
        } else {
            activePlayer = player1;
            opponentPlayer = player2;
        }
    }

    public void showTurnSwitchScreen(String nextPlayerName) {
        clearConsole();
        System.out.println("=============================================");
        System.out.println("          !!! PASS THE KEYBOARD !!!          ");
        System.out.println("=============================================");
        System.out.println("It is now " + nextPlayerName + "'s turn.");
        waitForEnter();
        clearConsole();
    }

    private void waitForEnter() {
        System.out.println("\nPress ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
