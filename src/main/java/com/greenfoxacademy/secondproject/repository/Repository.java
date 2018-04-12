package com.greenfoxacademy.secondproject.repository;

import com.greenfoxacademy.secondproject.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Repository extends CrudRepository<Todo, Long> {
  List<Todo> findAllByDone(boolean done);
  @Query("select x from Todo x where title like %:searchedTitle%")
  List<Todo> findAllByTitle(@Param("searchedTitle")String input);
}
