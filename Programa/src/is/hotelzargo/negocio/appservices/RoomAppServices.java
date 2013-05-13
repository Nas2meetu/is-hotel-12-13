package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

public interface RoomAppServices {

	void addRoom(RoomTransfer t)throws RoomAppServicesException;

	void delRoom(int id)throws RoomAppServicesException;

	void listRoom()throws RoomAppServicesException;

	void modRoom(RoomTransfer t)throws RoomAppServicesException;

}
