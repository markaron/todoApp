package com.greenfoxacademy.secondproject.controller;

import com.greenfoxacademy.secondproject.model.Assignee;
import com.greenfoxacademy.secondproject.model.Todo;
import com.greenfoxacademy.secondproject.repository.AssigneeRepo;
import com.greenfoxacademy.secondproject.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  Repository repo;
  @Autowired
  AssigneeRepo assigneeRepo;

  @GetMapping(value = {"/todo", "/todo/list", "/"})
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

   @PostMapping("/todo")
   public String search(@ModelAttribute (name = "searchString") String searchString, Model model){
      model.addAttribute("todos",repo.findAllByTitle(searchString));
      return "todo";
  }

  @GetMapping("/todo/assign")
  public String assigneePage(Model model){
    model.addAttribute("assigneeList", assigneeRepo.findAll());
    return "assignee";
  }

  @GetMapping(value = "/todo/addAssignee")
  public String addAssigneePage() {
    return "addAssignee";
  }

  @PostMapping(value = "/todo/assign")
  public String addAssignee(@ModelAttribute(name = "newAssignee") String newHuman,
                            @ModelAttribute(name = "newEmail") String newEmail) {
    assigneeRepo.save(new Assignee(newHuman, newEmail));
    return "redirect:/todo/assign";
  }

  @GetMapping("/{id}/deleteHuman")
  public String deleteHuman(@PathVariable(name = "id") Long id){
    assigneeRepo.deleteById(id);
    return "redirect:/todo/assign";
  }

  @GetMapping("/{id}/editHuman")
  public String editHumanPage(@PathVariable(name = "id") Long id, Model model){
    model.addAttribute("assigneeHuman", assigneeRepo.findById(id).get());
    return "editHuman";
  }

  @PostMapping("/{id}/editHuman")
  public String editHuman(@ModelAttribute Assignee modifiedAssignee){
    assigneeRepo.save(modifiedAssignee);
    return "redirect:/todo/assign";
  }
}
