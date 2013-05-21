package is.hotelzargo.negocio.shift.appservice;


import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.integracion.shift.dao.ShiftDAO;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class ShiftAppServicesImp implements ShiftAppServices {

	private void checkData(ShiftTransfer t) throws ShiftAppServicesException {
	String shift = t.getShift ();
	Time checkin = t.getCheckin ();
	Time checkout = t.getCheckout ();
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
			if(!dao.searchShift(t.getShift(),t.getCheckin(),t.getCheckout())){
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
			if (dao.searchShift(id)){
				//Se mira que no haya empleados con el turno a eliminar
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
	public Vector<ShiftTransfer> listShift() throws ShiftAppServicesException {
		// listar turnos
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			return dao.listShift();
		} catch (ShiftIntegrationException e) {
			throw new ShiftAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void modShift(ShiftTransfer t) throws ShiftAppServicesException {
		// modificar turno
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			dao.updateShift(t);
		} catch (ShiftIntegrationException e) {
			throw new ShiftAppServicesException(e.getMessage());
		}
		
	}

}
