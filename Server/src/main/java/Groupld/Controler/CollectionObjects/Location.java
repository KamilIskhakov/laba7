package Groupld.Controler.CollectionObjects;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class Location implements Serializable {
    @Column(name = "x_loc")
    private int x;
    @Column(name = "y_loc")
    private Float y;
    @Column(name = "z_loc")
    private double z;
    @Column(name = "name_loc")
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

    public String toString() {
        return "Координата X: " + x +"; "+
               "Координата Y: " + y +"; "+
               "Координата Z: " + z +"; "+
               "Город:" + name;
    }
}
