package is.hotelzargo.negocio.service.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.integracion.service.dao.ServicesDAO;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;

public class ServicesAppServicesImp implements ServicesAppServices {

	@Override
	public void addService(ServiceTransfer t)
			throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			if (!dao.searchShift(t.getServices())){
				dao.createService(t);
			}
			else{
				throw new ServicesAppServicesException("El servicio ya existe");	
			}
		} catch (ServicesIntegrationException e) {
			throw new ServicesAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void delService(int id) throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			if (dao.searchShiftByID(id)){
				dao.deleteService(id);
			}
			else{
				throw new ServicesAppServicesException("El servicio con ese ID no existe");
			}
		} catch (ServicesIntegrationException e) {
			throw new ServicesAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<ServiceTransfer> listService() throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			return dao.listService();
		} catch (ServicesIntegrationException e) {
			throw new ServicesAppServicesException(e.getMessage());
		}
		
		
	}

	@Override
	public void modService(ServiceTransfer t) throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			if (dao.searchShiftByID(t.getId())){
				dao.updateService(t);
			}
			else{
				throw new ServicesAppServicesException("El servicio a modificar no existe");
			}
		} catch (ServicesIntegrationException e) {
			throw new ServicesAppServicesException(e.getMessage());
		}
		
	}

}
