package com.unosystems.hulkstore.service;

import com.unosystems.hulkstore.dao.IProductoDao;
import com.unosystems.hulkstore.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoDao productoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void delete(Long id) {
        productoDao.deleteById(id);
    }
}
