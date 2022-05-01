package publicis.sapient.mower.mower;

import publicis.sapient.mower.model.Position;
import publicis.sapient.mower.model.Surface;
import publicis.sapient.mower.movement.service.LeftMovement;
import publicis.sapient.mower.model.Orientation;
import publicis.sapient.mower.movement.service.ForwardMovement;
import publicis.sapient.mower.movement.service.RightMovement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovementTest {

    private Surface surface;

    @Before
    public void setUp()  {
        surface = new Surface(2, 3);
    }

    @Test
    public void testWhenMouvementExceedsSurface(){
        Surface surface1 = new Surface(1,1);
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.N);
        position.setX(0);
        position.setY(1);
        boolean move = forwardMovement.move(surface1, position);
        Assert.assertEquals(1,position.getY() );
        Assert.assertEquals(0, position.getX());
        Assert.assertFalse(move);
    }

    @Test
    public void testMovementForwardWhenNorthOrientation() {
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.N);
        position.setX(1);
        position.setY(1);
        boolean move = forwardMovement.move(surface, position);
        Assert.assertTrue(position.getY() == 2 && move);
    }

    @Test
    public void testMovementForwardWhenSouthOrientation() {
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.S);
        position.setX(1);
        position.setY(1);
        boolean move = forwardMovement.move(surface, position);
        Assert.assertTrue(position.getY() == 0 && move);
    }

    @Test
    public void testMovementForwardWhenWestOrientation() {
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.W);
        position.setX(1);
        position.setY(1);
        boolean move = forwardMovement.move(surface, position);
        Assert.assertTrue(position.getX() == 0 && move);
    }

    @Test
    public void testMovementForwardWhenEstOrientation() {
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.E);
        position.setX(1);
        position.setY(1);
        boolean move = forwardMovement.move(surface, position);
        Assert.assertTrue(position.getX() == 2 && move);
    }

    @Test
    public void testMovementForwardWhenBadNewPosition() {
        ForwardMovement forwardMovement = new ForwardMovement();
        Position position = new Position();
        position.setOrientation(Orientation.E);
        position.setX(2);
        position.setY(1);
        boolean move = forwardMovement.move(surface, position);
        Assert.assertTrue(position.getX() == 2 && !move);
    }

    @Test
    public void testMovementRightWhenNorthOrientation() {
        RightMovement rightMovement = new RightMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.N);
        boolean move = rightMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.E) && move);
    }

    @Test
    public void testMovementRightWhenSouthOrientation() {
        RightMovement rightMovement = new RightMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.S);
        boolean move = rightMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.W) && move);
    }

    @Test
    public void testMovementRightWhenWestOrientation() {
        RightMovement rightMovement = new RightMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.W);
        boolean move = rightMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.N) && move);
    }

    @Test
    public void testMovementRightWhenEstOrientation() {
        RightMovement rightMovement = new RightMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.E);
        boolean move = rightMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.S) && move);
    }

    @Test
    public void testMovementLeftWhenEstOrientation() {
        LeftMovement leftMovement = new LeftMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.E);
        boolean move = leftMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.N) && move);
    }@Test
    public void testMovementLeftWhenWestOrientation() {
        LeftMovement leftMovement = new LeftMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.W);
        boolean move = leftMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.S) && move);
    }@Test
    public void testMovementLeftWhenNorthOrientation() {
        LeftMovement leftMovement = new LeftMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.N);
        boolean move = leftMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.W) && move);
    }@Test
    public void testMovementLeftWhenSouthOrientation() {
        LeftMovement leftMovement = new LeftMovement();
        Position position = new Position();
        position.setX(1);
        position.setY(1);
        position.setOrientation(Orientation.S);
        boolean move = leftMovement.move(surface, position);
        Assert.assertTrue(position.getOrientation().equals(Orientation.E) && move);
    }

}
