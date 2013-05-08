package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ServicesDAO;
import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

public class ServicesAppServicesImp implements ServicesAppServices {

	@Override
	public void addService(ServiceTransfer t)
			throws ServicesAppServicesException {
		// TODO crear servicio
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			dao.createService(t);
		} catch (ServicesIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delService(String id) throws ServicesAppServicesException {
		// TODO borrar servicio
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			dao.deleteService(id);
		} catch (ServicesIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void listService() throws ServicesAppServicesException {
		// TODO listar servicios
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			dao.listService();
		} catch (ServicesIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modService(ServiceTransfer t) throws ServicesAppServicesException {
		// TODO modificar servicio
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			dao.updateService(t);
		} catch (ServicesIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
