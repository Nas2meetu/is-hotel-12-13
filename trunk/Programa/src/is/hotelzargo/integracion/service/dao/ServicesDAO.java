package is.hotelzargo.integracion.service.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;

public interface ServicesDAO {

	public void createService(ServiceTransfer t) throws ServicesIntegrationException;
	
	public void deleteService(int id) throws ServicesIntegrationException;
	
	public ServiceTransfer getService(int id) throws ServicesIntegrationException;
	
	public boolean searchShift(String serviceName) throws ServicesIntegrationException;
	
	public boolean searchShiftByID(int id) throws ServicesIntegrationException;
	
	public Vector<ServiceTransfer> listService() throws ServicesIntegrationException;
	
	public void updateService(ServiceTransfer t) throws ServicesIntegrationException;
}
