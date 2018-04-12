package com.greenfoxacademy.secondproject.repository;

import com.greenfoxacademy.secondproject.model.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepo extends CrudRepository<Assignee, Long> {
}
