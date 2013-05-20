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

import javax.swing.JOptionPane;

public class DAOFactoryImp extends DAOFactory {
	
	//TODO todas las funciones que hagas aqui menos los get de los DAO son privadas
    private static Connection connection = null;
    private Statement s = null;
    private ResultSet rs = null;	
	

	public DAOFactoryImp() {		
		
		initDataBase();
		
		/*try {
			//se crean si no existen
			//createTableClientsIndividual();
			//createTableClientsCompany();
			//createTableRooms();
			//createTableShifts();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
			System.out.println("Aqui estamos al crear cliente");
			
		}*/	
	
	}		
	
	
	//Crear Bases de datos si no existen

	
	private void createTableClientsIndividual() throws SQLException{

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
				") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; ");
		
	}
	
	
	private void createTableClientsCompany() throws SQLException{
		
		s.executeUpdate("CREATE TABLE IF NOT EXISTS ClientCompany (" +
				  "id int(11) NOT NULL AUTO_INCREMENT, " +
				  "company varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "cif varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "phone varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "creditCard varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "address varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, " +
				  "PRIMARY KEY (id), " +
				  "UNIQUE KEY cif (cif) " +
				") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; ");
		
	}
	
	private void createTableRooms() throws SQLException{
		
		s.executeUpdate("CREATE TABLE IF NOT EXISTS Rooms (" +
			  "id int(11) NOT NULL AUTO_INCREMENT, " +
			  "room_number int(5) NOT NULL, " +
			  "price float NOT NULL, " +
			  "bed_number int(2) NOT NULL, " +
			  "PRIMARY KEY (id) " +
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; ");
	}
	
	private void createTableShifts() throws SQLException{
		
		s.executeUpdate("CREATE TABLE IF NOT EXISTS Shifts (" +
			  "id int(11) NOT NULL AUTO_INCREMENT, " +
			  "nameShift int(11) NOT NULL, " +
			  "entryTime date NOT NULL, " +
			  "checkOut date NOT NULL, " +
			  "PRIMARY KEY (id), " +
			  "UNIQUE KEY nameShift (nameShift) " +
			") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; ");
		
	}
		

	//Eliminar tablas si existen

	private void deleteTableClientIndividual() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS ClientIndividual");

	}
	
	private void deleteTableClientsCompany() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS ClientCompany");
	}
	
	private void deleteTableRooms() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS Rooms");
	}
	
	private void deleteTableShifts() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS Shifts");
	}
	
	//Inicializa base de datos
	
	private void initDataBase(){
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null, "Connection refused!");
        } 
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Connection refused!");
		}
        
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection refused!");
		}
        
     // Ejemplo de consulta        
		
        
        try {
			s = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println("connnnnnnnecttion");
			JOptionPane.showMessageDialog(null, "Connection refused!");
		}
        
		/*try {
			//s.execute("INSERT INTO ClientIndividual (id,name, surname, dni, phone, creditCard, address) VALUES " +
			//		"('22','kjsdahk','jdsk','586235174','652563985','4444444','adrees');" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("MALLLLLLL");
		}*/
        
        //insertando prueba
        /*
        try {
			s.executeQuery( "INSERT INTO ClientIndividual (name, surname, dni,phone,creditCard,address) " +
			     "VALUES ('nameee','apellido', '456987123', '625884599','4444444444444','fdfdas') ");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
        
        
        st.executeUpdate("INSERT INTO contacto (id, nombre, apellidos, DNI ,tlf) " +
        		"VALUES ('"+nombres[i]+"','"+apellidos[i]+"','"+telefonos[i]+"' )");
        */
        
        
     // Cerramos la conexion a la base de datos.
        /*try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
	}
	
	public void closeConnectionDataBase(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImp(connection);
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOImp(connection);
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImp(connection);
	}

	@Override
	public RoomDAO getRoomDAO() {
		return new RoomDAOImp(connection);
	}

	@Override
	public ServicesDAO getServicesDAO() {
		return new ServicesDAOImp(connection);
	}

	@Override
	public ShiftDAO getShiftDAO() {
		return new ShiftDAOImp(connection);
	}
	
}
