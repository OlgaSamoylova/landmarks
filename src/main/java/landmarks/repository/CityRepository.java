package landmarks.repository;

import landmarks.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaSpecificationExecutor<City>,
        JpaRepository<City, Integer> {
}
