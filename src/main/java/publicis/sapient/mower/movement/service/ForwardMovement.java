package publicis.sapient.mower.movement.service;

import publicis.sapient.mower.model.Position;

public class ForwardMovement extends Movement {

    @Override
    protected Position getNewPosition(Position originalPosition) {
        Integer newX = originalPosition.getX();
        Integer newY = originalPosition.getY();
        Position newPosition = new Position();
        switch (originalPosition.getOrientation()) {
            case N: {
                newY = originalPosition.getY() + 1;
                break;
            }
            case E: {
                newX = originalPosition.getX() + 1;
                break;
            }
            case W: {
                newX = originalPosition.getX() - 1;
                break;
            }
            case S: {
                newY = originalPosition.getY() - 1;
                break;
            }
            default:
                throw new IllegalStateException("Illegal orientation value !!! ");
        }
        newPosition.setX(newX);
        newPosition.setY(newY);
        newPosition.setOrientation(originalPosition.getOrientation());
        return newPosition;
    }
}
