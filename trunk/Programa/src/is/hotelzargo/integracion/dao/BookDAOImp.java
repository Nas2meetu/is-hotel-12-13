package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

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
					"('"+idClient+"', '"+checkIn+"', '"+checkOut+"', '"+deposit+"', '"+numPerson+"');" );
			
			//ahora busco inserto las habitaciones de esa reserva
			Statement statBookRooms = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<idRoom.size();i++){			
				statBookRooms.executeUpdate("INSERT INTO Rooms_books (idbook,idRoom) VALUES " +
						"('"+idBook+"', '"+idRoom.get(i)+"');" );				
			}
			//ahora toca los servicios de la reserva
			Statement statBookServices = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<services.size();i++){			
				statBookServices.executeUpdate("INSERT INTO Services_books (idBook,idServices) VALUES " +
						"('"+idBook+"', '"+services.get(i).getId()+"');" );				
			}			
			
			
		} catch (SQLException e) {
			e.getMessage();
			throw new BookIntegrationException("Problema al crear servicio");			
		}		
	}

	@Override
	public void deleteBook(int id) throws BookIntegrationException {
		//Para eliminar una reserva es necesario eliminar toda referencia
		//en las dos tablas auxiliares
		
		String QueryString = "DELETE FROM Services_books WHERE idBook='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al Services_books turno con ID "+id);				
		  }
		  
		String QueryDel = "DELETE FROM Rooms_books WHERE idBook='"+id+"';";
			try {
				  
				statement.executeUpdate(QueryDel);			
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BookIntegrationException("Problema al Rooms_books turno con ID "+id);				
			}
			//TODO comprobar con Noel que el nombre del campo id de Books es idBook
			String QueryDelEnd = "DELETE FROM Books WHERE idBook='"+id+"';";
			try {
				  
				statement.executeUpdate(QueryDelEnd);			
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BookIntegrationException("Problema al Books turno con ID "+id);				
			}
		
	}

	@Override
	public void updateBook(BookTransfer t) throws BookIntegrationException {
		// TODO llamadas a BBDD
		
	}

	@Override
	public BookTransfer getBook(int id) throws BookIntegrationException {
		//TODO comprobar campo tambien
		String QueryString = "SELECT * FROM Books WHERE idBook='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  				  
					int idClient = rs.getInt(2);
					Date checkIn = rs.getDate(3);
					Date checkOut = rs.getDate(4);
					float deposit = rs.getFloat(5);
					int numPerson = rs.getInt(6);
					
					//BookTransfer b = new BookTransfer(id,idClient, checkIn, checkOut,deposit,numPerson);					
					//return b;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al referenciar reserva con ID "+id);				
		  }			
		
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
