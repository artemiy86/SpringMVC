package top.infoservice.springcourse.dao;

import org.springframework.stereotype.Component;
import top.infoservice.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;

    private static final String URL = "jdbc:postgresql://172.17.1.1:5432/first_db";
    private static final String userName = "postgres";
    private static final String password = "Dfkg423fcd";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,userName,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM Person";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }
        return people;
    }

    public Person show(int id) throws SQLException {
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        Person person = null;
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM Person WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }

    public void save(Person person) throws SQLException {
        //person.setId(++PEOPLE_COUNT);
        //people.add(person);
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO Person VALUES (1,?,?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.executeUpdate();
    }

    public void update(int id, Person updatedPerson) throws SQLException {
        //Person personToBeUpdated = show(id);

        //personToBeUpdated.setName(updatedPerson.getName());
        //personToBeUpdated.setAge(updatedPerson.getAge());
        //personToBeUpdated.setEmail(updatedPerson.getEmail());

        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
        preparedStatement.setString(1,updatedPerson.getName());
        preparedStatement.setInt(2,updatedPerson.getAge());
        preparedStatement.setString(3,updatedPerson.getEmail());
        preparedStatement.setInt(4,id);
        preparedStatement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        //people.removeIf(p -> p.getId() == id);
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM Person WHERE id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
}