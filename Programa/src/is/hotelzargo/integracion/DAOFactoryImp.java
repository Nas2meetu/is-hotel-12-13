package is.hotelzargo.integracion;

import is.hotelzargo.integracion.dao.BookDAO;
import is.hotelzargo.integracion.dao.BookDAOImp;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.integracion.dao.ClientDAOImp;
import is.hotelzargo.integracion.dao.EmployeeDAO;
import is.hotelzargo.integracion.dao.EmployeeDAOImp;
import is.hotelzargo.integracion.dao.RoomDAO;
import is.hotelzargo.integracion.dao.RoomDAOImp;
import is.hotelzargo.integracion.dao.ServicesDAO;
import is.hotelzargo.integracion.dao.ServicesDAOImp;
import is.hotelzargo.integracion.dao.ShiftDAO;
import is.hotelzargo.integracion.dao.ShiftDAOImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactoryImp extends DAOFactory {
	
	//TODO todas las funciones que hagas aqui menos los get de los DAO son privadas
    Connection conexion = null;
    Statement s = null;
    ResultSet rs = null;
	
	

	public DAOFactoryImp() {
		
	/*	
		
		initDataBase();
		try {
			createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	*/
	
	}
	
	private void createDataBase() throws SQLException{
		s.executeUpdate("CREATE TABLE Clients (" +
				"		  id INT AUTO_INCREMENT, " +
						 "PRIMARY KEY(id), " +
						 "nombre VARCHAR(20), " +
						 "apellidos VARCHAR(20), " +
						 "telefono VARCHAR(20))");
	}
	
	
	private void deleteDataBase() throws SQLException{
		s.executeUpdate("DROP TABLE Clients");
	}
	
	
	
	private void initDataBase(){
 //PROBANDO BASE DE DATOS
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
           e.printStackTrace();
        } 
        

        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        
     // Establecemos la conexión con la base de datos.
        try {
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
     // Preparamos la consulta
        
		try {
			s = conexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        try {
			rs = s.executeQuery ("select * from Clientes");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
     // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
        try {
			while (rs.next())
			{
			    System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        /*
        
        //insertando prueba
        s.executeQuery( "INSERT INTO test.Clientes (id, nombre, apellidos, DNI, tlf, tarjetaCredito) 
        VALUES ('2', 'probando', 'gracia', '58422', '6258845', '555555') ");
        
        
        st.executeUpdate("INSERT INTO contacto (id, nombre, apellidos, DNI ,tlf) " +
        		"VALUES ('"+nombres[i]+"','"+apellidos[i]+"','"+telefonos[i]+"' )");
        
        */
        
     // Cerramos la conexion a la base de datos.
        try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImp();
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOImp();
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImp();
	}

	@Override
	public RoomDAO getRoomDAO() {
		return new RoomDAOImp();
	}

	@Override
	public ServicesDAO getServicesDAO() {
		return new ServicesDAOImp();
	}

	@Override
	public ShiftDAO getShiftDAO() {
		return new ShiftDAOImp();
	}
	
}
