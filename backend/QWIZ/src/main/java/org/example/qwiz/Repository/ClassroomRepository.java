package org.example.qwiz.Repository;

import org.example.qwiz.DTO.ClassroomJoinedDTO;
import org.example.qwiz.Model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    Classroom findByClassroomId(String classroomId);
    @Query(value = "SELECT account_id FROM classroom_account WHERE classroom_id = :classroomId",nativeQuery = true)
    List<Object[]> FindJoinedClassroomById(@Param("classroomId") int classroomId);

    @Query(value = "SELECT * FROM classroom_account WHERE account_id = :accountIds",nativeQuery = true)
    List<Object[]> FindJoinedAccountById(@Param("accountIds") int accountIds);

    @Query(value = "SELECT * FROM classroom WHERE id = :id",nativeQuery = true)
    Classroom findById(int id);

    @Query(value = "SELECT * FROM classroom WHERE creator=:creator",nativeQuery = true)
    List<Object[]> GetAllCreatedClass(@Param("creator") String creator);
}
