package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public interface RoomDAO {

	public void createRoom(RoomTransfer t) throws RoomIntegrationException;
	
	public void deleteRoom(int id) throws RoomIntegrationException;
	
	public RoomTransfer getRoom(String id) throws RoomIntegrationException;
	
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException;
	
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException;
	
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException;
	
	public boolean searchRoomByID(int id) throws RoomIntegrationException;
}
