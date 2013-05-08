package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

public interface RoomDAO {

	public void createRoom(RoomTransfer t) throws RoomIntegrationException;
	
	public void deleteRoom(String id) throws RoomIntegrationException;
	
	public RoomTransfer getRoom(String id) throws RoomIntegrationException;
	
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException;
	
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException;
}
