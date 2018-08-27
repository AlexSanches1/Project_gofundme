package hebron.app.service;

import hebron.app.models.History;
import hebron.app.models.Project;
import hebron.app.models.dto.HistoryDTO;
import hebron.app.models.dto.ProjectDTO;
import hebron.app.models.request_dto.RequestProjectDTO;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ConvertService {

    public Project convertToProject(RequestProjectDTO requestProjectDTO) {
        Project project = new Project();
        project.setTitle(requestProjectDTO.getTitle());
        project.setStartDate(requestProjectDTO.getStartDate());
        project.setEndDate(requestProjectDTO.getEndDate());
        project.setGoal(requestProjectDTO.getGoal());
        project.setHistories(requestProjectDTO.getHistories());
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
        dto.setHistories(project.getHistories());
        dto.setTeamMembers(project.getTeamMembers());
        return dto;
    }

    public String convertFileToMD5(byte[] bytes) {
        byte[] generatedBytes = null;
        try {
            generatedBytes = MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        StringBuffer newString = new StringBuffer();
        for (int i = 0; i < generatedBytes.length; i++) {
            newString.append(Integer.toHexString(0xFF & generatedBytes[i]));
        }
        return newString.toString();
    }

    public History convertToHistory(String fileName, String text) {
        History history = new History();
        history.setNumberImage(fileName);
        history.setText(text);
        return history;
    }

    public HistoryDTO convertHistoryToDTO(History history) {
        HistoryDTO dto = new HistoryDTO();
        dto.setId(history.getId());
        dto.setNumberImage(history.getNumberImage());
        dto.setText(history.getText());
        return dto;
    }
}
