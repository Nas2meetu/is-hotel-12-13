package is.hotelzargo.integracion.shift.dao;

import java.sql.Time;
import java.util.Vector;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public interface ShiftDAO {

	public void createShift(ShiftTransfer t) throws ShiftIntegrationException;
	
	public void deleteShift(int id) throws ShiftIntegrationException;
	
	public ShiftTransfer getShift(int id) throws ShiftIntegrationException;
	
	public ShiftTransfer getShiftByName(String name) throws ShiftIntegrationException;
	
	public Vector<ShiftTransfer> listShift() throws ShiftIntegrationException;
	
	public void updateShift(ShiftTransfer t) throws ShiftIntegrationException;

	public boolean searchShift(int id) throws ShiftIntegrationException;
	
	public boolean searchShift(String name, Time ckeckIn, Time checkOut) throws ShiftIntegrationException;

	public boolean employeesWithShift(int id) throws ShiftIntegrationException;
}
