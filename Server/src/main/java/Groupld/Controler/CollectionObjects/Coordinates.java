package Groupld.Controler.CollectionObjects;




import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Setter
@Getter
public class Coordinates implements Serializable {
    @Column(name = "x_coord")
    private Float x;
    @Column(name = "y_coord")
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
