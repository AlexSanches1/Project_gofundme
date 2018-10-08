package hebron.app.controller;

import hebron.app.models.request_dto.RequestContributorDTO;
import hebron.app.models.request_dto.RequestDonationDTO;
import hebron.app.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContributerController {

    @Autowired
    private ContributorService contributorService;

    @PostMapping("/create/")
    public ResponseEntity createContributor(RequestDonationDTO requestDonationDTO){
        contributorService.createContributor(requestDonationDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get/list")
    public ResponseEntity getListContributor(){
        return ResponseEntity.ok(contributorService.listContributor());
    }

    @GetMapping("/getById")
    public ResponseEntity getById(Long id){
        return ResponseEntity.ok(contributorService.getById(id));
    }
}
