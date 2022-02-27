package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.repository.PresupuestoRepository;
import com.ivarrace.gringotts.repository.model.Presupuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public List<Presupuesto> findAll(){
        return presupuestoRepository.findAll();
    }

    public Optional<Presupuesto> findById(String id){
        return presupuestoRepository.findById(id);
    }

    public Presupuesto create(Presupuesto presupuesto){
        return presupuestoRepository.save(presupuesto);
    }

    public void deleteById(String id){
        presupuestoRepository.deleteById(id);
    }

    public Presupuesto modify(String id, Presupuesto presupuesto){
        Presupuesto actual = presupuestoRepository.findById(id).get();
        actual.setName(presupuesto.getName());
        return presupuestoRepository.save(actual);
    }
}
