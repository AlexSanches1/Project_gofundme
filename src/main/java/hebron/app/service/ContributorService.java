package hebron.app.service;

import hebron.app.models.Contributor;
import hebron.app.models.Donation;
import hebron.app.models.dto.ContributorDTO;
import hebron.app.models.request_dto.RequestContributorDTO;
import hebron.app.models.request_dto.RequestDonationDTO;
import hebron.app.repositry.ContributorRepository;
import hebron.app.repositry.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContributorService {

    @Autowired
    private ConvertService convertService;

    @Autowired
    private ContributorRepository contributorRepository;

    @Autowired
    private DonationRepository donationRepository;


    public ContributorDTO createContributor(RequestDonationDTO requestDonationDTO) {
        RequestContributorDTO requestContributorDTO = requestDonationDTO.getRequestContributorDTO();

        if (existContributorByEmail(requestContributorDTO.getEmail())) {
            List<Contributor> contributors = contributorRepository
                    .findByEmail(requestDonationDTO.getRequestContributorDTO().getEmail());
            if (!donationRepository.findByProjectIdAndContributorId(
                    requestDonationDTO.getProjectId(), contributors.get(0).getId()).isEmpty()
                    ) {
                List<Donation> listDonation = donationRepository.findByProjectIdAndContributorId(
                        requestDonationDTO.getProjectId(), contributors.get(0).getId());
                Donation donation = listDonation.get(0);
                donation.setSum(donation.getSum() + requestDonationDTO.getSum());
                donationRepository.save(donation);
            }else {
                Donation donation = new Donation();
                donation.setContributorId(contributors.get(0).getId());
                donation.setSum(requestDonationDTO.getSum());
                donation.setProjectId(requestDonationDTO.getProjectId());
                try {
                    donation.setDate(convertService.formatDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                donationRepository.save(donation);
            }

        }

        Contributor contributor = convertService
                .convertToContributor(requestDonationDTO.getRequestContributorDTO());
        Contributor saveContributor = contributorRepository.save(contributor);
        Donation donation = new Donation();
        donation.setContributorId(saveContributor.getId());
        donation.setSum(requestDonationDTO.getSum());
        donation.setProjectId(requestDonationDTO.getProjectId());
        try {
            donation.setDate(convertService.formatDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        donationRepository.save(donation);

        return convertService.convertContributorToDTO(saveContributor);
    }

    public ContributorDTO getById(Long lastId) {
        Contributor contributor = contributorRepository.getOne(lastId);
        return convertService.convertContributorToDTO(contributor);
    }

    public List<ContributorDTO> listContributor() {
        List<Contributor> contributors = contributorRepository.findAll();

        return contributors.stream()
                .map(contributor -> convertService.convertContributorToDTO(contributor))
                .collect(Collectors.toList());
    }

    public Boolean existContributorByEmail(String email) {
        List<Contributor> contributors = contributorRepository.findByEmail(email);
        if (contributors.isEmpty()) {
            return false;
        }
        return true;
    }

}
