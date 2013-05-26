package is.hotelzargo.integracion.room.dao;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RoomDAOImp implements RoomDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public RoomDAOImp(){}

	@Override
	public void createRoom(RoomTransfer t) throws RoomIntegrationException {
		
		initDataBase();
		
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();
		
		try {
			
			statement.executeUpdate("INSERT INTO Rooms (room_number, price, bed_number) VALUES " +
					"('"+room_number+"', '"+price+"', '"+bed_number+"');" );					
			
		} catch (SQLException e) {
			throw new RoomIntegrationException("Problema al crear habitación");			
		}finally{
			closeConnectionDataBase();
		}
		
	}

	@Override
	public void deleteRoom(int id) throws RoomIntegrationException {
		
		initDataBase();
		// Se busca habitacion y se elimina
		String QueryString = "DELETE FROM Rooms WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al eliminar  habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
						
	}
	//se trabaja con el numero de habitacion, que suponemos unico
	@Override
	public RoomTransfer getRoom(int id) throws RoomIntegrationException {
		
		initDataBase();
		// Se devuelve habitacion
		String QueryString = "SELECT * FROM Rooms WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {
				  
				  	int room_number = rs.getInt(2);
				  	float price = rs.getFloat(3);
				  	int bed_number = rs.getInt(4);				  	
					
				  	RoomTransfer r = new RoomTransfer(id,bed_number, room_number, price);					
					return r;				  
			  }
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al buscar habitacion ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT * FROM Rooms;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro habitaciones, metiendolas en el vector
			Vector<RoomTransfer> rooms = new Vector<RoomTransfer>();
			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(1);
				  	int room_number = rs.getInt(2);
					float price = rs.getFloat(3);
					int bed_number = rs.getInt(4);
					
					RoomTransfer r = new RoomTransfer(id,bed_number, room_number, price);						
					rooms.add(r);
				  
			  }
			  
			  return rooms;
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al referenciar listado habitaciones");				
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException {
		
		initDataBase();
		int id = ((RoomTransfer) t).getId();
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();		

		//UPDATE
		String QueryString = "UPDATE Rooms SET room_number='"+room_number+"'," +
				"price='"+price+"',bed_number='"+bed_number+"' WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {			
			throw new RoomIntegrationException("Problema al actualizar habitacion");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}
	
	@Override
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
		String QueryString = "SELECT * FROM Rooms WHERE room_number='"+numRoom+"' AND bed_number='"+numBeds+"' AND price='"+price+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
	}
	
	@Override
	public boolean searchRoomByID(int id) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
				String QueryString = "SELECT * FROM Rooms WHERE id='"+id+"';";
				  try {
					rs = statement.executeQuery(QueryString);			
					//solo me devolvera 1 fila
					  if (rs.next()) {				  					
							return true;				  
					  }
					
				  } catch (SQLException e) {
					throw new RoomIntegrationException("Problema al buscar habitación ");				
				  }finally{
					  closeConnectionDataBase();
				  }
				
				return false;
	}
	
	@Override
	public boolean searchRoomByRoomID(int id) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
		String QueryString = "SELECT * FROM Rooms WHERE room_number='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
	}
	
	//comprueba que el numero de habitacion modificado no aparece en otra fila
	@Override
	public boolean checkNumRoom(int id, int numRoom)
			throws RoomIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT id FROM Rooms WHERE room_number='"+numRoom+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//si hay alguna fila, la habitacion ya existe, pero hayq ue ver que no exista en otra
			//zona de la tabla
			  if (rs.next()) {
				  	//hay que mirar si aparece en su propia fila, por lo que puede cambiarla sin problemas
				  	//en caso contrario no puede
				  	int idRoom = rs.getInt(1);
				  
				  	if (id == idRoom){
				  		return false;
				  	}else{
				  		return true;
				  	}
				  					  
			  }
			
		  } catch (SQLException e) {
			throw new RoomIntegrationException("Problema al checkear habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		  
		  return true;
		 
	}
	
	private void initDataBase() throws RoomIntegrationException {
		
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	throw new RoomIntegrationException("Conexion rechazada");
        }
        
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			throw new RoomIntegrationException("Conexion rechazada");
		}
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			throw new RoomIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			throw new RoomIntegrationException("Conexion rechazada");
		}
		
	}
	
	private void closeConnectionDataBase() throws RoomIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			//
			throw new RoomIntegrationException("Error al desconectar BBDD");
		}
	}
	
	//sirve para ver si hay reservas pendientes con la habitacion idRoom,controla que no
	//se elimine una habitacion con una reserva futura ya establecida
	@Override
	public boolean existsBooksWithRoom(int idRoom)
			throws RoomIntegrationException {
		
		initDataBase();
		Date date = null;

		String QueryString = "SELECT idBook FROM Rooms_books WHERE idRoom='"+idRoom+"';";
		String currentTime = "SELECT CURDATE();";
		  try {
			rs = statement.executeQuery(currentTime);
			//Statement statBook = connection.createStatement();

			  if (rs.next()) {
				  date = rs.getDate(1);
				  System.out.println(date.toString());
			  }
				  	
			  ResultSet rsTime = statement.executeQuery(QueryString);
			  while (rsTime.next()) {
				  //si llega aqui, quiere decir que hay reservas usando esa habitacion,
				  //por lo tanto no dejamos eliminarla
				  return true;
				  
				  	/*
					  //int idBook = rsTime.getInt(1);	
					  
					  //System.out.println("el id reserva es "+idBook);
					  	//ahora hay que comprobar que no haya reservas futuras con esa habitacion
					  	//comparo fecha del sistema con checkOut
					  	//String QueryInTime = "SELECT * FROM Books WHERE idBooks='"+idBook+"' AND checkOut>='"+date+"';";
					  String QueryInTime = "SELECT * FROM Books WHERE idBooks='"+idBook+"' AND checkOut>='"+date+"';";
					  	ResultSet rsFound = statBook.executeQuery(QueryInTime);
					  	if (rsFound.next()) {
					  		//si devuelve alguna fila entonces existe alguna reserva futura
					  		//que usara esa habitacion, por lo tanto no se puede eliminar
					  		System.out.println("CUMPLE FECHA");
					  		return true;
					  	}*/
				  					  
			  }
			
		  } catch (SQLException e) {			
			throw new RoomIntegrationException("Problema al checkear habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		  
		  return false;
		
	}

}
