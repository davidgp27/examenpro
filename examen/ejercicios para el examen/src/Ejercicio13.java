import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ejercicio13 {

            public static void main(String[] args) {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost/empleados2", "root", "Primera2024");

                    //Recuperar arg de main
                    String emp_no= args[0];        //num_e
                    String apellido= args[1];    //apellido
                    String oficio= args[2];        //oficio
                    String dir= args[3];        //dir
                    boolean sinDirector = dir.equals("0");
                    String salario= args[4];        //salario
                    String comision= args[5];        //comision
                    String dept_no= args[6];        //num dept

                    //departamento
                    String consultaDept = "SELECT COUNT(*) FROM DEPARTAMENTOS WHERE DEPT_NO = ?";
                    PreparedStatement pstmtDept = conexion.prepareStatement(consultaDept);
                    pstmtDept.setInt(1, Integer.parseInt(dept_no));



                    ResultSet resultadoDept = pstmtDept.executeQuery();
                    resultadoDept.next();
                    int countDept = resultadoDept.getInt(1);
                    resultadoDept.close();
                    pstmtDept.close();


                    if (countDept == 0) {
                        System.out.println("Error: El departamento con número " + dept_no + " no existe.");
                        conexion.close();
                        return;
                    }


                    //empleado
                    String consultaEmpleado = "SELECT COUNT(*) FROM EMPLEADOS WHERE EMP_NO = ?";
                    PreparedStatement pstmtEmpleado = conexion.prepareStatement(consultaEmpleado);
                    pstmtEmpleado.setInt(1, Integer.parseInt(emp_no));
                    ResultSet resultadoEmpleado = pstmtEmpleado.executeQuery();
                    resultadoEmpleado.next();


                    int countEmpleado = resultadoEmpleado.getInt(1);
                    resultadoEmpleado.close();
                    pstmtEmpleado.close();

                    if (countEmpleado > 0) {
                        System.out.println("Error: El empleado con número " + emp_no + " ya existe.");
                        conexion.close();
                        return;
                    }


                    //salario
                    if (Double.parseDouble(salario) <= 0) {
                        System.out.println("Error: El salario debe ser mayor que 0.");
                        conexion.close();
                        return;
                    }

                    if (!sinDirector) {
                        String consultaDirector = "SELECT COUNT(*) FROM EMPLEADOS WHERE EMP_NO = ?";
                        PreparedStatement pstmtDirector = conexion.prepareStatement(consultaDirector);
                        pstmtDirector.setInt(1, Integer.parseInt(dir));

                        ResultSet resultadoDirector = pstmtDirector.executeQuery();
                        resultadoDirector.next();
                        int countDirector = resultadoDirector.getInt(1);
                        resultadoDirector.close();
                        pstmtDirector.close();

                        if (countDirector == 0) {
                            System.out.println("Error: El director con número " + dir + " no existe en la tabla empleados.");
                            conexion.close();
                            return;
                        }
                    }


                    if (apellido == null || apellido.trim().isEmpty() || oficio == null || oficio.trim().isEmpty()) {
                        System.out.println("Error: El APELLIDO y el OFICIO no pueden ser nulos.");
                        conexion.close();
                        return;
                    }



                    //fecha
                    LocalDate fechaAlta = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaAltaStr = fechaAlta.format(formatter);
                    String dirValue = sinDirector ? "NULL" : dir;

                    String sql = String.format("INSERT INTO EMPLEADOS VALUES(%s, '%s', '%s', %s, '%s', %s, %s, %s)",
                            emp_no, apellido, oficio, dirValue, fechaAltaStr, salario, comision, dept_no);

                    System.out.println(sql);

                    Statement sentencia=conexion.createStatement();
                    int filas=sentencia.executeUpdate(sql);

                    if (filas > 0) {
                        System.out.println("Empleado insertado correctamente.");
                    }
                    else {
                        System.out.println("Error al insertar el empleado.");
                    }
                    sentencia.close();
                    conexion.close();

                }
                catch (ClassNotFoundException e) {
                    System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
                }
                catch (SQLException e) {
                    System.out.println("Error de SQL: " + e.getMessage());
                }
                catch (NumberFormatException e) {
                    System.out.println("Error: Uno o más argumentos numéricos no son válidos.");
                }
            }
}