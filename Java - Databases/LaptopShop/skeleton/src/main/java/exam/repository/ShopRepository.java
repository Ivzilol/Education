package exam.repository;

import exam.model.entity.Shop;
import exam.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findShopByNameAndTown(String name, Optional<Town> townByName);

    Optional<Shop> findByName(String shopName);
}
