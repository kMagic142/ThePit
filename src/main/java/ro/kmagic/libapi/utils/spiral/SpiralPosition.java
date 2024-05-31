package ro.kmagic.libapi.utils.spiral;

public class SpiralPosition
{
    private final int x;
    private final int y;
    
    public SpiralPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public SpiralPosition subtract(final SpiralPosition spiralPosition) {
        return new SpiralPosition(this.x - spiralPosition.getX(), this.y - spiralPosition.getY());
    }
}
