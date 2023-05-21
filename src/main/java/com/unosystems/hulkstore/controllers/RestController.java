package com.unosystems.hulkstore.controllers;


import com.unosystems.hulkstore.entity.Cliente;
import com.unosystems.hulkstore.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteService.findAll();
    }


}
