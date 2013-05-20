package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BookDAOImp implements BookDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public BookDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createBook(BookTransfer t) throws BookIntegrationException {
		
		int idBook = t.getIdBook();
		Vector<Integer> idRoom = ((BookTransfer) t).getIdRoom();
		int idClient = ((BookTransfer) t).getIdClient();
		Date checkIn = ((BookTransfer) t).getCheckIn();
		Date checkOut = ((BookTransfer) t).getCheckOut();
		float deposit = ((BookTransfer) t).getDeposit();
		int numPerson = ((BookTransfer) t).getNumPerson();
		Vector<ServiceTransfer> services = ((BookTransfer) t).getServices();
		
		try {
			//
			statement.executeUpdate("INSERT INTO Books (idClient,checkIn,checkOut,deposit,numPerson) VALUES " +
					"("+idClient+", "+checkIn+", "+checkOut+", "+deposit+", "+numPerson+");" );
			
			//ahora busco inserto las habitaciones de esa reserva
			Statement statBookRooms = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<idRoom.size();i++){			
				statBookRooms.executeUpdate("INSERT INTO Rooms_books (idbook,idRoom) VALUES " +
						"("+idBook+", "+idRoom.get(i)+");" );				
			}
			//ahora toca los servicios de la reserva
			Statement statBookServices = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<services.size();i++){			
				statBookServices.executeUpdate("INSERT INTO Services_books (idBook,idServices) VALUES " +
						"("+idBook+", "+services.get(i).getId()+");" );				
			}			
			
			
		} catch (SQLException e) {
			e.getMessage();
			throw new BookIntegrationException("Problema al crear servicio");			
		}		
	}

	@Override
	public void deleteBook(int id) throws BookIntegrationException {
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
	public void confirmBook(int id) throws BookIntegrationException {
		// TODO llamadas a BBDD
		
	}

}
