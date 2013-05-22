package is.hotelzargo.negocio.book.appservice;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.exception.BookAppServicesException;

import java.sql.Date;
import java.util.Vector;

public interface BookAppServices {

	void addBook(BookTransfer t)throws BookAppServicesException;

	void delBook(int id)throws BookAppServicesException;

	Vector<BookTransfer> listBook()throws BookAppServicesException;

	void modBook(BookTransfer t)throws BookAppServicesException;

	void searchBook(int id)throws BookAppServicesException;
	
	Vector<Integer> findBook(Date checkIn,Date checkOut)throws BookAppServicesException;

	void confirmBook(int id)throws BookAppServicesException;

}
