package is.hotelzargo.negocio.shift.appservice;

import java.util.Vector;

import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public interface ShiftAppServices {

	void addShift(ShiftTransfer t)throws ShiftAppServicesException;

	void deleteShift(int id)throws ShiftAppServicesException;;

	Vector<ShiftTransfer> listShift()throws ShiftAppServicesException;;

	void modShift(ShiftTransfer t)throws ShiftAppServicesException;;

}
