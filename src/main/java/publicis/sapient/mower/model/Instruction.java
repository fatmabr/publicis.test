package publicis.sapient.mower.model;


import publicis.sapient.mower.movement.service.ForwardMovement;
import publicis.sapient.mower.movement.service.LeftMovement;
import publicis.sapient.mower.movement.service.Movement;
import publicis.sapient.mower.movement.service.RightMovement;


public enum Instruction {

    /**
     * FORWARD movement
     */
    A {
        @Override
        public Movement create() {
            return new ForwardMovement();
        }
    },
    /**
     * RIGHT Movement
     **/
    D {
        @Override
        public Movement create() {
            return new RightMovement();
        }
    },
    /**
     * Left movement
     */
    G {
        @Override
        public Movement create() {
            return new LeftMovement();
        }
    };

    public abstract Movement create();
}
