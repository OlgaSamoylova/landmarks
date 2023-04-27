package landmarks.repository;

import landmarks.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface LandmarkRepository extends JpaSpecificationExecutor<Landmark>,
        JpaRepository<Landmark, Integer> {
}