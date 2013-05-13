package is.hotelzargo.negocio.appservices;







import java.sql.Date;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ShiftDAO;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public class ShiftAppServicesImp implements ShiftAppServices {

	private void checkData(ShiftTransfer t) throws ShiftAppServicesException {
	String shift = t.getShift ();
	Date checkin = t.getCheckin ();
	Date checkout = t.getCheckout ();
	int id = t.getId ();
	
	if (shift.length () < 0) 
		throw new ShiftAppServicesException("Nombre de turno no valido");
	if (checkin.equals (checkout)) 
		throw new ShiftAppServicesException("Hora entrada es la misma que a la hora de salida");
	}
	
	@Override
	public void addShift(ShiftTransfer t) throws ShiftAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		checkData(t);
		
		try {
			if(!dao.searchShift(t)){
				dao.createShift(t);
			}else{
				throw new ShiftAppServicesException("El turno ya existe");
			}
		} catch (ShiftIntegrationException e) {
			throw new ShiftAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void deleteShift(int id) throws ShiftAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			//Comprobar que existe el turno a eliminar, y no tenga empledos asignados
			if (dao.searchShiftByID(id)){
				//TODO mirar que no haya empleados con el turno a eliminar
				if (!dao.employeesWithShift(id)){
					dao.deleteShift(id);
				}
				else{
					throw new ShiftAppServicesException("El turno a eliminar está asignado a algún empleado");
				}
			}
			else{
				throw new ShiftAppServicesException("El turno a eliminar no existe");
			}
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
			throw new ShiftAppServicesException(e.getMessage());
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
			throw new ShiftAppServicesException(e.getMessage());
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
