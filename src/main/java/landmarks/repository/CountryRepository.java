package landmarks.repository;

import landmarks.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaSpecificationExecutor<Country>,
        JpaRepository<Country, Integer> {
}