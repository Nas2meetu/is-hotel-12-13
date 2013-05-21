package is.hotelzargo.integracion.room.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public interface RoomDAO {

	public void createRoom(RoomTransfer t) throws RoomIntegrationException;
	
	public void deleteRoom(int id) throws RoomIntegrationException;
	
	public RoomTransfer getRoom(int id) throws RoomIntegrationException;
	
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException;
	
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException;
	
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException;
	
	public boolean searchRoomByID(int id) throws RoomIntegrationException;
}
