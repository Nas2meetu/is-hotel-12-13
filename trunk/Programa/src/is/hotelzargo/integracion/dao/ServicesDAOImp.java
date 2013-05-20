package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ServicesDAOImp implements ServicesDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public ServicesDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void createService(ServiceTransfer t) throws ServicesIntegrationException {
		
		String service = ((ServiceTransfer) t).getServices();
		
		try {
			
			statement.executeUpdate("INSERT INTO Services (services) VALUES " +
					"('"+service+"');" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al crear servicio");			
		}
		
	}

	@Override
	public void deleteService(int id) throws ServicesIntegrationException {
		
		String QueryString = "DELETE FROM Services WHERE idServices='"+id+"';";
		try {
					  
			statement.executeUpdate(QueryString);			
					
		} catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al eliminar servicio con ID "+id);				
		}
		
	}

	@Override
	public ServiceTransfer getService(int id)
			throws ServicesIntegrationException {
		
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
		  }			
		
		return null;
		
	}

	@Override
	public Vector<ServiceTransfer> listService()
			throws ServicesIntegrationException {
		
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
		  }	
	}

	@Override
	public void updateService(ServiceTransfer t)
			throws ServicesIntegrationException {
		
		int id = ((ServiceTransfer) t).getId();
		String name = ((ServiceTransfer) t).getServices();
		

		//UPDATE
		String QueryString = "UPDATE Services SET services='"+name+"' WHERE idServices='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al actualizar servicio "+name);				
		  }
		  
	}

	@Override
	public boolean searchShift(String serviceName)
			throws ServicesIntegrationException {
		
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
		  }			

		return false;
	}

	@Override
	public boolean searchShiftByID(int id) throws ServicesIntegrationException {
		
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
		  }			

		return false;
	}

}
