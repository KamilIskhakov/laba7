package Groupld.Server.collectionmanagers.datamanagers;

import Groupld.Controler.CollectionObjects.*;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

public class SQLDataManager {
    private static final int NAME_INDEX = 1;
    private static final int X_COORD_INDEX = 2;
    private static final int Y_COORD_INDEX = 3;
    private static final int HEIGHT_INDEX = 4;
    private static final int WEIGHT_INDEX = 5;
    private static final int COLOR_INDEX = 6;
    private static final int COUNTRY_INDEX = 7;
    private static final int X_LOC_INDEX = 8;
    private static final int Y_LOC_INDEX = 9;
    private static final int Z_LOC_INDEX = 10;
    private static final int NAME_LOC_INDEX = 11;
    private static final int ID_INDEX = 12;
    private static final int CREATION_DATE_INDEX = 12;
    private static final int OWNER_INDEX = 13;
    private static final int KEY_INDEX = 14;
    private final Connection connection;
    private final String peopleTableName;
    private final String usersTableName;
    private final Logger logger;

    public SQLDataManager(Connection connection, String peopleTableName, String usersTableName, Logger logger) {
        this.connection = connection;
        this.peopleTableName = peopleTableName;
        this.usersTableName = usersTableName;
        this.logger = logger;
    }

    private void createDataTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS " + peopleTableName
                + "(id INTEGER PRIMARY KEY, "
                + "key BIGINT NOT NULL, "
                + "name VARCHAR(50) NOT NULL, "
                + "x_coord FLOAT NOT NULL, "
                + "y_coord FLOAT NOT NULL, "
                + "height INTEGER, "
                + "weight DOUBLE NOT NULL CHECK(weight>0), "
                + "color VARCHAR(100), "
                + "country VARCHAR(100) NOT NULL, "
                + "x_loc INT, "
                + "y_loc FLOAT NOT NULL, "
                + "z_loc DOUBLE, "
                + "name_loc VARCHAR(100) NOT NULL, "
                + "creation_date TIMESTAMP NOT NULL, "
                + "owner VARCHAR(100) NOT NULL,"
                + "FOREIGN KEY(owner) REFERENCES " + usersTableName + "(username))");
    }

    public ConcurrentHashMap<Integer, Person> initCollection() throws SQLException {
        createDataTable();
        ConcurrentHashMap<Integer, Person> people = new ConcurrentHashMap<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM " + peopleTableName);
        while (result.next()) {
            people.put(result.getInt("key"), getPersonFromTable(result));
        }
        logger.info(() -> "added " + people.size() + " objects from the database");
        return people;
    }

    private Person getPersonFromTable(ResultSet result) throws SQLException {
        Person person = new Person.PersonBuilder(result.getString("name"),
                new Coordinates.CoordinatesBuilder(result.getFloat("x_coord"),
                result.getFloat("y_coord")).build(),
                result.getDouble("weight"),
                Country.valueOf(result.getString("country"))).
                setColor(Color.valueOf(result.getString("color")))
                .setHeight(result.getInt("height"))
                .setLocation(
                new Location.LocationBuilder(result.getString("name_loc"),
                result.getFloat("x_loc"))
                .setX(result.getInt("y_loc"))
                .setZ(result.getDouble("z_loc")).build())
                .build();
        person.setId(result.getInt("id"));
        person.setCreationDate(result.getTimestamp("creation_date"));
        return person;
    }

    public boolean removeByKey(Integer key) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
                    + peopleTableName + " WHERE key=?");
            preparedStatement.setLong(1, key);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("error during removing object from table", e);
            return false;
        }
        return true;
    }

    public Integer add(Integer key, Person person) {
        Integer id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + peopleTableName
                    + "(id,name,x,y,height,weight,color,country,x_loc,y_loc,z_loc,name_loc,"
                    + "creation_date,owner,key) VALUES (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id");
            prepareStatement(preparedStatement, person);
            preparedStatement.setTimestamp(CREATION_DATE_INDEX, new Timestamp(person.getCreationDate().getTime()));
            preparedStatement.setString(OWNER_INDEX, person.getOwnerUsername()); // здесь должно быть имя владельца
            preparedStatement.setInt(KEY_INDEX, key);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            id = result.getInt("id");
        } catch (SQLException e) {
            logger.warn("error during adding object to table", e);
            return null;
        }
        return id;
    }

    public boolean update(Integer id, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + peopleTableName + " SET "
                    + "name=?, x_coord=?, y_coord=?, height=?, weight=?, color=?, country=?,"
                    + " x_loc=?, y_loc=?,z_loc=?,name_loc=? WHERE id=?");
            prepareStatement(preparedStatement, person);
            preparedStatement.setLong(ID_INDEX, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.warn("error during updating object from table", e);
            return false;
        }
        return true;
    }

    private void prepareStatement(PreparedStatement statement, Person person) throws SQLException {
        statement.setString(NAME_INDEX, person.getName());
        statement.setDouble(X_COORD_INDEX, person.getCoordinates().getX());
        statement.setFloat(Y_COORD_INDEX, person.getCoordinates().getY());
        statement.setInt(HEIGHT_INDEX, person.getHeight());
        statement.setDouble(WEIGHT_INDEX, person.getWeight());
        statement.setString(COLOR_INDEX, String.valueOf(person.getEyeColor()));
        statement.setString(COUNTRY_INDEX, String.valueOf(person.getNationality()));
        statement.setInt(X_LOC_INDEX, person.getLocation().getX());
        statement.setFloat(Y_LOC_INDEX, person.getLocation().getY());
        statement.setDouble(Z_LOC_INDEX, person.getLocation().getZ());
        statement.setString(NAME_LOC_INDEX, person.getLocation().getName());
    }

    public boolean deleteAllOwned(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + peopleTableName
                    + " WHERE owner=?");
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException e) {
            logger.warn("error during removing object from table", e);
            return false;
        }
        return true;
    }
}
