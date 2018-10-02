package hebron.app.controller;

import hebron.app.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("admin/history/add/")
    public ResponseEntity addHistory(Long id, @RequestBody String text) {
        return ResponseEntity.ok(historyService.addHistory(id, text));
    }
}
