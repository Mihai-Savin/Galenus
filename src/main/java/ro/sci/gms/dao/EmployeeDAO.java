package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.Employee;

public interface EmployeeDAO extends BaseDAO<Employee>{

	Collection<Employee> searchByName(String query);
}
