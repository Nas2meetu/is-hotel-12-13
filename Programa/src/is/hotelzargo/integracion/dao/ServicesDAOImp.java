package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

import java.util.Vector;

public class ServicesDAOImp implements ServicesDAO {

	@Override
	public void createService(ServiceTransfer t)
			throws ServicesIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public void deleteService(String id) throws ServicesIntegrationException {
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
