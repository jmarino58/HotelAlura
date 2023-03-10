package Dao;

import factory.FactoryConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class busquedaController {
//buscar Reserva por numero
    public List<Map<String, String>> getReservas(int numero) throws SQLException {
        List<Map<String, String>> resultado = new ArrayList<>();
        
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT id, fechaEntrada, FechaSalida, valor, formaDePago FROM Reservas where id = ?");
            
            try(statement) {
                statement.setInt(1, numero);
                statement.execute();
        
                final ResultSet resultSet = statement.getResultSet();
                
                try(resultSet) {
                    while (resultSet.next()) {
                        Map<String, String> fila = new HashMap<>();
                        fila.put("ID", String.valueOf(resultSet.getInt("id")));
                        fila.put("fechaEntrada", resultSet.getString("fechaEntrada"));
                        fila.put("fechaSalida", resultSet.getString("fechaSalida"));
                        fila.put("valor", String.valueOf(resultSet.getFloat("valor")));
                        fila.put("formaDePago",String.valueOf(resultSet.getInt("formaDePago")));
                        
            
                        resultado.add(fila);
                    }
                }
            }
    
            return resultado;
        }

    }

//buscar Huesped por apellido
    public List<Map<String, String>> getHuesped(String apellido) throws SQLException {
        List<Map<String, String>> resultado = new ArrayList<>();
        
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM Huespedes where apellido = ?");
            
            try(statement) {
                statement.setString(1, apellido);
                statement.execute();
        
                final ResultSet resultSet = statement.getResultSet();
                
                try(resultSet) {
                    while (resultSet.next()) {
                        Map<String, String> fila = new HashMap<>();
                        fila.put("id", String.valueOf(resultSet.getInt("id")));
                        fila.put("nombre", resultSet.getString("nombre"));
                        fila.put("apellido", resultSet.getString("apellido"));
                        fila.put("fechaNacimiento", resultSet.getString("fechaNacimiento"));
                        fila.put("nacionalidad",resultSet.getString("nacionalidad"));
                        fila.put("telefono",resultSet.getString("telefono"));
                        fila.put("idReserva",String.valueOf(resultSet.getInt("idReserva")));
                        resultado.add(fila);
                    }
                }
            }
    
            return resultado;
        }

    }    
}
