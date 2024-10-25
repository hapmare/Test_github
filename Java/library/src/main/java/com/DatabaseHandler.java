package com;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DatabaseHandler {
    private static final String URL = "jdbc:derby:database/library2;create=true";
    private static Connection conn =null ;
    private static Statement stmt= null;
    public DatabaseHandler()
    {
        CreateConnection();
        SetUpBookTable();
        SetUpUserTable();
    }
    void SetUpBookTable()
    {
        String TableName = "Book";
        try {
            stmt = (Statement) conn.createStatement();
            DatabaseMetaData dmb = conn.getMetaData();
            ResultSet table = dmb.getTables(null,null,TableName.toUpperCase(),null );
            if(table.next()){
                System.out.println("Table " + TableName + "Already exist.Ready for go");
            }else{
                stmt.execute("Create Table " + TableName + "("
                        +"      id varchar(200) primary key,\n"
                        +"      title varchar(200),\n"
                        +"      author varchar(200),\n"
                        +"      publisher varchar(200),\n"
                        +"      isAvail boolean default true"
                        +" )");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void SetUpUserTable()
    {
        String TableName = "AppUser";
        try {
            stmt = (Statement) conn.createStatement();
            DatabaseMetaData dmb = conn.getMetaData();
            ResultSet table = dmb.getTables(null,null,TableName.toUpperCase(),null );
            if(table.next()){
                System.out.println("Use " + TableName + "Already exist.Ready for go");
            }else{
                stmt.execute("Create Table " + TableName + "("
                        +"      UserName varchar(200),\n"
                        +"      PassWord varchar(200)\n"
                        +" )");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void CreateConnection() // Kết nối với cơ sở dữ liệu
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet SelectQuery(String query) // Thực hiện truy vấn Select 
    {
        ResultSet result;
        try {
            stmt= conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("exception at ExecQuery" +e.getLocalizedMessage());
            return null;
        } finally{
        }
        return result;
    }
    public Boolean Insert(String qu) // thưc hiện truy vấn nếu thành công thì trả về true
    {
        try {
            stmt=conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error" +e.getMessage(), "Error Occured",JOptionPane.ERROR_MESSAGE);
            return false;
        } finally{
        }
    }
}