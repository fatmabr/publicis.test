package publicis.sapient.mower.movement.service;

import publicis.sapient.mower.model.Orientation;
import publicis.sapient.mower.model.Position;

public class RightMovement extends Movement {

    @Override
    protected Position getNewPosition(Position position) {
        Position resultedPosition = new Position();
        switch (position.getOrientation()) {
            case N: {
                resultedPosition.setOrientation(Orientation.E);
                break;
            }
            case E: {
                resultedPosition.setOrientation(Orientation.S);
                break;
            }
            case W: {
                resultedPosition.setOrientation(Orientation.N);
                break;
            }
            case S: {
                resultedPosition.setOrientation(Orientation.W);
                break;
            }
            default:
                throw new IllegalStateException("Illegal orientation value !!! ");
        }
        return resultedPosition;
    }
}
