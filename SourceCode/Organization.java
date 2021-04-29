package SourceCode;
import java.util.Map;
import java.util.HashMap;


public class Organization{
	private Map<Integer, Employee> EmployeeList;
	public Organization(){
		EmployeeList = new HashMap<Integer, Employee>();
	}
	public void addEmployee(Employee e){
		EmployeeList.put(e.getID(), e);
	}
	public void deleteEmployee(int id){
		EmployeeList.remove(new Integer(id));
	}
	public Employee getEmployeeByID(int id){
		return EmployeeList.get(new Integer(id));
	}
}