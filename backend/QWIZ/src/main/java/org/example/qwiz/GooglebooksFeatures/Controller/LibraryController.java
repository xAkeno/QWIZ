package org.example.qwiz.GooglebooksFeatures.Controller;

import org.example.qwiz.GooglebooksFeatures.Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @GetMapping("/search")
    public ResponseEntity<?> getlibrary(@RequestParam("query") String query){
        try {
            if (query == null || query.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Search query is empty");
            }
            if (!libraryService.getLibrary(query).isEmpty()) {
                return ResponseEntity.ok(libraryService.getLibrary(query));
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("NIGGAS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
