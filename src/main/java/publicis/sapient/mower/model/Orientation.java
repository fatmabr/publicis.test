package publicis.sapient.mower.model;


public enum Orientation {


    N("NORTH"), E("EST"), W("WEST"), S("SOUTH");
    private final String label;

    Orientation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
