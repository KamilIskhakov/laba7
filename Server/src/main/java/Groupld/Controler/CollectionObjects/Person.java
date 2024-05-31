package Groupld.Controler.CollectionObjects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// если используете джакарту для парсинга в xml, учтите, что порядок геттеров для нанесения ярлыков важен,
// он такой же, как в xmltype, а также в том же порядке напишите сеттеры!
@Entity
@Getter
@Setter
public class Person implements Comparable<Location>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Coordinates coordinates;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Enumerated(EnumType.STRING)
    private Color eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country nationality;

    @Embedded
    private Location location;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Person(PersonBuilder personBuilder) {
        name = personBuilder.name;
        coordinates = personBuilder.coordinates;
        height = personBuilder.height;
        weight = personBuilder.weight;
        eyeColor = personBuilder.eyeColor;
        nationality = personBuilder.nationality;
        location = personBuilder.location;
    }

    public Person() {
    }

    public static class PersonBuilder {
        //no null
        private String name; //no null
        private Coordinates coordinates; //no null
        // no null
        private Integer height;
        private double weight; // > 0
        private Color eyeColor;
        private Country nationality; //no null
        private Location location;

        //constructor for required fields
        public PersonBuilder(String name, Coordinates coordinates, double weight, Country nationality) {
            this.name = name;
            this.coordinates = coordinates;
            this.weight = weight;
            this.nationality = nationality;
        }

        public PersonBuilder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public PersonBuilder setColor(Color eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public PersonBuilder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }



    public String toString() {
        String info = "";
        info += "Человек";
        info += " (добавлен " + creationDate.toString() + " " + creationDate.toString() + ")";
        info += "\n Имя: " + name;
        info += "\n Координаты: " + coordinates;
        info += "\n Рост: " + height;
        info += "\n Вес: " + weight;
        info += "\n Цвет глаз: " + eyeColor;
        info += "\n Страна проживания: " + nationality;
        info += "\n Местоположение: " + location;
        info += "\n id: " + id;
        return info;
    }


    @Override
    public int compareTo(Location location) {
        return (this.location).getName().length() - location.getName().length();
    }
}
