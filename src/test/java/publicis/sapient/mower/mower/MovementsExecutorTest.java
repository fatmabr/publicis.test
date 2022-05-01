package publicis.sapient.mower.mower;

import org.junit.jupiter.api.Assertions;

import publicis.sapient.mower.model.*;
import publicis.sapient.mower.movement.service.MovementsExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;


public class MovementsExecutorTest {

    Logger logger = Logger.getLogger(MovementsExecutorTest.class.getName());
    private Mower mower;
    private Surface surface;

    @Before
    public void setUp()  {
        surface = new Surface(5, 5);
    }

    @Test
    public void testMovementExecutor()   {
        Instruction[] instructions = {                Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.A,
        };
        mower = new Mower(new Position(1, 2, Orientation.N), instructions);
        MovementsExecutor movementsExecutor = new MovementsExecutor();
        movementsExecutor.execute(surface, mower);
        Assertions.assertTrue(mower.getPosition().getX() == 1 && mower.getPosition().getY() == 3 &&
                mower.getPosition().getOrientation().equals(Orientation.N));
    }

    @Test
    public void testMovementExecutor2()  {
        Instruction[] instructions = {                Instruction.A, Instruction.A,
                Instruction.D, Instruction.A,
                Instruction.A, Instruction.D,
                Instruction.A, Instruction.D,
                Instruction.D, Instruction.A
        };
        mower = new Mower(new Position(3, 3, Orientation.E), instructions);
        MovementsExecutor movementsExecutor = new MovementsExecutor();
        movementsExecutor.execute(surface, mower);
        Assert.assertTrue(mower.getPosition().getX() == 5 && mower.getPosition().getY() == 1 &&
                mower.getPosition().getOrientation().equals(Orientation.E));
    }

    @Test
    public void testConcurrency() throws InterruptedException {

        MovementsExecutor movementsExecutor = new MovementsExecutor();
        MovementsExecutor movementsExecutor1 = new MovementsExecutor();


        Instruction[] instructions1 = {Instruction.A, Instruction.A,
                Instruction.D, Instruction.A,
                Instruction.A, Instruction.D,
                Instruction.A, Instruction.D,
                Instruction.D, Instruction.A
        };
        final Mower mower1 = new Mower(new Position(3, 3, Orientation.E), instructions1);

        Thread thread = new Thread(() ->
                movementsExecutor.execute(surface, mower1));

        Instruction[] instructions2 = {Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.G, Instruction.A,
                Instruction.A,
        };
        final Mower mower2 = new Mower(new Position(1, 2, Orientation.N), instructions2);

        Thread thread2 = new Thread(() ->{
                movementsExecutor1.execute(surface, mower2);
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        logger.info(""+mower1.getPosition());
        logger.info(""+mower2.getPosition());

        Assert.assertTrue(mower1.getPosition().getX() == 5 && mower1.getPosition().getY() == 1 &&
                mower1.getPosition().getOrientation().equals(Orientation.E));
        Assert.assertTrue(mower2.getPosition().getX() == 1 && mower2.getPosition().getY() == 3 &&
                mower2.getPosition().getOrientation().equals(Orientation.N));


    }
}
