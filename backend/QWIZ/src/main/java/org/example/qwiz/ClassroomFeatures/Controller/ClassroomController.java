package org.example.qwiz.ClassroomFeatures.Controller;

import org.example.qwiz.ClassroomFeatures.DTO.ClassroomJoinedDTO;
import org.example.qwiz.ClassroomFeatures.Model.Classroom;
import org.example.qwiz.ClassroomFeatures.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private ClassroomService classroomService;
    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> Createclassroom(@RequestHeader("Authorization") String authorizationHeader) {
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String generatedcode = classroomService.generateClassroom(token);
            return ResponseEntity.ok().body(generatedcode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/join/{code}")
    public ResponseEntity<String> Joinclassroom(@RequestHeader("Authorization") String authorizationHeader,@PathVariable(name = "code") String code) {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            String responseCode = classroomService.joinClassroom(token,code).get();
            return ResponseEntity.ok().body(responseCode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/getAllClass")
    public ResponseEntity<List<ClassroomJoinedDTO>> GetAllClass(@RequestHeader("Authorization") String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            return ResponseEntity.ok().body(classroomService.GetAllJoinedClassroom(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/getAllStudent/{classcodeId}")
    public ResponseEntity<List<ClassroomJoinedDTO>> getStudent(@PathVariable("classcodeId") int id,
                                                               @RequestHeader("Authorization") String authorizationHeader){
        if(id !=0 && authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            return ResponseEntity.ok().body(classroomService.GetAllStudent(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/getAllCreatedClass")
    public ResponseEntity<List<Classroom>> getAllCreatedClass(@RequestHeader("Authorization") String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            return ResponseEntity.ok().body(classroomService.GetAllTheClassCreated(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
