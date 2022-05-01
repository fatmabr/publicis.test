package publicis.sapient.mower.model;

/**
 * It represent the surface when the mower executes
 */
public class Surface {
    /**
     * Position is an X Y max value.
      */
    private Position size;

    public Surface(int sizeX, int sizeY) {
        this.size = new Position(sizeX, sizeY, null);
    }

    public Position getSize() {
        return size;
    }

    public void setSize(Position size) {
        this.size = size;
    }
}
