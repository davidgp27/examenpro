import java.sql.*;
public class PrimeraParte {
    public static void main(String[] args){
        try {
            String url = "jdbc:mysql://localhost:3306/empresa";
            String usuario = "root";
            String contrasena = "Primera2024";

            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            DatabaseMetaData metaData = conexion.getMetaData();

            ResultSet tablas = metaData.getTables(null,null,"%",new String[]{"TABLE","VIEW"});

            System.out.println("Tablas y vistas de la base de datos: ");
            while (tablas.next()){
                System.out.println(tablas.getString("TABLE_NAME")+ " (");
            }
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
