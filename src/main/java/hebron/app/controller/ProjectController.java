package hebron.app.controller;

import hebron.app.models.Project;
import hebron.app.models.request_dto.RequestProjectDTO;
import hebron.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("project/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("create/")
    public ResponseEntity createNewProject(RequestProjectDTO requestProjectDTO) {
        return ResponseEntity.ok(projectService.createProject(requestProjectDTO));
    }

    @GetMapping("list/")
    public ResponseEntity listProject() {
        return ResponseEntity.ok(projectService.listProject());
    }

    @PostMapping("edit/")
    public ResponseEntity editProject(Project project, RequestProjectDTO requestProjectDTO) {
        return ResponseEntity.ok(projectService.editProject(project, requestProjectDTO));
    }

    @GetMapping("delete/")
    public void deleteProject(Project project) {
        projectService.delete(project   );
    }
}
