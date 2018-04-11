package com.greenfoxacademy.secondproject.controller;

import com.greenfoxacademy.secondproject.model.Todo;
import com.greenfoxacademy.secondproject.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  Repository repo;

  @GetMapping(value = {"/todo", "/todo/list"})
  public String list(Model model, @RequestParam(name = "isActive", required = false) boolean isDone) {
    if (isDone) {
      model.addAttribute("todos", repo.findAllByDone(!isDone));
    } else {
      model.addAttribute("todos", repo.findAll());
    }
    return "todo";
  }

  @GetMapping(value = "/todo/adder")
  public String addTodoPage() {
    return "adder";
  }

  @PostMapping("/todo/adder")
  public String addTodo(@ModelAttribute(name = "todoDesc") String todoDescription) {
    repo.save(new Todo(todoDescription));
    return "redirect:/todo";
  }

  @GetMapping("/{id}/delete")
  public String delete(@PathVariable(name = "id") Long id){
    repo.deleteById(id);
    return "redirect:/todo";
  }

  @GetMapping("/{id}/edit")
  public String editPage(@PathVariable(name = "id") Long id, Model model){
    model.addAttribute("todo", repo.findById(id).get());
    return "edit";
  }

  @PostMapping("/{id}/edit")
  public String edit(@ModelAttribute Todo modifiedTodo){
    repo.save(modifiedTodo);
    return "redirect:/todo";
  }
}


