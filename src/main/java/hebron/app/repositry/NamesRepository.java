package hebron.app.repositry;

import hebron.app.models.Names;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamesRepository extends JpaRepository<Names, Long> {
    List<Names> findByName(String name);
}
