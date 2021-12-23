package com.dao;

import com.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class AccountDAO implements Dao<Account>{

    private Database db;
    private Account account;
    private ArrayList<Account> data = new ArrayList<>();
    private BCryptPasswordEncoder en = new BCryptPasswordEncoder();

    public static final String DB_ACCOUNTS = "Accounts";



    @Autowired
    public AccountDAO(Database db) {
        this.db = db;
    }


    public AccountDAO() {}

    @Override
    public void save(Account data) throws SQLException {
        Statement statement = db.getConnection().createStatement();

        statement.executeUpdate("INSERT INTO "+ DB_ACCOUNTS +
                " (id_status ,login, password) " +
                " VALUES ('" +
                data.getId_status() + "','" +
                data.getLogin() + "','" +
                en.encode(data.getPassword()) + "');");

    }

    @Override
    public Account get(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        Account account = new Account();
        ResultSet resultSet = statement.executeQuery("Select * from "+ DB_ACCOUNTS +" WHERE id="+ pattern + ";");

        while (resultSet.next()){
            account.setData(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_status"),
                    resultSet.getString("login"),
                    resultSet.getString("password")
            );
           }
        return account;
    }

    @Override
    public ArrayList<Account> getAll() throws SQLException {
        Statement statement = db.getConnection().createStatement();

        data.clear();

        ResultSet resultSet = statement.executeQuery("Select * from "+DB_ACCOUNTS+";");

        while (resultSet.next()){
            Account account = new Account();
            account.setData(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_status"),
                    resultSet.getString("login"),
                    resultSet.getString("password")
            );
           data.add(account);
        }
        return data;
    }

    public ArrayList<Account> findByStatus(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();

        data.clear();

        ResultSet resultSet = statement.executeQuery("Select * from "+DB_ACCOUNTS+" where id_status="+ pattern +";");

        while (resultSet.next()){
            Account account = new Account();
            account.setData(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_status"),
                    resultSet.getString("login"),
                    resultSet.getString("password")
            );
            data.add(account);
        }
        return data;
    }

    @Override
    public void delete(String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        statement.execute("DELETE FROM "+ DB_ACCOUNTS +" WHERE id="+ pattern + ";");
    }

    @Override
    public void update(Account data, String pattern) throws SQLException {
        Statement statement = db.getConnection().createStatement();
        statement.execute("UPDATE "+ DB_ACCOUNTS +
                " SET id_status='" +data.getId_status()+
                "', login='" +data.getLogin()+
                "', password='" +en.encode(data.getPassword())+
                "' WHERE id=" +pattern+";");
    }
}
