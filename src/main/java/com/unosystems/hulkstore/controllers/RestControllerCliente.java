package com.unosystems.hulkstore.controllers;


import com.unosystems.hulkstore.entity.Cliente;
import com.unosystems.hulkstore.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestControllerCliente {

    @Autowired
    private ClienteServiceImpl clienteService;

    //mostrar todos los clientes
    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteService.findAll();
    }

    //Mostrar por id de cliente
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> mostrarId(@PathVariable Long id){
        Cliente cliente = null;
        Map<String,Object> response = new HashMap<>();
        try{
            cliente = clienteService.findById(id);

        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));//captura el error de forma mas detalla
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        }
        //validamos que el cliente no este nulo
        if(cliente == null){
            response.put("mensaje","El Cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    //guardar un nuevo cliente
    @PostMapping("/clientes")
    public ResponseEntity<?> guardarCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

        Cliente clienteNuevo = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo ' "+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            clienteNuevo = clienteService.save(cliente);
        }catch(DataAccessException e){
            response.put("mensaje", "error al insertar datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El cliente a sido cread con exito");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
