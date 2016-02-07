package ro.sci.gms.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.gms.dao.EmployeeDAO;
import ro.sci.gms.domain.Employee;

@Repository
public class IMEmployeeDAO extends IMBaseDAO<Employee> implements EmployeeDAO {

	@Override
	public Collection<Employee> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<Employee> all = new LinkedList<Employee>(getAll());
		for (Iterator<Employee> it = all.iterator(); it.hasNext();) {
			Employee emp = it.next();
			String ss = emp.getFirstName() + " " + emp.getLastName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	

}
