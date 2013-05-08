package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.transfer.BookTransfer;

import java.util.Vector;

public class BookDAOImp implements BookDAO {

	@Override
	public void createBook(BookTransfer t) throws BookIntegrationException {
		// TODO llamadas a BBDD crear reserva
		
	}

	@Override
	public void deleteBook(String id) throws BookIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public void updateBook(BookTransfer t) throws BookIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public BookTransfer getBook(String id) throws BookIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookIntegrationException {
		// TODO llamadas a BBDD
		return null;
	}

	@Override
	public void confirmBook(String id) throws BookIntegrationException {
		// TODO llamadas a BBDD
		
	}

}
