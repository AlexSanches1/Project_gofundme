package hebron.app.controller;

import hebron.app.models.request_dto.RequestProjectDTO;
import hebron.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("project/")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("create/")
    public ResponseEntity createNewProject(@RequestBody RequestProjectDTO requestProjectDTO) {
        projectService.createProject(requestProjectDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("list/")
    public ResponseEntity listProject() {
        return ResponseEntity.ok(projectService.listProject());
    }

    @GetMapping("delete/")
    public void deleteProject(Long id) {
        projectService.delete(id);
    }

    @GetMapping("getId/{id}")
    public ResponseEntity getFullProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
}
