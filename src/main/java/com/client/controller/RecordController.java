package com.client.controller;

import com.client.entity.Record;
import com.client.service.RecordRESTCunsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordRESTCunsumer recordService;

    @GetMapping(value = "")
    public String listRecords(Model model) {
        model.addAttribute("records", recordService.findAll());
        return "records/list";
    }

    @GetMapping(value = "/{id}/delete")
    public ModelAndView delete(@PathVariable long id) {
        recordService.delete(id);
        return new ModelAndView("redirect:/records");
    }

    @GetMapping(value = "/new")
    public String newRecord() {
        return "records/new";
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("email") String email) {
        recordService.create(new Record(name, surname, phoneNumber, email));
        return new ModelAndView("redirect:/records");
    }

    @PostMapping(value = "/update")
    public ModelAndView update(@RequestParam("record_id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("email") String email) {
        recordService.update(new Record(id, name, surname, phoneNumber, email));
        return new ModelAndView("redirect:/records");
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Record record = recordService.findOne(id);
        model.addAttribute("record", record);
        return "records/edit";
    }

}
