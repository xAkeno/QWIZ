package org.example.qwiz.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.qwiz.Model.Classroom;
import org.example.qwiz.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private ClassroomService classroomService;
    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @GetMapping("/create")
    public ResponseEntity<String> Createclassroom(HttpServletRequest request) {
        String token = "";

        Cookie cookie = WebUtils.getCookie(request, "Cookie");
        if (cookie != null) {
            token = cookie.getValue();
        }
        String generatedcode = classroomService.generateClassroom(token);
        if(generatedcode != null){
            return ResponseEntity.ok().body(generatedcode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/join/{code}")
    public ResponseEntity<String> Joinclassroom(HttpServletRequest request,@PathVariable(name = "code") String code) {
        String token = "";

        Cookie cookie = WebUtils.getCookie(request, "Cookie");
        if (cookie != null) {
            token = cookie.getValue();
        }
        String responseCode = classroomService.joinClassroom(token,code).get();
        if(responseCode !=null){
            return ResponseEntity.ok().body(responseCode);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
