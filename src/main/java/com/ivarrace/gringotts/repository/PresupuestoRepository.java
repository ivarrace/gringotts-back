package com.ivarrace.gringotts.repository;

import com.ivarrace.gringotts.repository.model.Presupuesto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PresupuestoRepository extends MongoRepository<Presupuesto, String> {

}