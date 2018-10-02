package hebron.app.service;

import hebron.app.models.History;
import hebron.app.models.Project;
import hebron.app.models.dto.HistoryDTO;
import hebron.app.repositry.HistoryRepository;
import hebron.app.repositry.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    private ConvertService convertService;

    private ProjectRepository projectRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository,
                          ConvertService convertService,
                          ProjectRepository projectRepository) {
        this.historyRepository = historyRepository;
        this.convertService = convertService;
        this.projectRepository = projectRepository;
    }

    public HistoryDTO addHistory(Long id, String text) {
        History history = new History();
        Project project = projectRepository.getOne(id);
        history.setText(text);
        History saveInDataBase = historyRepository.save(history);
        project.setHistory(saveInDataBase);
        projectRepository.save(project);
        return convertService.convertHistoryToDTO(saveInDataBase);
    }
}

