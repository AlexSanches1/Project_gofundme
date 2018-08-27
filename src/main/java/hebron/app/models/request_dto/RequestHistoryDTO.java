package hebron.app.models.request_dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestHistoryDTO {

    private String text;

    private MultipartFile photo;
}
