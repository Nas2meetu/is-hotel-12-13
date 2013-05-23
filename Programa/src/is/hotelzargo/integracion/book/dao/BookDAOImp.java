package is.hotelzargo.integracion.book.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BookDAOImp implements BookDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public BookDAOImp(){}

	@Override
	public void createBook(BookTransfer t) throws BookIntegrationException {
		
		initDataBase();
		
		//int idBook = t.getIdBook();
		Vector<Integer> idRoom = ((BookTransfer) t).getIdRoom();
		int idClient = ((BookTransfer) t).getIdClient();
		Date checkIn = ((BookTransfer) t).getCheckIn();
		Date checkOut = ((BookTransfer) t).getCheckOut();
		float deposit = ((BookTransfer) t).getDeposit();
		int numPerson = ((BookTransfer) t).getNumPerson();
		Vector<ServiceTransfer> services = ((BookTransfer) t).getServices();
		//boolean busy = t.isConfirm();
		//el campo en la base de datos es 0->false, 1->true
		int busy = 0;
		
		try {
			//
			statement.executeUpdate("INSERT INTO Books (idClient,checkIn,checkOut,deposit,numPerson,confirm) VALUES " +
					"('"+idClient+"', '"+checkIn+"', '"+checkOut+"', '"+deposit+"', '"+numPerson+"','"+busy+"');" );
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al crear la reserva");			
		}finally {
			closeConnectionDataBase();
		}
		
		//cojo el id de esta ultima reserva creada para guardar referencia en las
		//otras dos tablas de habitaciones y servicios
		int idBook = getLastIDBook();
		addRoomsToBook(idBook, idRoom);
		addServicesToBook(idBook, services);
		
	}
	
	private int getLastIDBook() throws BookIntegrationException{
		initDataBase();
		
		//elegimos el id maximo
		int last = -1;
		try {
			rs = statement.executeQuery("SELECT MAX(idBooks) FROM Books;");			
			//solo me devolvera 1 fila
			  while (rs.next()) {
					last = rs.getInt(1);
			  }			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema conseguir ultimo id de Books");
		}finally{
			closeConnectionDataBase();
		}
		
		return last;
	}

	@Override
	public void deleteBook(int id) throws BookIntegrationException {
		//Para eliminar una reserva es necesario eliminar toda referencia
		//en las dos tablas auxiliares
		
		  delAllServicesBook(id);
		  
		  delAllRoomsBook(id);
		
			initDataBase();	
			
			String QueryDelEnd = "DELETE FROM Books WHERE idBooks='"+id+"';";
			try {
				  
				statement.executeUpdate(QueryDelEnd);			
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BookIntegrationException("Problema al Books turno con ID "+id);				
			}finally{
				closeConnectionDataBase();
			}
		
	}

	@Override
	public void updateBook(BookTransfer t) throws BookIntegrationException {
		
		initDataBase();

		int idBook = ((BookTransfer) t).getIdBook();
		Vector<Integer> idRoom =((BookTransfer) t).getIdRoom();
		int idClient = ((BookTransfer) t).getIdClient();
		Date checkIn = ((BookTransfer) t).getCheckIn();
		Date checkOut = ((BookTransfer) t).getCheckOut();
		float deposit = ((BookTransfer) t).getDeposit();
		int numPerson = ((BookTransfer) t).getNumPerson();
		Vector<ServiceTransfer> services = ((BookTransfer) t).getServices();
		boolean busy = t.isConfirm();
		//UPDATE
		String QueryString = "UPDATE Books SET idClient='"+idClient+"',checkIn='"+checkIn+"'," +
				"checkOut='"+checkOut+"',deposit='"+deposit+"',numPerson='"+numPerson+"',confirm='"+busy+"' WHERE idBooks='"+idBook+"';";
		
		try{
			
			statement.executeUpdate(QueryString);
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new BookIntegrationException("Problema al actualizar reserva en Books "+idBook);
		}finally{
			closeConnectionDataBase();
		}
				
		//para actualizar habitaciones, se eliminan anteriores referencias, y se añaden las nuevas		
		delAllRoomsBook(idBook);		
		addRoomsToBook(idBook, idRoom);
		
		//con los servicios, la misma idea
		delAllServicesBook(idBook);
		addServicesToBook(idBook, services);		
		
	}
	
	//se añade el vector de habitaciones a la reserva
	private void addRoomsToBook(int idBook,Vector<Integer> idRoom) throws BookIntegrationException{
		initDataBase();
	
		try{
			Statement statBookRooms = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<idRoom.size();i++){			
				statBookRooms.executeUpdate("INSERT INTO Rooms_books (idbook,idRoom) VALUES " +
						"('"+idBook+"', '"+idRoom.get(i)+"');" );				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new BookIntegrationException("Problema al actualizar reserva en Rooms_books "+idBook);
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	//se añade el vector de habitaciones a la reserva
	private void addServicesToBook(int idBook,Vector<ServiceTransfer> services) throws BookIntegrationException{
		initDataBase();
	
		try{
			
			Statement statBookServices = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<services.size();i++){			
				statBookServices.executeUpdate("INSERT INTO Services_books (idBook,idService) VALUES " +
						"('"+idBook+"', '"+services.get(i).getId()+"');" );				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new BookIntegrationException("Problema al actualizar reserva en Services_books "+idBook);
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	//se eliminan todas las habitaciones de la reserva
	private void delAllRoomsBook(int idBook) throws BookIntegrationException{
		initDataBase();
		  
		String QueryDel = "DELETE FROM Rooms_books WHERE idBook='"+idBook+"';";
			try {
				  
				statement.executeUpdate(QueryDel);			
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BookIntegrationException("Problema al Rooms_books turno con ID "+idBook);				
			}finally{
				closeConnectionDataBase();
			}
	}
	
	//se eliminan todos los servicios de la reserva
	private void delAllServicesBook(int idBook)throws BookIntegrationException{
		initDataBase();
		
		String QueryString = "DELETE FROM Services_books WHERE idBook='"+idBook+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al Services_books turno con ID "+idBook);				
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public BookTransfer getBook(int id) throws BookIntegrationException {
		
		initDataBase();
		
		//obtenemos las habitaciones de la reserva
		Vector<Integer> rooms = getRoomsOfBook(id);
		
		//obtenemos servicios de la reserva
		Vector<ServiceTransfer> services = getServicesOfBook(id);
		
		String QueryString = "SELECT * FROM Books WHERE idBooks='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  				  
					int idClient = rs.getInt(2);
					Date checkIn = rs.getDate(3);
					Date checkOut = rs.getDate(4);
					float deposit = rs.getFloat(5);
					int numPerson = rs.getInt(6);
					boolean busy = rs.getBoolean(7);
					
					BookTransfer b = new BookTransfer(id,rooms,idClient, checkIn, checkOut,deposit,numPerson,services,busy);					
					return b;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al referenciar reserva con ID "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
	}
	
	//devuelve las habitaciones de una reserva concreta
	private Vector<Integer> getRoomsOfBook(int idBook) throws BookIntegrationException{
		
		initDataBase();
		Vector<Integer> rooms = new Vector<Integer>();
		String QueryString = "SELECT * FROM Rooms_books WHERE idBook='"+idBook+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {				  				  
					int idRoom = rs.getInt(3);
					rooms.add(idRoom);				  
			  }
			  
			  return rooms;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al devolver habitaciones de la reserva con ID "+idBook);				
		  }finally{
			  closeConnectionDataBase();
		  }
	}
	
	//devuelve las habitaciones de una reserva concreta
	private Vector<ServiceTransfer> getServicesOfBook(int idBook) throws BookIntegrationException{			
		initDataBase();
		Vector<ServiceTransfer> services = new Vector<ServiceTransfer>();
		String QueryString = "SELECT * FROM Services_books WHERE idBook='"+idBook+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {				  				  
					int idService = rs.getInt(3);
					ServiceTransfer s = getServicesByID(idService);
					services.add(s);				  
			  }
			  
			  return services;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al devolver habitaciones de la reserva con ID "+idBook);				
		  }finally{
			  closeConnectionDataBase();
		  }
	}
	
	//devuelve los servicios de cierta reserva
	private ServiceTransfer getServicesByID(int id) throws BookIntegrationException{
		initDataBase();
		
		String QueryString = "SELECT * FROM Services WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  
					String name = rs.getString(1);					
					ServiceTransfer s = new ServiceTransfer(id,name);					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al devolver servicios de la reserva con ID "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookIntegrationException {		
		initDataBase();
		
		Vector<BookTransfer> books = new Vector<BookTransfer>();
		String QueryString = "SELECT * FROM Books;";
		try{			
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  
					int id = rs.getInt(1);
					BookTransfer book = getBook(id);
					books.add(book);
				  
			  }	
			  
			  return books;
			  
		}catch(SQLException e){
			e.printStackTrace();
			throw new BookIntegrationException("Problema al listar reservas");
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	@Override
	public boolean searchBook(int id) throws BookIntegrationException {
		initDataBase();
		String QueryString = "SELECT * FROM Books WHERE idBooks='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new BookIntegrationException("Problema al buscar reserva "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}

	//pone a true la confirmacion
	@Override
	public void confirmBook(int id) throws BookIntegrationException {		
		initDataBase();
		//boolean b = true;
		//1->true en la BD,porque boolean no tira bien
		int b = 1;
		String QueryString = "UPDATE Books SET confirm='"+b+"' WHERE idBooks='"+id+"';";
		
		try{
			
			statement.executeUpdate(QueryString);
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new BookIntegrationException("Problema al confirmar reserva en Books "+id);
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	private void initDataBase() throws BookIntegrationException {
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	//JOptionPane.showMessageDialog(null, "Connection refused!");
        	throw new BookIntegrationException("Conexion rechazada");
        } 
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new BookIntegrationException("Conexion rechazada");
		}
        
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new BookIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			throw new BookIntegrationException("Conexion rechazada");
		}
		
	}
	
	private void closeConnectionDataBase() throws BookIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new BookIntegrationException("Error al desconectar BBDD");
		}
	}
	
	//TODO devuelve true si todas las habitaciones del vector se encuentran desocupadas
	//false en caso contrario
	public boolean emptyRooms(Vector<Integer> rooms) throws BookIntegrationException{
		
		return true;
		
	}


	@Override
	public Vector<Integer> findBook(Date checkIn, Date checkOut)
			throws BookIntegrationException {
		// TODO revision profunda
		initDataBase();
		Vector<Integer> rooms = new Vector<Integer>();
		//consigo los IDs de reserva que estan en ese intervalo de tiempo 
		String QueryString = "SELECT idBooks FROM Books WHERE (checkIn>='"+checkIn+"' AND checkIn<='"+checkOut+"') OR (checkOut>='"+checkIn+"' AND checkOut<='"+checkOut+"');";		  
		  //ahora busco en la tabla las habitaciones con esas reservas, que son las que van a estar
		  //ocupadas en ese periodo
		
		  try {
			  //en rs tengo los IDs de reserva
			rs = statement.executeQuery(QueryString);
			Statement statRoom = connection.createStatement() ;
			  while (rs.next()) {				  				  
					int idBook = rs.getInt(1);
						String QueryRooms = "SELECT room_number FROM Rooms WHERE id NOT IN (SELECT idRoom FROM Rooms_books WHERE idBook='"+idBook+"');";
						ResultSet rsRooms = statRoom.executeQuery(QueryRooms);
						while(rsRooms.next()){
							int room = rsRooms.getInt(1);
							rooms.add(room);
						}
					
			  }
			  
			  return rooms;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new BookIntegrationException("Problema al buscar habitaciones libres en cierta fecha");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		
	}

}
