package is.hotelzargo.integracion.employee.dao;

import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

public class EmployeeDAOImp implements EmployeeDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public EmployeeDAOImp(){}

	@Override
	public void createEmployeeAdmin(EmployeeTransfer t)
			throws EmployeeIntegrationException {
		
		initDataBase();
		
		// La unica diferencia es que password no es null
		int shiftID =((EmployeeTransferAdmin) t).getShift().getId();
		float pay = ((EmployeeTransferAdmin) t).getPay();
		String name = ((EmployeeTransferAdmin) t).getName();
		String surname = ((EmployeeTransfer) t).getSurname();
		String dni = ((EmployeeTransfer) t).getDNI();
		String tlf = ((EmployeeTransfer) t).getPhone();
		String pass = ((EmployeeTransferAdmin) t).getPassword();
		
		try {
			
			statement.executeUpdate("INSERT INTO Employee (shiftID, pay, nameEmployee,surnameEmployee,dniEmployee,tlfEmployee,password) VALUES " +
					"('"+shiftID+"', '"+pay+"','"+name+"','"+surname+"','"+dni+"','"+tlf+"', '"+pass+ "');" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al crear empleado ");			
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	@Override
	public void createEmployeeServices(EmployeeTransfer t)
			throws EmployeeIntegrationException {
		
		initDataBase();
		// Aqui password es null, ya que se ha decidido usar solo 1 tabla
		// La unica diferencia es que password no es null
				int shiftID =((EmployeeTransfer) t).getShift().getId();
				float pay = ((EmployeeTransfer) t).getPay();
				String name = ((EmployeeTransfer) t).getName();
				String surname = ((EmployeeTransfer) t).getSurname();
				String dni = ((EmployeeTransfer) t).getDNI();
				String tlf = ((EmployeeTransfer) t).getPhone();
				
				try {
					
					statement.executeUpdate("INSERT INTO Employee (shiftID, pay, nameEmployee,surnameEmployee,dniEmployee,tlfEmployee,password) VALUES " +
							"('"+shiftID+"', '"+pay+"','"+name+"','"+surname+"','"+dni+"','"+tlf+"',' "+null+ "');" );					
					
				} catch (SQLException e) {
					e.getMessage();
					throw new EmployeeIntegrationException("Problema al crear empleado ");			
				}finally{
					closeConnectionDataBase();
				}
		
		
	}

	@Override
	public void deleteEmployee(int id) throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryString = "DELETE FROM Employees WHERE idEmployee='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al eliminar empleado");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}

	@Override
	public EmployeeTransfer getEmployee(int id)
			throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Employees WHERE idEmployee='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
				  	int shiftID = rs.getInt(2);
				  	//conseguimos el turno con el ID
				  	ShiftTransfer s = getShiftOfEmployee(shiftID);					  	
				  	
					float pay = rs.getFloat(3);
					String name = rs.getString(4);
					String surname = rs.getString(5);
					String dni = rs.getString(6);
					String tlf = rs.getString(7);
					String pass = rs.getString(8);
					EmployeeTransfer e;
					if (pass == null){
						e = new EmployeeTransferServices(id,s,pay, name, surname,dni,tlf);
					}
					else{
						e = new EmployeeTransferAdmin(id,s,pay, name, surname,dni,tlf,pass);
					}
					return e;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al buscar empleado");				
		  }	finally{
			  closeConnectionDataBase();
		  }

		return null;
	}
	
	private ShiftTransfer getShiftOfEmployee(int shiftID) throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryShiftEmployee = "SELECT * FROM Shifts WHERE id='"+shiftID+"';";
	  	ShiftTransfer s = null;
		  try {
			  ResultSet rs2 = statement.executeQuery(QueryShiftEmployee);			
			//solo me devolvera 1 fila
			  while (rs2.next()) {
				  	String nameShift = rs2.getString(2);
					Time checkIn = rs2.getTime(3);
					Time checkOut = rs2.getTime(4);
					
					s = new ShiftTransfer(shiftID,nameShift, checkIn, checkOut);
			  }
		  } catch (SQLException e) {
				e.getMessage();
				throw new EmployeeIntegrationException("Problema al buscar turno de empleado");				
		  }	finally{
			  closeConnectionDataBase();
		  }
		  
		  return s;
	}

	@Override
	public Vector<EmployeeTransfer> listEmployee()
			throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Employees;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro empleados, metiendolos en el vector
			Vector<EmployeeTransfer> employees = new Vector<EmployeeTransfer>();
			
			  while (rs.next()) {
				  	int id = rs.getInt(1);
				  	int shiftID = rs.getInt(2);
				  	//conseguimos el turno con el ID
				  	ShiftTransfer s = getShiftOfEmployee(shiftID);					  	
				  	
					float pay = rs.getFloat(3);
					String name = rs.getString(4);
					String surname = rs.getString(5);
					String dni = rs.getString(6);
					String tlf = rs.getString(7);
					String pass = rs.getString(8);
					EmployeeTransfer e;
					if (pass == null){
						e = new EmployeeTransferServices(id,s,pay, name, surname,dni,tlf);
					}
					else{
						e = new EmployeeTransferAdmin(id,s,pay, name, surname,dni,tlf,pass);
					}
					employees.add(e);
				  
			  }
			  
			  return employees;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeIntegrationException("Problema al referenciar listado empleados");				
		  }	finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public void updateEmployeeServices(EmployeeTransfer t) throws EmployeeIntegrationException {
		
		initDataBase();
		
		int id = ((EmployeeTransfer) t).getId();
		int shiftID =((EmployeeTransfer) t).getShift().getId();
		float pay = ((EmployeeTransfer) t).getPay();
		String name = ((EmployeeTransfer) t).getName();
		String surname = ((EmployeeTransfer) t).getSurname();
		String dni = ((EmployeeTransfer) t).getDNI();
		String tlf = ((EmployeeTransfer) t).getPhone();
		

		//UPDATE
		String QueryString = "UPDATE Employees SET shiftID='"+shiftID+"'," +
				"pay='"+pay+"',nameEmployee='"+name+"',surnameEmployee='"+surname+"',dniEmployee='"+dni+"',tlfEmployee='"+tlf+"'  WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al actualizar empleado servicios ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}
	
	@Override
	public void updateEmployeeAdmin(EmployeeTransfer t) throws EmployeeIntegrationException {
		
		initDataBase();
		
		int id = ((EmployeeTransfer) t).getId();
		int shiftID =((EmployeeTransfer) t).getShift().getId();
		float pay = ((EmployeeTransfer) t).getPay();
		String name = ((EmployeeTransfer) t).getName();
		String surname = ((EmployeeTransfer) t).getSurname();
		String dni = ((EmployeeTransfer) t).getDNI();
		String tlf = ((EmployeeTransfer) t).getPhone();
		String pass = ((EmployeeTransferAdmin) t).getPassword();
		

		//UPDATE
		String QueryString = "UPDATE Employees SET shiftID='"+shiftID+"'," +
				"pay='"+pay+"',nameEmployee='"+name+"',surnameEmployee='"+surname+"',dniEmployee='"+dni+"',tlfEmployee='"+tlf+"',password='"+pass+"'  WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al actualizar empleado servicios ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}

	@Override
	public boolean searchEmployee(String dni)
			throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Employees WHERE dniEmployee='"+dni+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al buscar empleado");				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}
	
	@Override
	public boolean searchEmployeeByID(int id)
			throws EmployeeIntegrationException {
		
		initDataBase();
		
		String QueryString = "SELECT * FROM Employees WHERE idEmployee='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al buscar empleado");				
		  }finally{
			  closeConnectionDataBase();
		  }

		return false;
	}
	
	private void initDataBase() throws EmployeeIntegrationException {
		
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	//JOptionPane.showMessageDialog(null, "Connection refused!");
        	throw new EmployeeIntegrationException("Conexion rechazada");
        }
        
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new EmployeeIntegrationException("Conexion rechazada");
		}
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new EmployeeIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			//e.printStackTrace();
			//e.getMessage();
			//System.out.println("connnnnnnnecttion");
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new EmployeeIntegrationException("Conexion rechazada");
		}finally{
			closeConnectionDataBase();
		}
		
	}
	
	private void closeConnectionDataBase() throws EmployeeIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new EmployeeIntegrationException("Error al desconectar BBDD");
		}
	}

}
