
public class Ship {

    // Fields
    String name = "";
    int size = 0;
    int hitsTaken = 0;
    boolean isSunk = false;

    // Constructor: Uses the ShioType Enum to initialize the name and size.
    public Ship(ShipType type) {
        this.name = type.getName();
        this.size = type.getSize();
        this.hitsTaken = 0;
        this.isSunk = false;
    }

    // Methods
    public void recordHit() {
        if (!isSunk) {
            hitsTaken++;
            checkIfSunk();
        }
    }

    public void checkIfSunk() {
        if (hitsTaken == size) {
            isSunk = true;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }

    public boolean IsSunk() {
        return isSunk;
    }
}
