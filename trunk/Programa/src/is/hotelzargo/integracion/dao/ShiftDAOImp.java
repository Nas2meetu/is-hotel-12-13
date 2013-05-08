package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

import java.util.Vector;

public class ShiftDAOImp implements ShiftDAO {

	@Override
	public void createShift(ShiftTransfer t) throws ShiftIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public void deleteShift(String id) throws ShiftIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public ShiftTransfer getShift(String id) throws ShiftIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public Vector<ShiftTransfer> listShift() throws ShiftIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public void updateShift(ShiftTransfer t) throws ShiftIntegrationException {
		// TODO llamadas a BBDD
		
	}

}
