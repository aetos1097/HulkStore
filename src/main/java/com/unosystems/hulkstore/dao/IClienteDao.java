package com.unosystems.hulkstore.dao;

import com.unosystems.hulkstore.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
}
