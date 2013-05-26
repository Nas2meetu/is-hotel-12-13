package is.hotelzargo.negocio.shift.appservice;


import java.sql.Time;
import java.util.Vector;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.integracion.shift.dao.ShiftDAO;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class ShiftAppServicesImp implements ShiftAppServices {
	
	@Override
	public void addShift(ShiftTransfer t) throws ShiftAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();

		String checkIn = t.getCheckin()+":00";
		String checkOut = t.getCheckout()+":00";
		t.setCheckin(checkIn);
		t.setCheckout(checkOut);
		Time checkin = checkTime(checkIn);
		Time checkout = checkTime(checkOut);
		
		if(checkin.getTime() > checkout.getTime()) 
			throw new ShiftAppServicesException("La hora de entrada tiene que ser menor que la de salida");
		
		if(checkin.getHours() > checkout.getHours()-4)
			throw new ShiftAppServicesException("Tiene que haber 4 horas de diferencia entre horas");
		
		try {
			if(!dao.searchShift(t.getShift(),checkin,checkout)){
				dao.createShift(t);
			}else{
				throw new ShiftAppServicesException("El turno ya existe");
			}
		} catch (ShiftIntegrationException e) {
			e.printStackTrace();
			throw new ShiftAppServicesException(e.getMessage());
		}
	}

	@Override
	public void deleteShift(int id) throws ShiftAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			if (dao.searchShift(id)){
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
			throw new ShiftAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<ShiftTransfer> listShift() throws ShiftAppServicesException {
		
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
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			if(dao.searchShift(t.getId())){
				dao.updateShift(t);
			}
			else{
				throw new ShiftAppServicesException("El turno a modificar no existe");
			}
		} catch (ShiftIntegrationException e) {
			throw new ShiftAppServicesException(e.getMessage());
		}
		
	}
	
	/*private boolean checkData(ShiftTransfer t) throws ShiftAppServicesException {
		String shift = t.getShift();
		
		if (shift.length () <= 0) 
			throw new ShiftAppServicesException("Nombre de turno no valido");
		
		if (timeIn.equals (timeOut)) 
			throw new ShiftAppServicesException("Hora entrada es la misma que a la hora de salida");
		
		return true;
	}*/
	
	private Time checkTime(String t) throws ShiftAppServicesException{
		Time time;
		
		try{
			time = Time.valueOf(t);
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ShiftAppServicesException("Formato de hora turno invalido");
		}
		
		return time;
	}

}
