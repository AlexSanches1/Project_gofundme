package hebron.app.controller;

import hebron.app.models.Names;
import hebron.app.models.Project;
import hebron.app.repositry.NamesRepository;
import hebron.app.repositry.ProjectRepository;
import hebron.app.service.NamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ResourseController {

    private NamesRepository namesRepository;

    private NamesService namesService;

    private ProjectRepository projectRepository;

    @Autowired
    public ResourseController(NamesRepository namesRepository,
                              NamesService namesService,
                              ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
        this.namesRepository = namesRepository;
        this.namesService = namesService;
    }

    @GetMapping("/res/{imageName}")
    public ResponseEntity getImageByName(@PathVariable String imageName) {
        try {
            String md5Name = namesService.getMD5NameByOriginalName(imageName);
            md5Name = "./tmp/" + md5Name;
            return ResponseEntity.ok(Files.readAllBytes(Paths.get(md5Name)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/res/upload")
    public String uploadNewFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String md5NameOfFile = namesService.getMd5NameOfFileByBytes(bytes);
            String pathTO = "./tmp/" + md5NameOfFile;
            Files.write(Paths.get(pathTO), bytes);

            // Write to DB
            Names names = new Names();
            names.setName(file.getOriginalFilename());
            names.setMd5(md5NameOfFile);
            namesRepository.save(names);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getOriginalFilename();
    }

    @PostMapping("/res/uploadMainImage")
    public ResponseEntity uploadMainImage(Long id, MultipartFile file) {
        String nameOriginFile = uploadNewFile(file);
        Project project = projectRepository.getOne(id);
        project.setPathToMainImage(nameOriginFile);
        projectRepository.save(project);
        return ResponseEntity.ok(nameOriginFile);
    }

    @PostMapping("/res/uploadVideo")
    public ResponseEntity uploadVideo(Long id, MultipartFile file) {
        String nameOriginFile = uploadNewFile(file);
        Project project = projectRepository.getOne(id);
        project.setPathToVideo(nameOriginFile);
        projectRepository.save(project);
        return ResponseEntity.ok(nameOriginFile);
    }

}
