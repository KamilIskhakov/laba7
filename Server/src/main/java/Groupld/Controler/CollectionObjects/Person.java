package Groupld.Controler.CollectionObjects;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.Date;

// если используете джакарту для парсинга в xml, учтите, что порядок геттеров для нанесения ярлыков важен,
// он такой же, как в xmltype, а также в том же порядке напишите сеттеры!
@XmlRootElement(name = "person")
@XmlType(propOrder = {"name", "coordinates", "height", "weight", "eyeColor", "nationality", "location", "id", "creationDate"})
public class Person implements Comparable<Location>, Serializable {
    private String name; //no null
    private Coordinates coordinates; //no null
    private Integer height;
    private double weight; // > 0
    private Color eyeColor;
    private Country nationality; //no null
    private Location location;
    private String ownerUsername;
    private Integer id; //no null
    private Date creationDate; //no null

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

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @XmlElement
    public Integer getHeight() {
        return height;
    }

    @XmlElement
    public double getWeight() {
        return weight;
    }

    @XmlElement
    public Color getEyeColor() {
        return eyeColor;
    }

    @XmlElement
    public Country getNationality() {
        return nationality;
    }

    @XmlElement
    public Location getLocation() {
        return location;
    }
    @XmlElement
    public String getOwnerUsername(){
        return ownerUsername;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public Date getCreationDate() {
        return creationDate;
    }


    public int compareTo(Person personObj) {
        return id.compareTo(personObj.getId());

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String toString() {
        String info = "";
        info += "Человек" + id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public int compareTo(Location location) {
        return (this.location).getName().length() - location.getName().length();
    }
}
