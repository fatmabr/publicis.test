package publicis.sapient.mower.model;

public class Mower {

    /**
     * The position that occupy the mower at an instant t
     * defined by x and y
     */
    private Position position;
    /**
     * movements should  be done by "the mower" resulting of
     * the instructions given in the injected file
     * L : for left movement
     * F : for forward movement
     * R : for right movement
     */
    private Instruction[] instructions;

    public Mower() {
    }

    public Mower(Position position, Instruction[] instructions) {
        this.position = position;
        this.instructions = instructions;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }
}
