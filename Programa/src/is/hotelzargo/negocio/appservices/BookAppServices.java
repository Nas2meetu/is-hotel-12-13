package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

public interface BookAppServices {

	void addBook(BookTransfer t)throws BookAppServicesException;

	void delBook(int id)throws BookAppServicesException;

	Vector<BookTransfer> listBook()throws BookAppServicesException;

	void modBook(BookTransfer t)throws BookAppServicesException;

	void findBook(int id)throws BookAppServicesException;

	void confirmBook(int id)throws BookAppServicesException;

}
