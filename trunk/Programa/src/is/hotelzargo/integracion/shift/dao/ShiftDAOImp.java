package is.hotelzargo.integracion.shift.dao;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ShiftDAOImp implements ShiftDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public ShiftDAOImp(){}

	@Override
	public void createShift(ShiftTransfer t) throws ShiftIntegrationException {
		
		initDataBase();
		
		String nameShift =((ShiftTransfer) t).getShift();
		String checkIn = ((ShiftTransfer) t).getCheckin();
		String checkOut = ((ShiftTransfer) t).getCheckout();
		
		Time in = stringToTime(checkIn);
		Time out = stringToTime(checkOut);		
		
		try {
			
			statement.executeUpdate("INSERT INTO Shifts (nameShift, checkIn, checkOut) VALUES " +
					"('"+nameShift+"','"+in+"', '"+out+ "');" );					
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al crear turno "+nameShift);			
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	private Time stringToTime(String s) throws ShiftIntegrationException{
		Time time;
		
		try{
			time = Time.valueOf(s);
		}
		catch (Exception e){
			throw new ShiftIntegrationException("Formato de hora turno invalido");
		}
		
		return time;
	}

	@Override
	public void deleteShift(int id) throws ShiftIntegrationException {
		
		initDataBase();
		//Se sabe que si llega aquí no hay empleados con este turno y se puede eliminar
		String QueryString = "DELETE FROM Shifts WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al eliminar turno con ID "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}

	@Override
	public ShiftTransfer getShift(int id) throws ShiftIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Shifts WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {
				  
					String nameShift = rs.getString(2);
					Time checkIn = rs.getTime(3);
					Time checkOut = rs.getTime(4);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, checkIn.toString(), checkOut.toString());					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al referenciar turno con ID "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
	}
	
	@Override
	public ShiftTransfer getShiftByName(String name) throws ShiftIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT * FROM Shifts WHERE nameShift='"+name+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {
				  
				  	int id = rs.getInt(1);
				  	Time checkIn = rs.getTime(3);
				  	Time checkOut = rs.getTime(4);
					
					ShiftTransfer s = new ShiftTransfer(id,name, checkIn.toString(), checkOut.toString());					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al referenciar turno "+name);				
		  }finally{
			  closeConnectionDataBase();
		  }
		//si no está ese turno se devuelve null
		return null;
	}

	@Override
	public Vector<ShiftTransfer> listShift() throws ShiftIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT * FROM Shifts;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro turnos, metiendolos en el vector
			Vector<ShiftTransfer> shifts = new Vector<ShiftTransfer>();
			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(1);
				  	String nameShift = rs.getString(2);
				  	Time checkIn = rs.getTime(3);
				  	Time checkOut = rs.getTime(4);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, checkIn.toString(), checkOut.toString());						
					shifts.add(s);				  
			  }
			  
			  return shifts;
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al referenciar listado turnos");				
		  }finally{
			  closeConnectionDataBase();
		  }
		  		
	}

	@Override
	public void updateShift(ShiftTransfer t) throws ShiftIntegrationException {
		
		initDataBase();
		
		int id = ((ShiftTransfer) t).getId();
		String nameShift = ((ShiftTransfer) t).getShift();
		String checkIn = ((ShiftTransfer) t).getCheckin();
		String checkOut = ((ShiftTransfer) t).getCheckout();
		
		Time in = stringToTime(checkIn);
		Time out = stringToTime(checkOut);		

		//UPDATE
		String QueryString = "UPDATE Shifts SET nameShift='"+nameShift+"'," +
				"checkIn='"+in+"',checkOut='"+out+"'  WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al actualizar turno "+nameShift);				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}

	@Override
	public boolean searchShift(int id) throws ShiftIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT * FROM Shifts WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al buscar turno "+id);				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}
	
	@Override
	public boolean searchShift(String name, Time checkIn, Time checkOut) throws ShiftIntegrationException {
		
		initDataBase();
		String QueryString = "SELECT * FROM Shifts WHERE nameShift='"+name+"' AND checkIn='"+checkIn+"' AND checkOut='"+checkOut+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al buscar turno ");				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}

	@Override
	public boolean employeesWithShift(int id) throws ShiftIntegrationException {
		
		initDataBase();
		//Buscamos en la tabla empleados que ninguno tenga este ID turno
		String QueryString = "SELECT * FROM Employees WHERE shiftID='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//si devuelve alguna fila, este turno tiene empleados asignados
			  if (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			throw new ShiftIntegrationException("Problema al buscar empleados con cierto turno ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		
		return false;
	}

	
	private void initDataBase() throws ShiftIntegrationException {
		
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	//JOptionPane.showMessageDialog(null, "Connection refused!");
        	throw new ShiftIntegrationException("Conexion rechazada");
        }
        
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new ShiftIntegrationException("Conexion rechazada");
		}
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new ShiftIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			throw new ShiftIntegrationException("Conexion rechazada");
		}
		
	}
	
	private void closeConnectionDataBase() throws ShiftIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new ShiftIntegrationException("Error al desconectar BBDD");
		}
	}	
}
