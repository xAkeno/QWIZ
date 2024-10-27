package org.example.qwiz.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.qwiz.DTO.ClassroomJoinedDTO;
import org.example.qwiz.Model.Classroom;
import org.example.qwiz.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

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
    public ResponseEntity<String> Createclassroom(HttpServletRequest request) {
        String token = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check for the specific cookie name you want
                if ("token".equals(cookie.getName())) { // Use the actual cookie name
                    token = cookie.getValue();
                }
            }
        }
        String generatedcode = classroomService.generateClassroom(token);
        if(generatedcode != null){
            return ResponseEntity.ok().body(generatedcode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/join/{code}")
    public ResponseEntity<String> Joinclassroom(HttpServletRequest request,@PathVariable(name = "code") String code) {
        String token = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check for the specific cookie name you want
                if ("token".equals(cookie.getName())) { // Use the actual cookie name
                    token = cookie.getValue();
                }
            }
        }
        String responseCode = classroomService.joinClassroom(token,code).get();
        if(responseCode !=null){
            return ResponseEntity.ok().body(responseCode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/getAllClass")
    public ResponseEntity<List<ClassroomJoinedDTO>> GetAllClass(HttpServletRequest request){
        String token = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check for the specific cookie name you want
                if ("token".equals(cookie.getName())) { // Use the actual cookie name
                    token = cookie.getValue();
                }
            }
        }
//        List<?> obj =  classroomService.GetAllJoinedClassroom(token);
        return ResponseEntity.ok().body(classroomService.GetAllJoinedClassroom(token));
    }
    @GetMapping("/getAllStudent/{classcodeId}")
    public ResponseEntity<List<ClassroomJoinedDTO>> getStudent(@PathVariable("classcodeId") int id){
        if(id == 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok().body(classroomService.GetAllStudent(id));
    }
    @GetMapping("/getAllCreatedClass")
    public ResponseEntity<List<Classroom>> getAllCreatedClass(HttpServletRequest request){
        String token = "";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check for the specific cookie name you want
                if ("token".equals(cookie.getName())) { // Use the actual cookie name
                    token = cookie.getValue();
                }
            }
        }
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok().body(classroomService.GetAllTheClassCreated(token));
    }
}
