package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

import java.sql.Connection;
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
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createService(ServiceTransfer t){
		
		int id = ((ServiceTransfer) t).getId();
		String service = ((ServiceTransfer) t).getServices();
		
		try{
			statement.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
			throw new ServicesIntegrationException("Problema al crear un servicio");
		}
	
			
		// TODO llamadas a BBDD
		
	}

	@Override
	public void deleteService(int id) throws ServicesIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public ServiceTransfer getService(String id)
			throws ServicesIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public Vector<ServiceTransfer> listService()
			throws ServicesIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public void updateService(ServiceTransfer t)
			throws ServicesIntegrationException {
		// TODO llamadas a BBDD
		
	}

}
