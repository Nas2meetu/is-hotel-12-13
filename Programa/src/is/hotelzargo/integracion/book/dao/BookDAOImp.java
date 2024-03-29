package is.hotelzargo.integracion.book.dao;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.book.transfer.BookTransfer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Date checkIn = stringToDate(t.getCheckIn());
		Date checkOut = stringToDate(t.getCheckOut());
		float deposit = ((BookTransfer) t).getDeposit();
		int numPerson = ((BookTransfer) t).getNumPerson();
		Vector<Integer> services = ((BookTransfer) t).getServices();
		//boolean busy = t.isConfirm();
		//el campo en la base de datos es 0->false, 1->true
		int busy = 0;
		
		try {
			//
			statement.executeUpdate("INSERT INTO Books (idClient,checkIn,checkOut,deposit,numPerson,confirm) VALUES " +
					"('"+idClient+"', '"+checkIn+"', '"+checkOut+"', '"+deposit+"', '"+numPerson+"','"+busy+"');" );
			
		} catch (SQLException e) {
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
		Date checkIn = stringToDate(t.getCheckIn());
		Date checkOut = stringToDate(t.getCheckOut());
		float deposit = ((BookTransfer) t).getDeposit();
		int numPerson = ((BookTransfer) t).getNumPerson();
		Vector<Integer> services = ((BookTransfer) t).getServices();
		boolean busy = t.isConfirm();
		int confirm;
		if (busy){
			confirm = 1;
		}
		else{
			confirm = 0;
		}
		//UPDATE
		String QueryString = "UPDATE Books SET idClient='"+idClient+"',checkIn='"+checkIn+"'," +
				"checkOut='"+checkOut+"',deposit='"+deposit+"',numPerson='"+numPerson+"',confirm='"+confirm+"' WHERE idBooks='"+idBook+"';";
		
		try{
			
			statement.executeUpdate(QueryString);
			
		}catch(SQLException e){
			
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
			
			throw new BookIntegrationException("Problema al actualizar reserva en Rooms_books "+idBook);
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	//se añade el vector de habitaciones a la reserva
	private void addServicesToBook(int idBook,Vector<Integer> services) throws BookIntegrationException{
		initDataBase();
	
		try{
			
			Statement statBookServices = connection.createStatement();
			//recorro todas las habitaciones, y las voy almacenando
			for (int i=0;i<services.size();i++){			
				statBookServices.executeUpdate("INSERT INTO Services_books (idBook,idService) VALUES " +
						"('"+idBook+"', '"+services.get(i)+"');" );				
			}
			
		}catch(SQLException e){
			
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
			
			throw new BookIntegrationException("Problema al Services_books turno con ID "+idBook);				
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public BookTransfer getBook(int id) throws BookIntegrationException {
		
		//De abrir y cerrar la BD se encarga list
		//initDataBase();
		
		//obtenemos las habitaciones de la reserva
		Vector<Integer> rooms = getRoomsOfBook(id);
		
		//obtenemos un vector de IDs de servicios de la reserva
		Vector<Integer> services = getServicesOfBook(id);
		
		String QueryString = "SELECT * FROM Books WHERE idBooks='"+id+"';";
		  try {
			  Statement statementTwo = connection.createStatement();
			  ResultSet rsB = statementTwo.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rsB.next()) {
				  				  
					int idClient = rsB.getInt(2);
					Date checkIn = rsB.getDate(3);
					Date checkOut = rsB.getDate(4);
					float deposit = rsB.getFloat(5);
					int numPerson = rsB.getInt(6);
					boolean busy = rsB.getBoolean(7);
					
					String in = dateToString(checkIn);
					String out = dateToString(checkOut);
					
					BookTransfer b = new BookTransfer(id,rooms,idClient, in, out,deposit,numPerson,services,busy);					
					return b;
				  
			  }
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al referenciar reserva con ID "+id);				
		  }/*finally{
			  closeConnectionDataBase();
		  }*/
		
		return null;
	}
	
	//convierte una fecha sacada de la BD a un String, para pasarlo en el Transfer
	private String dateToString(Date d){
		Format formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		String s = formatter.format(d);
		return s;
	}	
	
	//devuelve las habitaciones de una reserva concreta
	private Vector<Integer> getRoomsOfBook(int idBook) throws BookIntegrationException{
		//De abrir y cerrar la BD se encarga list
		//initDataBase();
		Vector<Integer> rooms = new Vector<Integer>();
		String QueryString = "SELECT * FROM Rooms_books WHERE idBook='"+idBook+"';";
		  try {
			  Statement statementTwo = connection.createStatement();
			  ResultSet rs3 = statementTwo.executeQuery(QueryString);			
			  while (rs3.next()) {				  				  
					int idRoom = rs3.getInt(3);
					rooms.add(idRoom);				  
			  }
			  
			  return rooms;
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al devolver habitaciones de la reserva con ID "+idBook);				
		  }/*finally{
			  closeConnectionDataBase();
		  }*/
	}
	
	//devuelve las habitaciones de una reserva concreta
	private Vector<Integer> getServicesOfBook(int idBook) throws BookIntegrationException{	
		//De abrir y cerrar la BD se encarga list

		Vector<Integer> services = new Vector<Integer>();
		String QueryString = "SELECT * FROM Services_books WHERE idBook='"+idBook+"';";
		  try {
			  Statement statementTwo = connection.createStatement();
			  ResultSet rs2 = statementTwo.executeQuery(QueryString);			
			  while (rs2.next()) {				  				  
					int idService = rs2.getInt(3);
					//Ya no hace falta porque ahora guardo los IDs
					//ServiceTransfer s = getServicesByID(idService);
					services.add(idService);				  
			  }
			  
			  return services;
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al devolver habitaciones de la reserva con ID "+idBook);				
		  }
	}
	
	//devuelve los servicios de cierta reserva
	/*private ServiceTransfer getServicesByID(int id) throws BookIntegrationException{
		
		String QueryString = "SELECT * FROM Services WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  
					String name = rs.getString(1);					
					ServiceTransfer s = new ServiceTransfer(id,name);					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al devolver servicios de la reserva con ID "+id);				
		  }
		
		return null;
	}*/

	@Override
	public Vector<BookTransfer> listBook() throws BookIntegrationException {		
		initDataBase();
		
		Vector<BookTransfer> books = new Vector<BookTransfer>();
		String QueryString = "SELECT idBooks FROM Books;";
		try{			
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {				  
					int id = rs.getInt(1);
					BookTransfer book = getBook(id);
					books.add(book);				  
			  }	
			  return books;
			  
		}catch(SQLException e){
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
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
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
        	throw new BookIntegrationException("Conexion rechazada");
        } 
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			throw new BookIntegrationException("Conexion rechazada");
		}
        
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
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
			throw new BookIntegrationException("Error al desconectar BBDD");
		}
	}
	
	//Devuelve true si todas las habitaciones del vector se encuentran desocupadas
	//entre esas fechas,false en caso contrario
	public boolean emptyRooms(Vector<Integer> rooms,Date in, Date out) throws BookIntegrationException{
		
		//se buscan reservas en ese intervalo de fechas(ocupadas)
		initDataBase();
		
		//consigo los IDs de reserva que estan en ese intervalo de tiempo 
		//String QueryString = "SELECT idBooks FROM Books WHERE (checkIn>='"+in+"' AND checkIn<='"+out+"') OR (checkOut>='"+in+"' AND checkOut<='"+out+"');";
		//String QueryString = "SELECT idRoom FROM Rooms_books;";
		//Obtengo las fechas de ocupacion de las reservas
		String QueryString = "SELECT idBooks,checkIn,checkOut FROM Books;";
		try {
			rs = statement.executeQuery(QueryString);
			//Statement statRoom = connection.createStatement() ;
			Statement statementTwo = connection.createStatement();
			//miro que las habitaciones del vector no pararezcan
			  while (rs.next()) {	
				  
				  int idB = rs.getInt(1);
				  Date inBook = rs.getDate(2);
				  Date outBook = rs.getDate(3);
				  
				  //cojo las habitaciones de esa reserva
				  String QueryRooms = "SELECT idRoom FROM Rooms_books WHERE idBook='"+idB+"';";
				  ResultSet rsRooms = statementTwo.executeQuery(QueryRooms);
				  while(rsRooms.next()){
					  int room = rsRooms.getInt(1);
					  //se comprueba que esa habitacion no este entre la fecha de entrada y salida
					  //ya ocupada
					  if (rooms.contains(room)){
						  if ((inBook.after(in) && inBook.before(out)))
							  return false;
						  
						  if (outBook.after(in) && outBook.before(out))
								  return false;
						  
					  }
					  
				  }
				  					
			  }
			  
			  return true;
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al buscar habitaciones libres en cierta fecha");				
		  }finally{
			  closeConnectionDataBase();
		  }
	
		
	}


	@Override
	public Vector<Integer> findBook(Date checkIn, Date checkOut)
			throws BookIntegrationException {
		
		Vector<Integer> rooms = new Vector<Integer>();
		//consigo todas las habitaciones, y quito del vector las que no cumplan fechas
		getAllRooms(rooms);
		initDataBase();
		
		//consigo los IDs de reserva que estan en ese intervalo de tiempo 
		//String QueryString = "SELECT idBooks,checkIn,checkOut FROM Books WHERE (checkIn>='"+checkIn+"' AND checkIn<='"+checkOut+"') OR (checkOut>='"+checkIn+"' AND checkOut<='"+checkOut+"');";		  
		  //ahora busco en la tabla las habitaciones con esas reservas, que son las que van a estar
		  //ocupadas en ese periodo
		 String QueryString = "SELECT idBooks,checkIn,checkOut FROM Books;";
		  try {
			  //en rs tengo los IDs de reserva
			rs = statement.executeQuery(QueryString);
			Statement statRoom = connection.createStatement() ;
			  while (rs.next()) {				  				  
					int idBook = rs.getInt(1);
					  Date inBook = rs.getDate(2);
					  Date outBook = rs.getDate(3);
					  
					  if ( (inBook.before(checkIn) && outBook.before(checkOut)) || (inBook.after(checkIn) && outBook.after(checkOut))  ){
					  
						  //Aqui cumple fechas, pero ya se añadieron arriba
						//String QueryRooms = "SELECT room_number FROM Rooms WHERE id NOT IN (SELECT idRoom FROM Rooms_books WHERE idBook='"+idBook+"');";
						/*String QueryRooms = "SELECT idRoom FROM Rooms_books WHERE idBook='"+idBook+"';";
						ResultSet rsRooms = statRoom.executeQuery(QueryRooms);
						while(rsRooms.next()){
							int room = rsRooms.getInt(1);
							rooms.add(room);
						}*/
						
					  }
					  else{
						  //se mira que no pise alguna habitacion 
						  //ya almacenada en una anterior reserva recorrida
							String QueryRooms = "SELECT idRoom FROM Rooms_books WHERE idBook='"+idBook+"';";
							ResultSet rsRooms = statRoom.executeQuery(QueryRooms);
							while(rsRooms.next()){
								int room = rsRooms.getInt(1);
								if (rooms.contains(room)){
									//aqui se da el problema de que remove quita
									//la posicion indicada por room, y no el object que 
									//rooms.remove(room);
									for (int i=0;i<rooms.size();i++){
										if (rooms.get(i) == room){
											rooms.remove(i);
										}
									}
								}
							}
 
					  }
					
			  }
			  return rooms;
			
		  } catch (SQLException e) {			
			throw new BookIntegrationException("Problema al buscar habitaciones libres en cierta fecha");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		
	}
	
	private void getAllRooms(Vector<Integer> rooms) throws BookIntegrationException{
		initDataBase();
		String QueryString = "SELECT id FROM Rooms;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {	
				  int id = rs.getInt(1);
					rooms.add(id);				  
			  }
			
		  } catch (SQLException e) {
			throw new BookIntegrationException("Problema al conseguir habitaciones");				
		  }finally{
			  closeConnectionDataBase();
		  }

	}
	
	private Date stringToDate(String s) throws BookIntegrationException{
		
		Date date = null;
		try {
			java.util.Date dateUtil = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
			date = new java.sql.Date(dateUtil.getTime());
			//date = (Date) new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(s);
		} catch (ParseException e1) {
			throw new BookIntegrationException("El formato de la fecha no es correcto");
		}
		return date;
		
	}

	@Override
	public boolean existsClient(int idClient) throws BookIntegrationException {
		
		if (searchClient(idClient)){
			return true;
		}
		else{
			return false;
		}
		
	}
	

	private boolean searchClient(int id) throws BookIntegrationException {
		
		//Se ha decidido usar ID unico para las dos tablas, así que este ID
		//puede estar en clientes individuales o en company, se busca en una tabla
		//y luego en otra
		if (searchIndividual(id)){
			return true;
		}
		else if (searchCompany(id)){
			return true;
		}
		else{
			return false;
		}
				
	}
	
	private boolean searchIndividual(int id) throws BookIntegrationException{
		
		initDataBase();
		
		String QueryString = "SELECT * FROM ClientIndividual WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  if (rs.next()) {
				  return true;
			  }
			  
			  return false;
			
		  } catch (SQLException e) {
			throw new BookIntegrationException("Problema al buscar en tabla ClientIndividual");				
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	private boolean searchCompany(int id) throws BookIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM ClientCompany WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  if (rs.next()) {
				  return true;
			  }
			  
			 return false;
			
		  } catch (SQLException e) {
			throw new BookIntegrationException("Problema al buscar en tabla ClientCompany");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}

	@Override
	public boolean existsRooms(Vector<Integer> idRoom)
			throws BookIntegrationException {
		
		int n = 0;
		int cont = 0;
		initDataBase();
		// Se buscan las habitaciones en la DB
		String QueryString = "SELECT id FROM Rooms;";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  	n = rs.getInt(1);
				  	//si cierta habitacion no existe, devuelve falso
				  	if (idRoom.contains(n)){
				  		cont++;
				  	}
			  }
			  
			  if (cont == idRoom.size()){
				  return true;
			  }
			
		  } catch (SQLException e) {
			
			throw new BookIntegrationException("Problema al buscar habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
		
	}

	@Override
	public boolean existsServices(Vector<Integer> services)
			throws BookIntegrationException {
		
		int n = 0;
		//variable local para contar que todos los servicios esten en la tabla
		int cont = 0;
		initDataBase();
		// Se buscan los servicios en la DB
		String QueryString = "SELECT idServices FROM Services;";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  	n = rs.getInt(1);
				  	//si cierto servicio no existe, devuelve falso
				  	if (services.contains(n)){
				  		cont++;
				  	}
			  }
			  //estan todos
			  if (cont == services.size()){
				  return true;
			  }
			
		  } catch (SQLException e) {			
			throw new BookIntegrationException("Problema al buscar servicio ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
		
	}

}
