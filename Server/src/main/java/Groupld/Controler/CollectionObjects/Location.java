package Groupld.Controler.CollectionObjects;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
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

    public String toString() {
        return "Координата X: " + x +"; "+
               "Координата Y: " + y +"; "+
               "Координата Z: " + z +"; "+
               "Город:" + name;
    }
}
