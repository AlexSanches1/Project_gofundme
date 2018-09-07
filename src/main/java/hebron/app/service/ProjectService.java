package hebron.app.service;

import hebron.app.models.Project;
import hebron.app.models.dto.ProjectDTO;
import hebron.app.models.request_dto.RequestProjectDTO;
import hebron.app.repositry.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private ConvertService convertService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ConvertService convertService) {
        this.projectRepository = projectRepository;
        this.convertService = convertService;
    }

    public void createProject(RequestProjectDTO requestProjectDTO) {
        Project project = convertService.convertToProject(requestProjectDTO);
        projectRepository.save(project);
    }

    public List<ProjectDTO> listProject() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> convertService.convertProjectToDTO(project))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.getOne(id);
        return convertService.convertProjectToDTO(project);
    }
}
