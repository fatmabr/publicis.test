package publicis.sapient.mower.model.line;

import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.model.Orientation;

import java.util.ArrayList;
import java.util.List;

public class MowerLine implements Line {

    private List<Instruction> instructions = new ArrayList<>();

    private int mowerX;
    private int mowerY;
    private Orientation orientation;

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public int getMowerX() {
        return mowerX;
    }

    public void setMowerX(int mowerX) {
        this.mowerX = mowerX;
    }

    public int getMowerY() {
        return mowerY;
    }

    public void setMowerY(int mowerY) {
        this.mowerY = mowerY;
    }
}
