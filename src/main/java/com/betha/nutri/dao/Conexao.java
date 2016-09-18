
package com.betha.nutri.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {
    
    private static Conexao INSTANCE;
    
    private java.sql.Connection conn;
    private final String url;
    private final String usuario;
    private final String senha;
    
    private Conexao() {
        url = "jdbc:postgresql://localhost/nutri";
        usuario = "postgres";
        senha = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Conexao get() {
        if (INSTANCE == null)
            INSTANCE = new Conexao();
        return INSTANCE;
    }
    
    public Statement getStm() throws SQLException {
        getConn();
        return conn.createStatement();
    }

    public PreparedStatement getParamStm(String sql) throws SQLException {
        getConn();
        return conn.prepareStatement(sql);
    }

    private void getConn() throws SQLException {
        if(conn == null)
            conn = DriverManager.getConnection(url, usuario, senha);
        if (conn.isClosed())
            conn = DriverManager.getConnection(url, usuario, senha);
    }
    
}
