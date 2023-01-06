package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactoryConnector {
    public Connection recuperaConexion() throws SQLException {
        return DriverManager.getConnection(
                //jdbc:sqlite:C:/sqlite/JTP.db
                "jdbc:sqlite:C:/clubOdoo/aluraHotel.db"
                //?useTimeZone=true&serverTimeZone=UTC "admin","root1234"
                );
    }

}
