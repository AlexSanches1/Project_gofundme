package hebron.app.service;

import hebron.app.models.Contributor;
import hebron.app.models.Donation;
import hebron.app.models.History;
import hebron.app.models.Project;
import hebron.app.models.dto.ContributorDTO;
import hebron.app.models.dto.HistoryDTO;
import hebron.app.models.dto.ProjectDTO;
import hebron.app.models.request_dto.RequestContributorDTO;
import hebron.app.models.request_dto.RequestDonationDTO;
import hebron.app.models.request_dto.RequestProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConvertService {


    public Project convertToProject(RequestProjectDTO requestProjectDTO) {
        Project project = new Project();
        project.setTitle(requestProjectDTO.getTitle());
        project.setShortDescription(requestProjectDTO.getShortDescription());
        project.setStartDate(requestProjectDTO.getStartDate());
        project.setEndDate(requestProjectDTO.getEndDate());
        project.setGoal(requestProjectDTO.getGoal());
        return project;
    }
    public Date formatDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.parse(String.valueOf(new Date()));
    }

    public Contributor convertToContributor(RequestContributorDTO requestContributorDTO) {
        Contributor contributor = new Contributor();
        contributor.setFullName(requestContributorDTO.getFullName());
        contributor.setEmail(requestContributorDTO.getEmail());
        contributor.setImage(requestContributorDTO.getImage());
        return contributor;
    }

    public ContributorDTO convertContributorToDTO(Contributor contributor) {
        ContributorDTO dto = new ContributorDTO();
        dto.setId(contributor.getId());
        dto.setEmail(contributor.getEmail());
        dto.setFullName(contributor.getFullName());
        dto.setImage(contributor.getImage());
        return dto;
    }

    public ProjectDTO convertProjectToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setPathToMainImage(project.getPathToMainImage());
        dto.setPathToVideo(project.getPathToVideo());
        dto.setShortDescription(project.getShortDescription());
        dto.setTitle(project.getTitle());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setParticipantsCount(project.getParticipantsCount());
        dto.setCurrentSum(project.getCurrentSum());
        dto.setGoal(project.getGoal());
        if (project.getHistory() != null) dto.setHistory(convertHistoryToDTO(project.getHistory()));
        dto.setCurrentSum(0.0);
        dto.setParticipantsCount(0);
        return dto;
    }

    public String convertFileToMD5(byte[] bytes) {
        byte[] generatedBytes = null;
        try {
            generatedBytes = MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        StringBuilder newString = new StringBuilder();
        for (byte generatedByte : generatedBytes) {
            newString.append(Integer.toHexString(0xFF & generatedByte));
        }
        return newString.toString();
    }

    public HistoryDTO convertHistoryToDTO(History history) {
        HistoryDTO dto = new HistoryDTO();
        dto.setId(history.getId());
        dto.setText(history.getText());
        return dto;
    }
}
