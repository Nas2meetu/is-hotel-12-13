package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

public interface ServicesDAO {

	public void createService(ServiceTransfer t) throws ServicesIntegrationException;
	
	public void deleteService(String id) throws ServicesIntegrationException;
	
	public ServiceTransfer getService(String id) throws ServicesIntegrationException;
	
	public Vector<ServiceTransfer> listService() throws ServicesIntegrationException;
	
	public void updateService(ServiceTransfer t) throws ServicesIntegrationException;
}
