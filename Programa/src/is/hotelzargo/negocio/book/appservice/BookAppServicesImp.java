package is.hotelzargo.negocio.book.appservice;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import is.hotelzargo.integracion.book.dao.BookDAO;
import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.exception.BookAppServicesException;

public class BookAppServicesImp implements BookAppServices {

	@Override
	public void addBook(BookTransfer t) throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			
			String in = t.getCheckIn();
			String out = t.getCheckOut();
			
			Date din = stringToDate(in);
			Date dout = stringToDate(out);
			if(din.after(dout)) throw new BookAppServicesException("La fecha de entrada no puede ser posterior a la de salida");
			//TODO Testear testear testear
			if (dao.existsServices(t.getServices())){
				if (dao.existsRooms(t.getIdRoom())){
					if (dao.emptyRooms(t.getIdRoom(),din,dout)){
						if (dao.existsClient(t.getIdClient())){
							dao.createBook(t);
						}
						else{
							throw new BookAppServicesException("No es posible crear la reserva, el cliente no existe");
						}
					}
					else{
						throw new BookAppServicesException("No es posible crear la reserva, tiene habitaciones ocupadas");
					}
				}
				else{
					throw new BookAppServicesException("No es posible crear la reserva, alguna habitacion no existe");
				}
			}
			else{
				throw new BookAppServicesException("No es posible crear la reserva, algun servicio no existe");
			}
		} catch (BookIntegrationException e) {
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void delBook(int id) throws BookAppServicesException {
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
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();
		
		try {
			return dao.listBook();
		} catch (BookIntegrationException e) {
			e.printStackTrace();
			throw new BookAppServicesException(e.getMessage());
		}
	}

	@Override
	public void modBook(BookTransfer t) throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();			
		
		try {
			
			String in = t.getCheckIn();
			String out = t.getCheckOut();
			
			Date din = stringToDate(in);
			Date dout = stringToDate(out);
			if(din.after(dout)) throw new BookAppServicesException("La fecha de entrada no puede ser posterior a la de salida");
			//TODO comprobar a saco gente
			if (dao.searchBook(t.getIdBook())){				
				if (dao.existsServices(t.getServices())){
					if (dao.existsRooms(t.getIdRoom())){
						if (dao.emptyRooms(t.getIdRoom(),din,dout)){
							if (dao.existsClient(t.getIdClient())){
								dao.updateBook(t);
							}
							else{
								throw new BookAppServicesException("No es posible modificar la reserva, el cliente no existe");
							}
						}
						else{
							throw new BookAppServicesException("No es posible modificar la reserva, tiene habitaciones ocupadas");
						}
					}
					else{
						throw new BookAppServicesException("No es posible modificar la reserva, alguna habitacion no existe");
					}
				}
				else{
					throw new BookAppServicesException("No es posible modificar la reserva, alguna servicio no existe");
				}				
			}
			else{
				throw new BookAppServicesException("La reserva a modificar no existe");
			}
			
			
		} catch (BookIntegrationException e) {
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void confirmBook(int id) throws BookAppServicesException {		
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
			throw new BookAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<Integer> findBook(String checkIn, String checkOut)
			throws BookAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		BookDAO dao = fac.getBookDAO();

		Date dateIn = stringToDate(checkIn);
		Date dateOut = stringToDate(checkOut);
		if(dateIn.after(dateOut)) throw new BookAppServicesException("La fecha de entrada no puede ser posterior a la de salida");
		
		try {
			Vector<Integer> v = dao.findBook(dateIn,dateOut);
			if (v.isEmpty()){
				throw new BookAppServicesException("No hay habitaciones disponibles en esas fechas");
			}
			else{
				return v;
			}
			
		} catch (BookIntegrationException e) {
			throw new BookAppServicesException(e.getMessage());
		}
	}
	
	private Date stringToDate(String s) throws BookAppServicesException{
		
		Date date = null;
		try {
			java.util.Date dateUtil = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
			date = new java.sql.Date(dateUtil.getTime());
			//date = (Date) new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
		} catch (ParseException e1) {
			throw new BookAppServicesException("El formato de la fecha no es correcto");
		}
		return date;
		
	}
	/*
	private Vector<ServiceTransfer> getServices(String s){
		Vector<ServiceTransfer> sol = new Vector<ServiceTransfer>();
		//no se como me llegan aqui los servicios, intuyo que seran
		//ids de servicios y hay que buscarlos en la DB?
		ServiceTransfer st = new ServiceTransfer(1, "cosa");
		sol.add(st);
		return sol;
	}
	
	private Vector<Integer> getRoomsVector(String rooms){
		Vector<Integer> sol = new Vector<Integer>();
		sol.add(2);
		sol.add(3);
		//obtener habitaciones
		return sol;
	}
	
	*/

}
