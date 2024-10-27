package org.example.qwiz.Service;

import org.example.qwiz.DTO.ClassroomJoinedDTO;
import org.example.qwiz.Model.Account;
import org.example.qwiz.Model.Classroom;
import org.example.qwiz.Repository.AccountRepository;
import org.example.qwiz.Repository.ClassroomRepository;
import org.example.qwiz.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassroomService {
    private ClassroomRepository classroomRepository;
    private AccountRepository accountRepository;
    private JwtGenerator jwtGenerator;
    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, JwtGenerator jwtGenerator, AccountRepository accountRepository) {
        this.classroomRepository = classroomRepository;
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
    }
    public String generateClassroom(String jwt) {
        Random rand = new Random();
        Date now = new Date();
        int random1 = 1000 + rand.nextInt(9000);
        int random2 = 1000 + rand.nextInt(9000);
        if (jwt == null || jwt.equals("")) {
            return null;
        }
        String creator = JwtGenerator.extractUsername(jwt);
        String code = random1 + "-" + random2;

        Account creatorAccount = accountRepository.findByUsername(creator)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Classroom classroom = new Classroom();
        classroom.setClassroomId(code);
        classroom.setCreator(creator);
        classroom.setCreated(String.valueOf(now));

        classroom.getAccount().add(creatorAccount);

        classroomRepository.save(classroom);
        return code;
    }
    public Optional<String> joinClassroom(String jwt, String code){
        String creator = JwtGenerator.extractUsername(jwt);

        System.out.println("creator: " + creator);
        Account creatorAccount = accountRepository.findByUsername(creator)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Classroom checkClass = classroomRepository.findByClassroomId(code);

        if(checkClass == null){
            return Optional.empty();
        }

        Classroom classroom = classroomRepository.findByClassroomId(code);

        classroom.getAccount().add(creatorAccount);
        classroomRepository.save(classroom);
        return Optional.of(code);
    }
    public List<ClassroomJoinedDTO> GetAllJoinedClassroom(String jwt){
        String username = JwtGenerator.extractUsername(jwt);
        int userId = accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")).getId();
        List<Object[]> results = classroomRepository.FindJoinedAccountById(userId);
        List<ClassroomJoinedDTO> news = new ArrayList<>();
        for (Object[] row : results) {
            int accountId = (Integer) row[0];
            int classroomId = (Integer) row[1];
            System.out.println(classroomId + " " + accountId);
            ClassroomJoinedDTO classro = new ClassroomJoinedDTO(accountId,classroomId);
            news.add(classro);
        }
        return news;
    }
    public List<ClassroomJoinedDTO> GetAllStudent(int classroomId){
//        String username = JwtGenerator.extractUsername(jwt);
        List<Object[]> result = classroomRepository.FindJoinedClassroomById(classroomId);
        List<ClassroomJoinedDTO> news = new ArrayList<>();
        for (Object[] row : result) {
            int accountId = (Integer) row[0];
            ClassroomJoinedDTO classro = new ClassroomJoinedDTO(accountId);
            news.add(classro);
        }
        return news;
    }
    /*
    * private Integer id;
    @Column(unique = true, nullable = false,name = "classroomId")
    private String classroomId;
    @Column(nullable = false,name = "creator")
    private String creator;
    @Column(nullable = false,name = "created")
    private String created;*/
    public  List<Classroom> GetAllTheClassCreated(String jwt){
        String username = JwtGenerator.extractUsername(jwt);
        List<Object[]> result = classroomRepository.GetAllCreatedClass(username);
        List<Classroom> news = new ArrayList<>();
        for (Object[] row : result) {
            int id = (Integer) row[0];
            String classroomId = (String) row[1];
            String created = (String) row[2];
            String creator = (String) row[3];

            news.add(new Classroom(id,classroomId,creator,created));
        }
        return news;
    }
}
