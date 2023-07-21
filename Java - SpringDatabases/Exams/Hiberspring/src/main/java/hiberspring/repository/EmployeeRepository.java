package hiberspring.repository;

import hiberspring.models.dtos.ExportEmployeeDto;
import hiberspring.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select new hiberspring.models.dtos.ExportEmployeeDto(" +
            "concat(e.firstName, ' ', e.lastName) as fullName, e.position, ec.number)" +
            " from Employee as e" +
            " join EmployeeCard as ec on ec.id = e.card.id" +
            " join Branch as b on b.id = e.branch.id" +
            " join Product as p on b.id = p.branch.id" +
            " where p.branch.id is not null" +
            " order by fullName, e.position desc")
    List<ExportEmployeeDto> findAllEmployeeWithProduct();
}
