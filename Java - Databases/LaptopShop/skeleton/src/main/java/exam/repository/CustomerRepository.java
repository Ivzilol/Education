package exam.repository;

import exam.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Optional<Customer> findCustomerByFirstName(String firstName);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
