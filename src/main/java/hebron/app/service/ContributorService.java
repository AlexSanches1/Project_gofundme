package hebron.app.service;

import hebron.app.models.Contributor;
import hebron.app.models.dto.ContributorDTO;
import hebron.app.models.request_dto.RequestContributorDTO;
import hebron.app.repositry.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContributorService {

    @Autowired
    ConvertService convertService;

    @Autowired
    ContributorRepository contributorRepository;

    public void createContributor(RequestContributorDTO requestContributerDTO){

        Contributor contributor = convertService.convertToContributor(requestContributerDTO);
        if (contributor.getId() !=null) {

            contributorRepository.save(contributor);
        }

    }
    public ContributorDTO getById(Long id){
        Contributor contributor = contributorRepository.getOne(id);
        return convertService.convertContributorToDTO(contributor);
    }

    public List<ContributorDTO> listContributor(){
        List<Contributor> contributors = contributorRepository.findAll();

        return contributors.stream()
                .map(contributor -> convertService.convertContributorToDTO(contributor))
                .collect(Collectors.toList());

    }
}
