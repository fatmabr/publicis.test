package publicis.sapient.mower.mower;

import publicis.sapient.mower.movement.service.LeftMovement;
import publicis.sapient.mower.movement.MovementFactory;
import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.movement.service.ForwardMovement;
import publicis.sapient.mower.movement.service.Movement;
import publicis.sapient.mower.movement.service.RightMovement;
import org.junit.Assert;
import org.junit.Test;

public class MovementFactoryTest {

    @Test
    public void testRightMovementCreationWhenInstructionD(){
        Movement movement = MovementFactory.create(Instruction.D);
        Assert.assertTrue(movement instanceof RightMovement);
    }
    @Test
    public void testLeftMovementCreationWhenInstructionG(){
        Movement movement = MovementFactory.create(Instruction.G);
        Assert.assertTrue(movement instanceof LeftMovement);
    }
    @Test
    public void testRightMovementCreationWhenInstructionF(){
        Movement movement = MovementFactory.create(Instruction.A);
        Assert.assertTrue(movement instanceof ForwardMovement);
    }
}
