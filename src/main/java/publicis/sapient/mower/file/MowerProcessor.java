package publicis.sapient.mower.file;

import publicis.sapient.file.AbstractFileProcessor;
import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.model.Mower;
import publicis.sapient.mower.model.Position;
import publicis.sapient.mower.model.Surface;
import publicis.sapient.mower.model.line.Header;
import publicis.sapient.mower.model.line.Line;
import publicis.sapient.mower.model.line.MowerLine;
import publicis.sapient.mower.model.line.SurfaceLine;
import publicis.sapient.mower.movement.service.MovementsExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MowerProcessor extends AbstractFileProcessor<Mower> {

    Logger log = Logger.getLogger(MowerProcessor.class.getName());

    @Autowired
    private MovementsExecutor movementsExecutor;

    @Override
    public Mower process(Header header, Line line) {
        Surface surface = createSurface((SurfaceLine) header);
        Mower mower = createMower((MowerLine) line);
        movementsExecutor.execute(surface, mower);
        if (log.isLoggable(Level.INFO)) {
            log.info(mower.getPosition().toString());
        }
        return mower;
    }

    private Mower createMower(MowerLine line) {
        List<Instruction> instructions = line.getInstructions();
        Instruction[] instructionsArray = instructions.toArray(new Instruction[instructions.size()]);
        return new Mower(new Position(line.getMowerX(), line.getMowerY(), line.getOrientation()), instructionsArray);
    }

    private Surface createSurface(SurfaceLine surfaceLine) {
        return new Surface(surfaceLine.getSurfaceXSize(), surfaceLine.getSurfaceYSize());
    }
}
