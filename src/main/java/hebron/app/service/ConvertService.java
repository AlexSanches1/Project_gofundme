package hebron.app.service;

import hebron.app.models.Project;
import hebron.app.models.dto.ProjectDTO;
import hebron.app.models.dto.RequestProjectDTO;
import org.springframework.stereotype.Service;

@Service
public class ConvertService {

    public Project convertToProject(RequestProjectDTO requestProjectDTO) {
        Project project = new Project();
        project.setTitle(requestProjectDTO.getTitle());
        project.setStartDate(requestProjectDTO.getStartDate());
        project.setEndDate(requestProjectDTO.getEndDate());
        project.setGoal(requestProjectDTO.getGoal());
        project.setFooter(requestProjectDTO.getFooter());
        project.setHeader(requestProjectDTO.getHeader());
        return project;
    }

    public ProjectDTO convertProjectToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setParticipantsCount(project.getParticipantsCount());
        dto.setCurrentSum(project.getCurrentSum());
        dto.setGoal(project.getGoal());
        dto.setHeader(project.getHeader());
        dto.setFooter(project.getFooter());
        return dto;
    }
}
