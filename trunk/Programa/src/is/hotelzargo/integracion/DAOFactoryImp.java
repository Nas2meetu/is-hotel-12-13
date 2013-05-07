package is.hotelzargo.integracion;

import is.hotelzargo.integracion.dao.BookDAO;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.integracion.dao.ClientDAOImp;
import is.hotelzargo.integracion.dao.EmployeeDAO;
import is.hotelzargo.integracion.dao.RoomDAO;
import is.hotelzargo.integracion.dao.ServicesDAO;
import is.hotelzargo.integracion.dao.ShiftDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactoryImp extends DAOFactory {
	
	
    Connection conexion = null;
    Statement s = null;
    ResultSet rs = null;
	
	

	public DAOFactoryImp() {
		
	/*	
		
		initDataBase();
		try {
			createDataBase();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	
	*/
	
	}
	
	

	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImp();
	}
	
	
	
	
	public void createDataBase() throws SQLException{
		s.executeUpdate("CREATE TABLE Clients (" +
				   		 "id INT AUTO_INCREMENT, " +
						 "PRIMARY KEY(id), " +
						 "nombre VARCHAR(20), " +
						 "apellidos VARCHAR(20), " +
						 "telefono VARCHAR(20))");
	}
	
	public void createTableClientsIndividual() throws SQLException{
		s.executeUpdate("CREATE TABLE Clients (" +
		   		 "id INT AUTO_INCREMENT, " +
				 "PRIMARY KEY(id), " +
				 "nombre VARCHAR(20), " +
				 "apellidos VARCHAR(20), " +
				 "telefono VARCHAR(20))");
		

		s.executeUpdate("CREATE TABLE IF NOT EXISTS ClientIndividual (" +
				  "id int(11) NOT NULL AUTO_INCREMENT, " +
				  "name varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "surname varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "dni varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "phone varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "creditCard varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "address varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "PRIMARY KEY (id), " +
				  "UNIQUE KEY dni (dni) " +
				") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ; ");
		
		
		
		
	}
	
	public void insertClientInidividual(String name, String surname, String dni, String phone, String creditCard,String address) throws SQLException{
		s.executeUpdate("INSERT INTO ClientIndividual (id, name, surname, dni, phone, creditCard, address) VALUES " +
		"(1, "+name+", "+surname+", "+dni+", "+phone+", "+creditCard+", "+address+");" );
	}
	
	
	public void deleteDataBase() throws SQLException{
		s.executeUpdate("DROP TABLE Clients");
	}
	
	
	
	public void initDataBase(){
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
			// Auto-generated catch block
			e1.printStackTrace();
		}
        
        
     // Establecemos la conexión con la base de datos.
        try {
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
        
     // Preparamos la consulta
        
		try {
			s = conexion.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
        try {
			rs = s.executeQuery ("select * from Clientes");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
        
     // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
        try {
			while (rs.next())
			{
			    System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3));
			}
		} catch (SQLException e) {
			// Auto-generated catch block
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
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	// MAIN 
	public static void main(String[] args) {
		//MainFrame.getInstance().setVisible(true);
		
		DAOFactoryImp d = new DAOFactoryImp();
	}



	@Override
	public BookDAO getBookDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public EmployeeDAO getEmployeeDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public RoomDAO getRoomDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ServicesDAO getServicesDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ShiftDAO getShiftDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
