package publicis.sapient.mower.file;

import publicis.sapient.file.CSVFileParser;
import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.model.line.Line;
import publicis.sapient.mower.model.Orientation;
import publicis.sapient.mower.model.line.MowerLine;
import publicis.sapient.mower.model.line.SurfaceLine;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a class that permits to parse a line in the given file
 * It permits to transform the file line to a useful object
 * Surface or Mower
 */
public class MowerLineParser extends CSVFileParser {
    /**
     * first line in the file represents the "mower" max X size and max Y size.
     * It s represented by just one line in the file.
     */
    public static final int HEADER_LINES_COUNT = 1;
    /**
     * Every "Mower" is given in two consecutive lines of the file.
     * First line is the initial position
     * Second line is the instructions to be fulfilled by the "Mower".
     */
    public static final int LINE_PER_ITEM = 2;

    public static final Character LINE_ITEM_SEPARATOR = ' ';

    /**
     * This function creates an item (Surface or Mower) according to the size of the line list.
     * If the {@link #isHeaderLine} is true, it return a Surface Line
     * Otherwise if the {@link #isHeaderLine} is false than it returns a Mower Line
     *
     * @param lines
     * @param isHeaderLine flag to mention if we should create a header line type or a normal line
     * @return
     */
    @Override
    public Line newItem(List<String[]> lines, boolean isHeaderLine) {
        try {
            if (isHeaderLine) {
                int mowerXSize = Integer.parseInt(lines.get(0)[0]);
                int mowerYSize = Integer.parseInt(lines.get(0)[1]);
                //new Surface
                return new SurfaceLine(mowerXSize, mowerYSize);
            } else {
                MowerLine mower = new MowerLine();
                int mowerInitialX = Integer.parseInt(lines.get(0)[0]);
                mower.setMowerX(mowerInitialX);
                int mowerInitialY = Integer.parseInt(lines.get(0)[1]);
                mower.setMowerY(mowerInitialY);
                mower.setOrientation(Orientation.valueOf(lines.get(0)[2]));
                List<Instruction> instructions = getInstructions(lines);
                mower.setInstructions(instructions);
                return mower;
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException(String.format("There is a problem with file format %s", exception.getMessage()));
        }
    }

    /**
     * parses the instruction line and transform it from String to #Instruction enum
     *
     * @param lines
     * @return
     */
    private List<Instruction> getInstructions(List<String[]> lines) {
        String[] strings = lines.get(1);
        char[] chars = strings[0].toCharArray();
        List<Instruction> instructions = new ArrayList<>();
        for (Character instructionV : chars) {
            Instruction instruction = Instruction.valueOf(instructionV.toString());
            if (instruction != null) {
                instructions.add(instruction);
            }
        }
        return instructions;
    }

    /**
     * method specifying the number of lines representing the surface
     * @return
     */
    public int getHeaderLinesCount() {
        return HEADER_LINES_COUNT;
    }
    /**
     * method specifying the number of lines representing the mower
     * @return
     */
    public int getLinePerItem() {
        return LINE_PER_ITEM;
    }

    @Override
    public Character getLineItemSeparator() {
        return LINE_ITEM_SEPARATOR;
    }
}
