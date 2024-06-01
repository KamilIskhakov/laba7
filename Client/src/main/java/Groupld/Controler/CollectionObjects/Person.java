package Groupld.Controler.CollectionObjects;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// если используете джакарту для парсинга в xml, учтите, что порядок геттеров для нанесения ярлыков важен,
// он такой же, как в xmltype, а также в том же порядке напишите сеттеры!

@Getter
@Setter
public class Person implements Comparable<Groupld.Controler.CollectionObjects.Location>, Serializable {

    private Integer id;

    private String name;

    private Groupld.Controler.CollectionObjects.Coordinates coordinates;

    private Integer height;

    private Double weight;

    private Color eyeColor;

    private Country nationality;

    private Groupld.Controler.CollectionObjects.Location location;

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
        private Groupld.Controler.CollectionObjects.Coordinates coordinates; //no null
        // no null
        private Integer height;
        private double weight; // > 0
        private Color eyeColor;
        private Country nationality; //no null
        private Groupld.Controler.CollectionObjects.Location location;

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

        public PersonBuilder setLocation(Groupld.Controler.CollectionObjects.Location location) {
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
