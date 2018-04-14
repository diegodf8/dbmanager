package com.cice.dbmanager;

import java.sql.*;
import java.util.List;

/**
 * Clase encargada de generar el acceso y uso de la base de datps
 */

public class Manager {

    private final String DRIVER;
    private final String HOST;
    private final String PUERTO;
    private final String USER;
    private final String PASS;
    private final String DATABASE;

    private Connection connection;
    private Statement statement;

    public Manager(){
        this.DRIVER = "com.mysql.jdbc.Driver";
        this.HOST = "localhost";
        this.PUERTO = "8889";
        this.USER = "root";
        this.PASS = "root";
        this.DATABASE = "diego";

    }

    public Manager(String DRIVER, String HOST, String PUERTO, String USER, String PASS, String DATABASE) {
        this.DRIVER = DRIVER;
        this.HOST = HOST;
        this.PUERTO = PUERTO;
        this.USER = USER;
        this.PASS = PASS;
        this.DATABASE = DATABASE;
    }

    private String generarUrl(){
        return "jdbc:mysql://"+HOST+":"+PUERTO+"/"+DATABASE;
    }
    
    /**
     * Metodo que se utiliza para conectar contra una base de datos, ya se por defecto
     * o inicializada según los parámetros del constructor
     * @return estado de conexión (true en caso afirmativo)
     */
    private boolean conectarBaseDatos(){
        boolean esConectado = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(generarUrl(),USER, PASS);
            if(connection != null){
                esConectado = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return esConectado;
    }

    /**
     * Método que usaremos para desconectarnosde la base de datos y asi liberar recursos
     * @return estado de conexion (true si se desconecta)
     */
    private boolean desconectarBaseDatos(){
        boolean esDesconectado = false;
        try {
            connection.close();
            esDesconectado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esDesconectado;
    }

    public void ejecutarSelect(String sql){
        conectarBaseDatos();
        try {
            ResultSet resultado = statement.executeQuery(sql);
            while (resultado.next()){
                int numeroColumnas = resultado.getMetaData().getColumnCount();
                for (int i = 0 ; i < numeroColumnas ; i++){
                    String dato = resultado.getString(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        desconectarBaseDatos();
    }
}
