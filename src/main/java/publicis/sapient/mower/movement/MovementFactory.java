package publicis.sapient.mower.movement;

import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.movement.service.Movement;

public class MovementFactory {

    private MovementFactory() {
    }

    public static Movement create(Instruction instruction){
       return instruction.create();
    }
}
