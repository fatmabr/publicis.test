package publicis.sapient.mower.movement.service;

import publicis.sapient.mower.movement.MovementFactory;
import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.model.Surface;
import publicis.sapient.mower.model.Position;
import publicis.sapient.mower.model.Mower;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MovementsExecutor {
    Logger logger = Logger.getLogger(MovementsExecutor.class.getName());
    /**
     * mower execution
     *
     * @param surface
     * @param mower
     */
    public void execute(final Surface surface, final Mower mower) {
        for (final Instruction instruction : mower.getInstructions()) {
            final Movement movement = MovementFactory.create(instruction);
            final Position position = mower.getPosition();
            movement.move(surface, position);
        }
    }
}
