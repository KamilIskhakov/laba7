package Groupld.Controler.CollectionObjects;


import java.io.Serializable;

public class Location implements Serializable {
    private int x;
    private Float y;
    private double z;
    private String name;

    public Location(LocationBuilder locationBuilder){
        x = locationBuilder.x;
        y = locationBuilder.y;
        z = locationBuilder.z;
        name = locationBuilder.name;
    }
    public Location(){

    }
    public static class LocationBuilder {

        private int x;
        private Float y; // no null
        private double z;
        private String name; //no null

        //constructor for required fields
        public LocationBuilder(String name, Float y ) {
            this.name = name;
            this.y = y;
        }
        public LocationBuilder setX(int x) {
            this.x = x;
            return this;
        }
        public LocationBuilder setZ(double z) {
            this.z = z;
            return this;
        }
        public Location build() {
            return new Location(this);
        }
    }

    public int getX(){
        return x;
    }

    public Float getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public String getName(){
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Координата X: " + x +"; "+
               "Координата Y: " + y +"; "+
               "Координата Z: " + z +"; "+
               "Город:" + name;
    }
}
