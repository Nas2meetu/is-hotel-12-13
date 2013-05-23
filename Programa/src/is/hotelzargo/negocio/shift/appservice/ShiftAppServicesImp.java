package is.hotelzargo.negocio.shift.appservice;


import java.sql.Time;
import java.util.Vector;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.integracion.shift.dao.ShiftDAO;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class ShiftAppServicesImp implements ShiftAppServices {

	private ShiftTransfer checkData(Vector<String> t) throws ShiftAppServicesException {
		String shift = t.get(0);
		
		if (shift.length () <= 0) 
			throw new ShiftAppServicesException("Nombre de turno no valido");
		
		Time timeIn = checkTime(t.get(1));
		Time timeOut = checkTime(t.get(2));
		
		if (timeIn.equals (timeOut)) 
			throw new ShiftAppServicesException("Hora entrada es la misma que a la hora de salida");
		//TODO alguna comprobacion mas del horario(in anterior a out por ejemplo)
		
		//Devolvemos tranfer formado		
		ShiftTransfer transfer = new ShiftTransfer(-1,shift,timeIn,timeOut);
		
		return transfer;
	}
	
	private Time checkTime(String t) throws ShiftAppServicesException{
		Time time;
		
		try{
			time = Time.valueOf(t);
		}
		catch (Exception e){
			throw new ShiftAppServicesException("Formato de hora turno invalido");
		}
		
		return time;
	}
	
	@Override
	public void addShift(ShiftTransfer t) throws ShiftAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ShiftDAO dao = fac.getShiftDAO();
		
		try {
			if(!dao.searchShift(t.getShift(),t.getCheckin(),t.getCheckout())){
				dao.createShift(t);
			}else{
				throw new ShiftAppServicesException("El turno ya existe");
			}
		} catch (ShiftIntegrationException e) {
			throw new ShiftAppServicesException(e.getMessage());
		}
		//TODO ventanas onformativas asi o JOptionPlane
		//si llega aqui, el turno se ha añadido con exito
		throw new ShiftAppServicesException("Turno añadido correctamente");
		
		//o esta que es mas sucia
		 //JOptionPane.showMessageDialog(null,"No, para nada ","Aviso",JOptionPane.PLAIN_MESSAGE);
		
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
