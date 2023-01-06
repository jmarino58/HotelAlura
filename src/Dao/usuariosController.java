package Dao;

import factory.FactoryConnector;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class usuariosController {
    
//buscar usuario
    public String getUsuario(String nombre) throws SQLException {
        String resultado = "";
        FactoryConnector factory = new FactoryConnector();
        final Connection con = factory.recuperaConexion();
        try(con) {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT password FROM usuarios where nombre = ?");
            try(statement) {
                statement.setString(1, nombre);
                statement.execute();
        
                final ResultSet resultSet = statement.getResultSet();
                
                try(resultSet) {
                    while (resultSet.next()) {
                        resultado = resultSet.getString("password");
                        
                    }
                }
            }
    
            return resultado;
        }
    }

    
    
}
