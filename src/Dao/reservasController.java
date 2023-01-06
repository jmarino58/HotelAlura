package Dao;

import factory.FactoryConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reservasController {
    //buscar ultimo número de reserva en BBDD
    public int getNumeroReserva() throws SQLException {
        int resultado = 0;
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        try(con) {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT count(*) FROM Reservas");
            try(statement) {
                statement.execute();
                ResultSet result = statement.getResultSet();
             if (result.next()) {
                resultado = result.getInt(1);
             }
               
            }
    
            return resultado;
        }
    }

    public int guardarReserva(String fEntrada,String fSalida, String costo,int fPago) throws SQLException {
                
       FactoryConnector factory = new FactoryConnector();
        int idGenerate=0;
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Reservas"
                        + "(fechaEntrada, fechaSalida,valor,FormaDePago)"
                        + " VALUES (?, ?, ?,?)"
                        );
                        statement.setString(1,fEntrada);
                        statement.setString(2,fSalida);
                        statement.setFloat(3,Float.valueOf(costo));
                        statement.setInt(4,fPago);
            
            try(statement) {
                statement.execute();
                //con.commit();
                ResultSet result = statement.getGeneratedKeys();
                idGenerate = result.getInt(1);
                }
            } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK de la transacciÃ³n");
            con.rollback();
        }
        
        return idGenerate;
}
//Modificar Reservas

    public int modificar(int id, String fEntrada, String fSalida, float valor,int formaPago) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                .prepareStatement("UPDATE Reservas SET "
                        + " fechaEntrada = ?, "
                        + " fechaSalida = ?,"
                        + " valor = ?,"
                        + " formaDePago = ?"
                        + " WHERE ID = ?");
            
            try(statement) {
                statement.setString(1, fEntrada);
                statement.setString(2, fSalida);
                statement.setFloat(3, valor);
                statement.setInt(4, formaPago);
                statement.setInt(5,id);
                statement.execute();
        
                int updateCount = statement.getUpdateCount();
        
                return updateCount;
            }
        }
    }
    
    public int eliminar(Integer id) throws SQLException {
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        
        try(con) {
            final PreparedStatement statement = con
                .prepareStatement("DELETE FROM Reservas WHERE ID = ?");
            
            try(statement) {
                
                statement.setInt(1, id);
                
                
                statement.execute();
        
                int updateCount = statement.getUpdateCount();
        
                return updateCount;
            }
        }
    }

    
    
}
