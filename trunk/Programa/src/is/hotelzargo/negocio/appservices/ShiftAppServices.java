package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public interface ShiftAppServices {

	void addShift(ShiftTransfer t)throws ShiftAppServicesException;

	void delShift(String id)throws ShiftAppServicesException;;

	void listShift()throws ShiftAppServicesException;;

	void modShift(ShiftTransfer t)throws ShiftAppServicesException;;

}
