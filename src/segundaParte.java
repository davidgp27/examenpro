import java.sql.*;
public class segundaParte {
    public static void main(String[] args){
        try {
            String url = "jdbc:mysql://localhost:3306/dbempresa";
            String usuario = "root";
            String contrasena = "Primera2024";

            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            DatabaseMetaData metaData = conexion.getMetaData();

            ResultSet columnas = metaData.getColumns(null,null,"departamentos",null);

            System.out.println("Información de las columnas de la tabla departamentos");
            while (columnas.next()){
                String nombre = columnas.getString("COLUMN_NAME");
                String tipo = columnas.getString("TYPE_NAME");
                int tamaño = columnas.getInt("COLUMN_SIZE");
                System.out.println(nombre + " -Tipo: " + tipo + "-Tamaño: "+ tamaño);
            }
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
