package org.example.qwiz.ClassroomFeatures.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.qwiz.AccountFeatures.Model.Account;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Classroom")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, nullable = false,name = "classroomId")
    private String classroomId;
    @Column(nullable = false,name = "creator")
    private String creator;
    @Column(nullable = false,name = "created")
    private String created;
    @Column(nullable = false,name = "classname")
    private String classname;
    @Column(nullable = false,name = "course")
    private String course;
    @Column(nullable = false,name = "grade")
    private String grade;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Classroom_Account",
        joinColumns = {
            @JoinColumn(name = "ClassroomId",referencedColumnName = "id")
        },inverseJoinColumns = {
            @JoinColumn(name = "AccountId",referencedColumnName = "id")
        }
    )
    private List<Account> account = new ArrayList<>();

    public Classroom(Integer id) {
        this.id = id;
    }

    public Classroom(Integer id,String classroomId, String creator, String created,String classname,String course,String grade) {
        this.id = id;
        this.classroomId = classroomId;
        this.creator = creator;
        this.created = created;
        this.classname = classname;
        this.course = course;
        this.grade = grade;
    }
    
}
