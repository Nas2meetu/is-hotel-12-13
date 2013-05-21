package is.hotelzargo.negocio.book.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.book.dao.BookDAO;
import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.exception.ClientAppServicesException;

public class BookAppServicesImp implements BookAppServices {

	@Override
	public void addBook(BookTransfer t) throws BookAppServicesException {
		// TODO crear reserva
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.createBook(t);
		} catch (BookIntegrationException e) {
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void delBook(int id) throws BookAppServicesException {
		// TODO borrar reserva
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.deleteBook(id);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookAppServicesException {
		// TODO listar reservas
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			return dao.listBook();
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException("Problema al listar reservas");
		}
	}

	@Override
	public void modBook(BookTransfer t) throws BookAppServicesException {
		// TODO modificar reservas
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.updateBook(t);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void findBook(int id) throws BookAppServicesException {
		// TODO buscar reservas
		
	}

	@Override
	public void confirmBook(int id) throws BookAppServicesException {
		// TODO confirmar reserva
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.confirmBook(id);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
