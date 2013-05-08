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
    private static Connection connection = null;
    private Statement s = null;
    private ResultSet rs = null;	
	

	public DAOFactoryImp() {		
		
		initDataBase();
		
		try {
			//se crean si no existen
			createTableClientsIndividual();
			createTableClientsCompany();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}	
	
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
				") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ; ");
		
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
				") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ; ");
	}
	
	//Insertar tipos de Usuario
	
	public void insertClientIndividual(String name, String surname, String dni, String phone, String creditCard,String address) throws SQLException{
		s.executeUpdate("INSERT INTO ClientIndividual (name, surname, dni, phone, creditCard, address) VALUES " +
		"("+name+", "+surname+", "+dni+", "+phone+", "+creditCard+", "+address+");" );
	}
	
	public void insertClientCompany(String company, String cif, String phone, String creditCard,String address) throws SQLException{
		s.executeUpdate("INSERT INTO ClientCompany (company, cif, phone, creditCard, address) VALUES " +
		"("+company+", "+cif+", "+phone+", "+creditCard+", "+address+");" );
	}
	

	//Eliminar tablas si existen

	public void deleteTableClientIndividual() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS ClientIndividual");

	}
	
	public void deleteTableClientsCompany() throws SQLException{
		s.executeUpdate("DROP TABLE IF EXISTS ClientCompany");
	}
	
	//Inicializa base de datos
	
	private void initDataBase(){
        
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
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
     // Ejemplo de consulta        
		
        
        try {
			s = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        try {
			rs = s.executeQuery ("select * from Clients");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
     // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
        try {
			while (rs.next())
			{
			    System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        
        //insertando prueba
        /*
        try {
			s.executeQuery( "INSERT INTO test.Clients (id, nombre, apellidos, tlf) " +
			     "VALUES ('5','nombreeba', 'gracia', '6258845') ");
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
	
	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImp(connection);
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
