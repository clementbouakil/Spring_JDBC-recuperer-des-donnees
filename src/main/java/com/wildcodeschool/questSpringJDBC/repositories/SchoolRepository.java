package com.wildcodeschool.questSpringJDBC.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.wildcodeschool.questSpringJDBC.entities.School;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "@ps745GF";

    public static List<School> selectByCountry(String country) {
        try(
            Connection connection = DriverManager.getConnection(
                DB_URL, DB_USER, DB_PASSWORD);
                
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM school WHERE country LIKE ?");
        ) {
            statement.setString(1, country);

            try(
                ResultSet resulSet = statement.executeQuery();
            ) {
                List<School> schools = new ArrayList<School>();

                while(resulSet.next()){
                    int id = resulSet.getInt("id");
                    String name = resulSet.getString("name");
                    int capacity = resulSet.getInt("capacity");
                    country = resulSet.getString("country");
                    schools.add(new School(id, name, capacity, country));
                }
                return schools;
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", e);
        }
    }


}
