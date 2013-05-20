package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.integracion.exception.ServicesIntegrationException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

import java.sql.Connection;
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
		
		String service = ((BookTransfer) t).get;
		
		try {
			
			statement.executeUpdate("INSERT INTO Books (services) VALUES " +
					"("+service+");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ServicesIntegrationException("Problema al crear servicio");			
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
