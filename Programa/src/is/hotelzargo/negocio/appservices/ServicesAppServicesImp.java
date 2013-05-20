package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ServicesDAO;
import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

public class ServicesAppServicesImp implements ServicesAppServices {

	@Override
	public void addService(ServiceTransfer t)
			throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		int id = t.getId();
		
		try {
			if (!dao.searchShiftByID(id)){
				dao.createService(t);
			}
			else{
				throw new ServicesAppServicesException("El servicio ya existe");	
			}
		} catch (ServicesIntegrationException e) {
			e.getMessage();
			throw new ServicesAppServicesException("Problema al crear servicio");
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
			e.getMessage();
			throw new ServicesAppServicesException("Problema al eliminar servicio");
		}
		
	}

	@Override
	public Vector<ServiceTransfer> listService() throws ServicesAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ServicesDAO dao = fac.getServicesDAO();
		
		try {
			return dao.listService();
		} catch (ServicesIntegrationException e) {
			e.getMessage();
			throw new ServicesAppServicesException("Problema al listar servicios");
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
			e.getMessage();
			throw new ServicesAppServicesException("Problema al modificar servicio");
		}
		
	}

}
