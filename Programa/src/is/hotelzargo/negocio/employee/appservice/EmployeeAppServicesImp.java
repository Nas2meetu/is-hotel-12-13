package is.hotelzargo.negocio.employee.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.employee.dao.EmployeeDAO;
import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;

public class EmployeeAppServicesImp implements EmployeeAppServices {

	@Override
	public void addEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		if(t instanceof EmployeeTransferAdmin ) {
			
			checkDataEmployeeAdmin(t);
			
			try{
				if (dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployeeAdmin(t);
			} catch (EmployeeIntegrationException e) {
				throw new EmployeeAppServicesException(e.getMessage());
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{
				if (dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployeeServices(t);
			} catch (EmployeeIntegrationException e) {
				throw new EmployeeAppServicesException(e.getMessage());
			}
		}
	
		
	}
	
	@Override
	public void delEmployee(int id) throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			if (dao.searchEmployeeByID(id)){
				dao.deleteEmployee(id);
			}
			else{
				throw new EmployeeAppServicesException("Empleado inexistente con ese ID");
			}
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException(e.getMessage());
		}
		
	}

	
	
	@Override
	public Vector<EmployeeTransfer> listEmployee() throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			return dao.listEmployee();
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void modEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		
		if(t instanceof EmployeeTransferAdmin ) {
			
			checkDataEmployeeAdmin(t);
			
			try{
				if (!dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("El empleado no existe");
				else dao.updateEmployeeAdmin(t);
			} catch (EmployeeIntegrationException e) {
				throw new EmployeeAppServicesException(e.getMessage());
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{
				if (!dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("El empleado no existe");
				else dao.updateEmployeeServices(t);
			} catch (EmployeeIntegrationException e) {
				throw new EmployeeAppServicesException(e.getMessage());
			}
		}
		
	}
	
	private void checkDataEmployeeServices(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		String TLF = ((EmployeeTransferServices)t).getPhone();
		
		if(!((EmployeeTransferServices)t).getDNI().isEmpty()) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(!((EmployeeTransferServices)t).getName().isEmpty())
			throw new EmployeeAppServicesException("Sin nombre");
		if	(!((EmployeeTransferServices)t).getSurname().isEmpty())
			throw new EmployeeAppServicesException("Sin apellido");
		if ((TLF.length() != 9)||(TLF.indexOf("9") == -1)||(TLF.indexOf("6") == -1))
			throw new EmployeeAppServicesException("Telefono no valido");
		if(((EmployeeTransferServices)t).getPay() == 0)
			throw new EmployeeAppServicesException("Sin sueldo");
		
	}

	private void checkDataEmployeeAdmin(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		String TLF = ((EmployeeTransferAdmin)t).getPhone();
		
		if(!((EmployeeTransferAdmin)t).getDNI().isEmpty()) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(!((EmployeeTransferAdmin)t).getName().isEmpty())
			throw new EmployeeAppServicesException("Sin nombre");
		if(!((EmployeeTransferAdmin)t).getSurname().isEmpty())
			throw new EmployeeAppServicesException("Sin apellido");
		if(!((EmployeeTransferAdmin)t).getPassword().isEmpty())
			throw new EmployeeAppServicesException("Sin contrasegna");
		if ((TLF.length() != 9)||(TLF.indexOf("9") == -1)||(TLF.indexOf("6") == -1))
			throw new EmployeeAppServicesException("Sin tlf");
		if(((EmployeeTransferAdmin)t).getPay() == 0)
			throw new EmployeeAppServicesException("Sin sueldo");
		
		
	}

}
