package com.ndumiso.manageusersandtask.repository;
import com.ndumiso.manageusersandtask.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ndumiso on 14/06/18.
 */

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

}
