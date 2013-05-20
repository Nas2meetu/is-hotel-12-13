package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

public interface RoomAppServices {

	void addRoom(RoomTransfer t)throws RoomAppServicesException;

	void delRoom(int id)throws RoomAppServicesException;

	Vector<RoomTransfer> listRoom()throws RoomAppServicesException;

	void modRoom(RoomTransfer t)throws RoomAppServicesException;

}
