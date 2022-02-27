package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.repository.model.Presupuesto;
import com.ivarrace.gringotts.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @GetMapping("/")
    public List<Presupuesto> findAll(){
        return presupuestoService.findAll();
    }

    @GetMapping("/{id}")
    public Presupuesto findById(@PathVariable String id){
        return presupuestoService.findById(id).get();
    }

    @PostMapping("/")
    public Presupuesto create(@RequestBody Presupuesto presupuesto){
        return presupuestoService.create(presupuesto);
    }

    @PutMapping("/{id}")
    public Presupuesto put(@PathVariable String id, @RequestBody Presupuesto presupuesto){
        return presupuestoService.modify(id, presupuesto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        presupuestoService.deleteById(id);
    }

}
