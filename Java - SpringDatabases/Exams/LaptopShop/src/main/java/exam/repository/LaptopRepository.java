package exam.repository;

import exam.model.dto.ExportLaptopsDTO;
import exam.model.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    Optional<Laptop> findByMacAddress(String macAddress);
    @Query("SELECT new exam.model.dto.ExportLaptopsDTO(" +
            "l.macAddress, l.cpuSpeed, l.ram, l.storage, l.price, s.name AS shopName, t.name AS townName)" +
            " FROM Laptop AS l" +
            " JOIN l.shop AS s" +
            " JOIN s.town AS t" +
            " order by l.cpuSpeed DESC, l.ram DESC, l.storage DESC, l.macAddress ASC")
    List<ExportLaptopsDTO> findBestLaptops();
}
