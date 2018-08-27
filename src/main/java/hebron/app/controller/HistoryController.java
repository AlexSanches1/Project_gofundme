package hebron.app.controller;

import hebron.app.models.request_dto.RequestHistoryDTO;
import hebron.app.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("history/")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("add/")
    public ResponseEntity addHistory(RequestHistoryDTO requestHistoryDTO) {
        return ResponseEntity.ok(historyService.addHistory(requestHistoryDTO));
    }
}
