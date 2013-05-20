package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.transfer.EmployeeTransferServices;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class EmployeeDAOImp implements EmployeeDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public EmployeeDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createEmployeeAdmin(EmployeeTransfer t)
			throws EmployeeIntegrationException {
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
					"("+shiftID+", "+pay+","+name+","+surname+","+dni+","+tlf+", "+pass+ ");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al crear empleado ");			
		}		
		
	}
	
	@Override
	public void createEmployeeServices(EmployeeTransfer t)
			throws EmployeeIntegrationException {
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
							"("+shiftID+", "+pay+","+name+","+surname+","+dni+","+tlf+", "+null+ ");" );					
					
				} catch (SQLException e) {
					e.getMessage();
					throw new EmployeeIntegrationException("Problema al crear empleado ");			
				}
		
		
	}

	@Override
	public void deleteEmployee(int id) throws EmployeeIntegrationException {
		String QueryString = "DELETE FROM Employees WHERE idEmployee="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);			
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al eliminar empleado");				
		  }
		
	}

	@Override
	public EmployeeTransfer getEmployee(int id)
			throws EmployeeIntegrationException {
		String QueryString = "SELECT * FROM Employees WHERE idEmployee="+id+";";
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
		  }			

		return null;
	}
	
	private ShiftTransfer getShiftOfEmployee(int shiftID) throws EmployeeIntegrationException {
		String QueryShiftEmployee = "SELECT * FROM Shifts WHERE id="+shiftID+";";
	  	ShiftTransfer s = null;
		  try {
			  ResultSet rs2 = statement.executeQuery(QueryShiftEmployee);			
			//solo me devolvera 1 fila
			  while (rs2.next()) {
				  	String nameShift = rs2.getString(2);
					Date checkIn = rs2.getDate(3);
					Date checkOut = rs2.getDate(4);
					
					s = new ShiftTransfer(shiftID,nameShift, checkIn, checkOut);
			  }
		  } catch (SQLException e) {
				e.getMessage();
				throw new EmployeeIntegrationException("Problema al buscar turno de empleado");				
		  }	
		  
		  return s;
	}

	@Override
	public Vector<EmployeeTransfer> listEmployee()
			throws EmployeeIntegrationException {
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
		  }	
	}

	@Override
	public void updateEmployeeServices(EmployeeTransfer t) throws EmployeeIntegrationException {
		
		int id = ((EmployeeTransfer) t).getId();
		int shiftID =((EmployeeTransfer) t).getShift().getId();
		float pay = ((EmployeeTransfer) t).getPay();
		String name = ((EmployeeTransfer) t).getName();
		String surname = ((EmployeeTransfer) t).getSurname();
		String dni = ((EmployeeTransfer) t).getDNI();
		String tlf = ((EmployeeTransfer) t).getPhone();
		

		//UPDATE
		String QueryString = "UPDATE Employees SET shiftID="+shiftID+"," +
				"pay="+pay+",nameEmployee="+name+",surnameEmployee="+surname+",dniEmployee="+dni+",tlfEmployee="+tlf+"  WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al actualizar empleado servicios ");				
		  }
		
	}
	
	@Override
	public void updateEmployeeAdmin(EmployeeTransfer t) throws EmployeeIntegrationException {
		
		int id = ((EmployeeTransfer) t).getId();
		int shiftID =((EmployeeTransfer) t).getShift().getId();
		float pay = ((EmployeeTransfer) t).getPay();
		String name = ((EmployeeTransfer) t).getName();
		String surname = ((EmployeeTransfer) t).getSurname();
		String dni = ((EmployeeTransfer) t).getDNI();
		String tlf = ((EmployeeTransfer) t).getPhone();
		String pass = ((EmployeeTransferAdmin) t).getPassword();
		

		//UPDATE
		String QueryString = "UPDATE Employees SET shiftID="+shiftID+"," +
				"pay="+pay+",nameEmployee="+name+",surnameEmployee="+surname+",dniEmployee="+dni+",tlfEmployee="+tlf+",password="+pass+"  WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al actualizar empleado servicios ");				
		  }
		
	}

	@Override
	public boolean searchEmployee(String dni)
			throws EmployeeIntegrationException {
		
		String QueryString = "SELECT * FROM Employees WHERE dniEmployee="+dni+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al buscar empleado");				
		  }			

		return false;
	}
	
	@Override
	public boolean searchEmployeeByID(int id)
			throws EmployeeIntegrationException {
		
		String QueryString = "SELECT * FROM Employees WHERE idEmployee="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new EmployeeIntegrationException("Problema al buscar empleado");				
		  }			

		return false;
	}

}
