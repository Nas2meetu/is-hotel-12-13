package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ShiftDAO;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public class ShiftAppServicesImp implements ShiftAppServices {

	private void checkData(ShiftTransfer t) throws ShiftAppServicesException {
	
	}
	
	@Override
	public void addShift(ShiftTransfer t) throws ShiftAppServicesException {
		// TODO crear turno
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		checkData(t);
		
		try {
			if(!dao.searchShift(t)){
				dao.createShift(t);
			}else{
				throw new ShiftAppServicesException("El usuario ya existe");
			}
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delShift(String id) throws ShiftAppServicesException {
		// TODO borrar turno
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			dao.deleteShift(id);
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void listShift() throws ShiftAppServicesException {
		// TODO listar turno
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			dao.listShift();
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modShift(ShiftTransfer t) throws ShiftAppServicesException {
		// TODO modificar turno
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			dao.updateShift(t);
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
