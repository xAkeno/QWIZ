package org.example.qwiz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Classroom(Integer id,String classroomId, String creator, String created) {
        this.id = id;
        this.classroomId = classroomId;
        this.creator = creator;
        this.created = created;
    }
}
