package com.revature.assignforce.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Curriculum;

@Repository
public interface CurriculumRepo extends JpaRepository<Curriculum, Integer>{

}
