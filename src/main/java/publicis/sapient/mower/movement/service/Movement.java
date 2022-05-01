package publicis.sapient.mower.movement.service;

import publicis.sapient.mower.model.Surface;
import publicis.sapient.mower.model.Position;


/**
 * This class represent the mower movement
 */
public abstract class Movement {

    /**
     *
     * @param surface
     * @param position
     * @return
     */
    public boolean move(Surface surface, Position position) {
        Position newPosition = getNewPosition(position);
        if (!isRightLeftMovement() && !isBadXPosition(surface, newPosition.getX()) && !isBadYPosition(surface, newPosition.getY())) {
            position.setX(newPosition.getX());
            position.setY(newPosition.getY());
            return true;
        } else if (isRightLeftMovement()) {
            position.setOrientation(newPosition.getOrientation());
            return true;
        }
        return false;
    }

    private boolean isRightLeftMovement() {
        return this instanceof RightMovement || this instanceof LeftMovement;
    }

    /**
     * Calculate the new position of the mower after the instructed movement.
     */
    protected abstract Position getNewPosition(Position originalPosition);

    /**
     *
     * @param surface
     * @param y
     * @return true when the y position is greater than the max Y value.
     */
    protected boolean isBadYPosition(Surface surface, int y) {
        return y < 0 || y > surface.getSize().getY();
    }

    /**
     *
     * @param surface
     * @param x
     * @return return true when x position is greater than X value.
     */
    protected boolean isBadXPosition(Surface surface, Integer x) {
        return x < 0 || x > surface.getSize().getX();
    }
}
