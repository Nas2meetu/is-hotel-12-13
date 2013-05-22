package is.hotelzargo.negocio.book.appservice;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
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
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			//TODO si las habitaciones elegidas para la reserva están libres,
			//entonces se podrá reservar
			if (dao.emptyRooms(t.getIdRoom())){
				dao.createBook(t);
			}
			else{
				throw new BookAppServicesException("No es posible crear la reserva, tiene habitaciones ocupadas");
			}
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void delBook(int id) throws BookAppServicesException {
		// Borrar reserva
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			if (dao.searchBook(id)){
				dao.deleteBook(id);
			}
			else{
				throw new BookAppServicesException("La reserva a eliminar no existe");
			}
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookAppServicesException {
		// listar reservas
		
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
		// modificar reservas
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			//TODO conflicto de fechas se comprueba antes de llegar aqui?
			//creo que si
			if (dao.searchBook(t.getIdBook())){
				dao.updateBook(t);
			}
			else{
				throw new BookAppServicesException("La reserva a modificar no existe");
			}
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException("Problema al modificar reserva");
		}
		
	}

	@Override
	public void confirmBook(int id) throws BookAppServicesException {
		// Confirmar reserva		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			dao.confirmBook(id);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Vector<Integer> findBook(String checkIn, String checkOut)
			throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		//TODO pasar las fechas de string a date puebalo gorka
		Date dateIn;
		Date dateOut;
		try {
			dateIn = (Date) new SimpleDateFormat("dd MM yyyy", Locale.FRANCE).parse(checkIn);
			dateOut = (Date) new SimpleDateFormat("dd MM yyyy", Locale.FRANCE).parse(checkOut);
		} catch (ParseException e1) {
			e1.printStackTrace();
			throw new BookAppServicesException("Problema al parsear formato fecha en findBook");
		}
		
		try {
			return dao.findBook(dateIn,dateOut);
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException("Problema al buscar habitaciones para reservar");
		}
	}

}
