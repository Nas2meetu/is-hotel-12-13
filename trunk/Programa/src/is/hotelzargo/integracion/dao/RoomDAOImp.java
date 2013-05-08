package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

import java.util.Vector;

public class RoomDAOImp implements RoomDAO {

	@Override
	public void createRoom(RoomTransfer t) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public void deleteRoom(String id) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public RoomTransfer getRoom(String id) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		
	}

}
