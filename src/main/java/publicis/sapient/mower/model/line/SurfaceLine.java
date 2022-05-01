package publicis.sapient.mower.model.line;


public class SurfaceLine implements Header {

    public SurfaceLine(int surfaceXSize, int surfaceYSize) {
        this.surfaceXSize = surfaceXSize;
        this.surfaceYSize = surfaceYSize;
    }

    private int surfaceXSize;
    private int surfaceYSize;

    public int getSurfaceXSize() {
        return surfaceXSize;
    }

    public void setSurfaceXSize(int surfaceXSize) {
        this.surfaceXSize = surfaceXSize;
    }

    public int getSurfaceYSize() {
        return surfaceYSize;
    }

    public void setSurfaceYSize(int surfaceYSize) {
        this.surfaceYSize = surfaceYSize;
    }
}
