package com.student.controller;

import com.student.model.Student;
import com.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping()
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable<Student> students = studentService.findAll();
        modelAndView.addObject("list", students);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Student student = (Student) studentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@ModelAttribute Student student){
        studentService.save(student);
        return new ModelAndView("redirect:" + "/students");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("student", studentService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editStudent(@ModelAttribute Student student){
        studentService.update(student);
        return new ModelAndView("redirect:" + "/students");
    }





}
