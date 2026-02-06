package ee.tanel.veebipood.repository;

import ee.tanel.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// repository - andmebaasiga suhtlemiseks. tema sees on kõik funktsioonid mida on võimalik adnmebaasiga teha

public interface ProductRepository extends JpaRepository<Product, Long> {
}

