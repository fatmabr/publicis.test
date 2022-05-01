package publicis.sapient.mower.movement.service;

import publicis.sapient.mower.model.Position;
import publicis.sapient.mower.model.Orientation;

public class LeftMovement extends Movement {
    @Override
    protected Position getNewPosition(Position position) {
        Position resultedPosition = new Position();
        resultedPosition.setY(position.getY());
        resultedPosition.setX(position.getX());
        switch (position.getOrientation()){
            case N: {
                resultedPosition.setOrientation(Orientation.W);
                break;
            } case E:{
                resultedPosition.setOrientation(Orientation.N);
                break;
            } case W: {
                resultedPosition.setOrientation(Orientation.S);
                break;
            } case S:{
                resultedPosition.setOrientation(Orientation.E);
                break;
            }
            default: throw new IllegalStateException("Illegal orientation value !!! ");
        }
        return resultedPosition;
    }
}
