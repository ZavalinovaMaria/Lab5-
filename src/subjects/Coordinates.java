package subjects;

public class Coordinates {
    /**
     * A class representing coordinates.
     */
    private float x;
    private Float y;
    /**
     * Creates a new coordinates instance.
     *
     * @param x coordinate x
     * @param y coordinate y
     */
    public Coordinates(float x, Float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(long x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "Coordinates{x=" +x+","+ ", y'" + y +
                '}';
    }
}
