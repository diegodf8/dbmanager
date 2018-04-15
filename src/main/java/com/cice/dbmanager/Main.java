package com.cice.dbmanager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main (String [] args){
        Manager manager = new Manager();
        manager.ejecutarUpdate("INSERT INTO prueba (nombre) VALUES('Test11')");
        manager.ejecutarUpdate("UPDATE prueba SET nombre='Upoddss' WHERE id=10 AND nombre='Test9'");
        manager.ejecutarUpdate("DELETE FROM prueba where id=14");
        ResultSet resultado = manager.ejecutarSelect("SELECT * FROM prueba");
        try {
            while (resultado.next()){
                String id = resultado.getString("id");
                String nm = resultado.getString("nombre");
                System.out.println(id + "-" +nm);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                resultado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
