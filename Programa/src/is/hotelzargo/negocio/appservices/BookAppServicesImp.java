package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.BookDAO;
import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.transfer.BookTransfer;

public class BookAppServicesImp implements BookAppServices {

	@Override
	public void addBook(BookTransfer t) throws BookAppServicesException {
		// TODO crear reserva
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.createBook(t);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delBook(String id) throws BookAppServicesException {
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
	public void listBook() throws BookAppServicesException {
		// TODO listar reservas
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.listBook();
		} catch (BookIntegrationException e) {
			e.printStackTrace();
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
	public void findBook(String id) throws BookAppServicesException {
		// TODO buscar reservas
		
	}

	@Override
	public void confirmBook(String id) throws BookAppServicesException {
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
