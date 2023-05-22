package com.unosystems.hulkstore.dao;

import com.unosystems.hulkstore.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDao extends CrudRepository<Producto,Long> {
}
