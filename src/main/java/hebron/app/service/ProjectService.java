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

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ConvertService convertService;

    public ProjectDTO createProject(RequestProjectDTO requestProjectDTO) {
        Project project = convertService.convertToProject(requestProjectDTO);
        Project saveProject = projectRepository.save(project);
        return convertService.convertProjectToDTO(saveProject);
    }

    public List<ProjectDTO> listProject() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> convertService.convertProjectToDTO(project))
                .collect(Collectors.toList());
    }

    public ProjectDTO editProject(Project project, RequestProjectDTO requestProjectDTO) {
        Project pastProject = projectRepository.getOne(project.getId());
        pastProject.setTitle(requestProjectDTO.getTitle());
        pastProject.setStartDate(requestProjectDTO.getStartDate());
        pastProject.setEndDate(requestProjectDTO.getEndDate());
        pastProject.setGoal(requestProjectDTO.getGoal());
        pastProject.setHistories(requestProjectDTO.getHistories());
        Project updateProject = projectRepository.save(pastProject);

        projectRepository.deleteById(project.getId());

        return convertService.convertProjectToDTO(updateProject);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
