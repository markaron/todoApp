package com.greenfoxacademy.secondproject.repository;

import com.greenfoxacademy.secondproject.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Repository extends CrudRepository<Todo, Long> {
  List<Todo> findAllByDone(boolean done);
}
