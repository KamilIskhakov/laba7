package Groupld.Controler.CollectionObjects;




import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;
import java.io.Serializable;


@Entity
@Getter
@Setter
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

    public String toString() {
        return "X: " + x +"; " + "Y: " + y;
    }


}
