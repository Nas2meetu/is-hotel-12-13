package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.transfer.BookTransfer;

public interface BookAppServices {

	void addBook(BookTransfer t)throws BookAppServicesException;

	void delBook(String id)throws BookAppServicesException;

	void listBook()throws BookAppServicesException;

	void modBook(BookTransfer t)throws BookAppServicesException;

	void findBook(String id)throws BookAppServicesException;

	void confirmBook(String id)throws BookAppServicesException;

}
