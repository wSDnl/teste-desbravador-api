package com.wssh.InfraData.DataConfiguration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class Conn  implements Serializable {

    private static final String URL = DotEnvConfig.get("URL");
    private static final String USER = DotEnvConfig.get("USER");
    private static final String PASSWORD = DotEnvConfig.get("PASSWORD");

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Map<String, Object>> executeQuery(String query) throws SQLException {
    
        List<Map<String, Object>> resultado = new ArrayList<>();

        try(
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); 
            ) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultado.add(row);
            }    

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao executar a consulta: " + e.getMessage(), e);
        }finally {    
            //try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return resultado;

    }

    public void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
