package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public interface ShiftDAO {

	public void createShift(ShiftTransfer t) throws ShiftIntegrationException;
	
	public void deleteShift(String id) throws ShiftIntegrationException;
	
	public ShiftTransfer getShift(String id) throws ShiftIntegrationException;
	
	public Vector<ShiftTransfer> listShift() throws ShiftIntegrationException;
	
	public void updateShift(ShiftTransfer t) throws ShiftIntegrationException;
}
