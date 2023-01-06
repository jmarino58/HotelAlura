package Dao;

import factory.FactoryConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class huespedesController {

    public void guardarHuesped(String nombre, String apellido, String fnacimiento, String nacionalidad, String telefono, int idReserva) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        try (con) {
            final PreparedStatement statement = con.prepareStatement("INSERT INTO Huespedes" + "(nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva)" + " VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, fnacimiento);
            statement.setString(4, nacionalidad);
            statement.setString(5, telefono);
            statement.setInt(6, idReserva);
            try (statement) {
                statement.execute();
                // con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK de la transacci\u00c3\u00b3n");
            con.rollback();
        }
    }


//modificar Huesped
public int modificar(int id, String nombre, String apellido, String fNacimiento,String nacionalidad,String telefono) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                .prepareStatement("UPDATE Huespedes SET "
                        + " nombre = ?, "
                        + " apellido = ?,"
                        + " fechaNacimiento = ?,"
                        + " nacionalidad = ?,"
                        + " telefono = ?"
                        + " WHERE id = ?");
            
            try(statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5,telefono);
                statement.setInt(6,id);
                statement.execute();
        
                int updateCount = statement.getUpdateCount();
        
                return updateCount;
            }
        }
    }    

public int eliminarXId(Integer id) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                .prepareStatement("DELETE FROM Huespedes WHERE ID = ?");
            try(statement) {
                
                statement.setInt(1, id);
                statement.execute();
        
                int updateCount = statement.getUpdateCount();
            return updateCount;
            }
        }
    }

public int eliminarXReserva(Integer id) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                .prepareStatement("DELETE FROM Huespedes WHERE idReserva = ?");
            try(statement) {
                
                statement.setInt(1, id);
                statement.execute();
        
                int updateCount = statement.getUpdateCount();
            return updateCount;
            }
        }
    }
}
