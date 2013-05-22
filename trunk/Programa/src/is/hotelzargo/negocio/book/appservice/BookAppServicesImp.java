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
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;

public class BookAppServicesImp implements BookAppServices {

	@Override
	public void addBook(Vector<String> v) throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		BookTransfer t = obtainParameters(v);
		
		//se crea tranfer con los datos ya comprobados
		//añadir false al final
		
		
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
	
	//aqui miro todos los datos del vector comprobando que sean datos validos
	//es decir, fechas, numeros...
	private BookTransfer obtainParameters(Vector<String> v){
		Vector sol = new Vector();
		//TODO supongo que la gui me pasa un vector de habitaciones, que
		//se sacaran con el checkbox multiple que se ha pensado...
		Vector<Integer> rooms = getRoomsVector(v.get(0));
		int client = Integer.parseInt(v.get(1));
		
		Date checkIn = stringToDate(v.get(2));
		Date checkOut = stringToDate(v.get(3));
		
		float deposit = Float.parseFloat(v.get(4));
		int numPerson = Integer.parseInt(v.get(5));
		
		Vector<ServiceTransfer> services = getServices(v.get(6));
		
		BookTransfer b = new BookTransfer(-1, rooms, client, checkIn, checkOut, deposit, numPerson, services, false);
		
		
		return b;
		
	}
	
	private Vector<Integer> getRoomsVector(String rooms){
		Vector<Integer> sol = new Vector<Integer>();
		sol.add(2);
		sol.add(3);
		//TODO obtener habitaciones
		return sol;
	}
	
	private Date stringToDate(String s){
		
		Date date = null;
		try {
			java.util.Date dateUtil = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
			date = new java.sql.Date(dateUtil.getTime());
			//date = (Date) new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
		} catch (ParseException e1) {
			e1.printStackTrace();
			//throw new BookAppServicesException("Problema al parsear formato fecha en findBook");
		}
		return date;
		
	}
	
	private Vector<ServiceTransfer> getServices(String s){
		Vector<ServiceTransfer> sol = new Vector<ServiceTransfer>();
		//TODO no se como me llegan aqui los servicios, intuyo que seran
		//ids de servicios y hay que buscarlos en la DB?
		ServiceTransfer st = new ServiceTransfer(1, "cosa");
		sol.add(st);
		return sol;
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
			if (dao.searchBook(id)){
				dao.confirmBook(id);
			}
			else{
				throw new BookAppServicesException("La reserva a confirmar no existe");
			}
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException("Fallo al confirmar reserva");
		}
		
	}

	@Override
	public Vector<Integer> findBook(String checkIn, String checkOut)
			throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();

		Date dateIn = stringToDate(checkIn);
		Date dateOut = stringToDate(checkOut);
		
		try {
			Vector<Integer> v = dao.findBook(dateIn,dateOut);
			if (v.isEmpty()){
				System.out.println("el vector disponibilidad es vacio primo");
			}
			else{
				System.out.println(v.get(0).toString());
			}
			return v;
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException("Problema al buscar habitaciones para reservar");
		}
	}

}
