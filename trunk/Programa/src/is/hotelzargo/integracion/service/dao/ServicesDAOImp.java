package is.hotelzargo.integracion.service.dao;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ServicesDAOImp implements ServicesDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public ServicesDAOImp(){}

	@Override
	public void createService(ServiceTransfer t) throws ServicesIntegrationException {
		
		initDataBase();
		
		String service = ((ServiceTransfer) t).getServices();
		
		try {
			
			statement.executeUpdate("INSERT INTO Services (services) VALUES " +
					"('"+service+"');" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al crear servicio");			
		}finally{
			closeConnectionDataBase();
		}
		
	}

	@Override
	public void deleteService(int id) throws ServicesIntegrationException {
		
		initDataBase();
		
		String QueryString = "DELETE FROM Services WHERE idServices='"+id+"';";
		try {
					  
			statement.executeUpdate(QueryString);			
					
		} catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al eliminar servicio con ID "+id);				
		}finally{
			closeConnectionDataBase();
		}
		
	}

	@Override
	public ServiceTransfer getService(int id)
			throws ServicesIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Services WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
					String name = rs.getString(1);
					
					ServiceTransfer s = new ServiceTransfer(id,name);					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ServicesIntegrationException("Problema al referenciar servicio con ID "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
		
	}

	@Override
	public Vector<ServiceTransfer> listService()
			throws ServicesIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Services;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro servicios, metiendolos en el vector
			Vector<ServiceTransfer> services = new Vector<ServiceTransfer>();
			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(1);
				  	String name = rs.getString(2);
					
				  	ServiceTransfer s = new ServiceTransfer(id,name);						
				  	services.add(s);
				  
			  }
			  return services;
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al referenciar listado servicios");				
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public void updateService(ServiceTransfer t)
			throws ServicesIntegrationException {
		
		initDataBase();
		
		int id = ((ServiceTransfer) t).getId();
		String name = ((ServiceTransfer) t).getServices();
		

		//UPDATE
		String QueryString = "UPDATE Services SET services='"+name+"' WHERE idServices='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al actualizar servicio "+name);				
		  }finally{
			  closeConnectionDataBase();
		  }
		  
	}

	@Override
	public boolean searchShift(String serviceName)
			throws ServicesIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Services WHERE services='"+serviceName+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al buscar servicio "+serviceName);				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}

	@Override
	public boolean searchShiftByID(int id) throws ServicesIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Services WHERE idServices='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al buscar servicio "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}

	private void initDataBase() throws ServicesIntegrationException {
		
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	//JOptionPane.showMessageDialog(null, "Connection refused!");
        	throw new ServicesIntegrationException("Conexion rechazada");
        }
        
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new ServicesIntegrationException("Conexion rechazada");
		}
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", null);
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new ServicesIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			throw new ServicesIntegrationException("Conexion rechazada");
		}
		
	}
	
	private void closeConnectionDataBase() throws ServicesIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ServicesIntegrationException("Error al desconectar BBDD");
		}
	}
	
}
