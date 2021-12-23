package com.dao;

import com.models.Subject;
import com.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@Component
public class TeacherDAO implements Dao<Teacher> {
    private Database db;
    public static final String DB_TEACHERS = "Teachers";
    public static final String DB_SUBJECTS = "Subjects";
    Teacher teacher = new Teacher();
    Subject subject = new Subject();
    private ArrayList<Teacher> data = new ArrayList<>();

    @Autowired
    public TeacherDAO(Database db) {
        this.db = db;
    }

    public TeacherDAO() {}

    @Override
    public void save(Teacher data) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        statement.executeUpdate("INSERT INTO " + DB_TEACHERS +
                " (name, surname, fathersname) " +
                " VALUES ('" +
                data.getName() + "','" +
                data.getSurname() + "','" +
                data.getFathersname()+ "');");
    }

    @Override
    public Teacher get(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        Teacher teacher = new Teacher();
        Subject subject = new Subject();
        ArrayList<String> subjects = new ArrayList<>();

        ResultSet resultSet1 = statement.executeQuery("Select * from "+ DB_SUBJECTS +" WHERE id_teacher="+ pattern + ";");
        while(resultSet1.next()){
            subjects.add(resultSet1.getString("name"));
        }


        ResultSet resultSet = statement.executeQuery("Select * from "+ DB_TEACHERS +" WHERE id="+ pattern + ";");

        while (resultSet.next()){
            teacher.setData(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("fathersname"),
                    subjects
            );
        }
        return teacher;
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        Statement statement = db.getConnection().createStatement();
        Subject subject = new Subject();
        SubjectDAO subjectDAO = new SubjectDAO();
        ArrayList<String> subjects = new ArrayList<>();

        data.clear();

        ResultSet resultSet = statement.executeQuery("Select * from " + DB_TEACHERS + ";");

        while (resultSet.next()){
            Teacher teacher = new Teacher();
                teacher.setData(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("fathersname"),
                        subjects
                );
                data.add(teacher);

        }
        return data;
    }


    public ArrayList<Teacher> findBySubject(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        Subject subject = new Subject();
        SubjectDAO subjectDAO = new SubjectDAO();
        ArrayList<String> subjects = new ArrayList<>();

        data.clear();

        ResultSet resultSet = statement.executeQuery("Select * from " + DB_TEACHERS + ", " + DB_SUBJECTS +
                " where teachers.id = subjects.id_teacher and subjects.name='" + pattern+"';");

        subjects.add(pattern);

        while (resultSet.next()){
            Teacher teacher = new Teacher();
            teacher.setData(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("fathersname"),
                    subjects
            );
            data.add(teacher);

        }
        return data;
    }

    @Override
    public void delete(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        statement.execute("DELETE FROM "+ DB_TEACHERS +" WHERE id="+ pattern + ";");
    }

    @Override
    public void update(Teacher data, String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        statement.execute("UPDATE "+ DB_TEACHERS +
                " SET name='" +data.getName()+
                "', surname='" +data.getSurname()+
                "', fathersname='" +data.getFathersname()+
                "' WHERE id=" +pattern+";");
    }




}
