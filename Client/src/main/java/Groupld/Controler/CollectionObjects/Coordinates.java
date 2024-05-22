package Groupld.Controler.CollectionObjects;



import java.io.Serializable;


public class Coordinates implements Serializable {
    private Float x;
    private Float y;

    public Coordinates(CoordinatesBuilder coordinatesBuilder) {
        x  = coordinatesBuilder.x;
        y = coordinatesBuilder.y;
    }
    public Coordinates(){

    }
    public static class CoordinatesBuilder {

        private Float x;
        private Float y;

        //constructor for required fields
        public CoordinatesBuilder(Float x, Float y) {
            this.x = x;
            this.y = y;
        }
        public Coordinates build() {
            return new Coordinates(this);
        }
    }


    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String toString() {
        return "X: " + x +"; " + "Y: " + y;
    }


}
