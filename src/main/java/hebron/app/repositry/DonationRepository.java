package hebron.app.repositry;

import hebron.app.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByProjectIdAndContributorId(Long projectId, Long contributeId);
}
