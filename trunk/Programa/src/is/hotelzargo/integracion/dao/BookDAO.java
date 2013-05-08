package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.transfer.BookTransfer;

public interface BookDAO {

	public void createBook(BookTransfer t) throws BookIntegrationException;
	
	public void deleteBook(String id) throws BookIntegrationException;
	
	public void updateBook(BookTransfer t) throws BookIntegrationException;
	
	public BookTransfer getBook(String id) throws BookIntegrationException;
	
	// Hay que pensar que le entra a esto o si se hacen varias
	//public boolean searchBook() throws BookIntegrationException;
	
	public Vector<BookTransfer> listBook() throws BookIntegrationException;
	
	public void confirmBook(String id) throws BookIntegrationException;
}
