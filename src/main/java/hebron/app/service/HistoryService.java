package hebron.app.service;

import hebron.app.exceptions.BadFileUploadException;
import hebron.app.models.History;
import hebron.app.models.dto.HistoryDTO;
import hebron.app.models.request_dto.RequestHistoryDTO;
import hebron.app.repositry.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ConvertService convertService;

    public Boolean savePhotoInFolder(MultipartFile photo) {
        try {
            byte[] bytes = photo.getBytes();
            String file = convertService.convertFileToMD5(bytes);
            String path = "C:\\Users\\sanch\\Desktop\\Project_gofundme\\src\\main\\resources\\media\\image";
            Files.write(Paths.get(path), file.getBytes());
        } catch (IOException e) {
            throw new BadFileUploadException();
        }
        return true;
    }

    public HistoryDTO addHistory(RequestHistoryDTO requestHistoryDTO) {
        if (!savePhotoInFolder(requestHistoryDTO.getPhoto())) {
            throw new BadFileUploadException("Bad Request");
        }
        History saveInDataBase;
        try {
            String fileName = convertService.convertFileToMD5(requestHistoryDTO.getPhoto().getBytes());
            saveInDataBase = historyRepository.save(convertService.convertToHistory(
                    fileName,requestHistoryDTO.getText()));
        } catch (IOException e) {
            throw  new BadFileUploadException();
        }
        return convertService.convertHistoryToDTO(saveInDataBase);
    }
}
