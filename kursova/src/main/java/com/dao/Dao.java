package com.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {
    void save(T data) throws SQLException;

    T get(String pattern) throws SQLException;

        ArrayList<T> getAll() throws SQLException;

    void delete(String pattern) throws SQLException;

    void update(T data, String pattern) throws SQLException;
}
