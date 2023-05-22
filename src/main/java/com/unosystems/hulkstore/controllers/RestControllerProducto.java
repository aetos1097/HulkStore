package com.unosystems.hulkstore.controllers;


import com.unosystems.hulkstore.entity.Producto;
import com.unosystems.hulkstore.service.ProductoServiceImpl;
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

@RestController
@RequestMapping("/api-producto")
public class RestControllerProducto {
    @Autowired
    private ProductoServiceImpl productoService;

    //mostrar productos
    @GetMapping("/productos")
    public List<Producto> mostrarProductos(){
        return productoService.findAll();
    }

    //mostrar un solo producto
    @GetMapping("/producto/{id}")
    public ResponseEntity<?> mostrarPorId(@PathVariable Long id){
        Producto producto;
        Map<String,Object> response = new HashMap<>();
        try{
            producto = productoService.findById(id);

        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta del producto en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));//captura el error de forma mas detalla
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(producto == null){
            assert producto != null;
            response.put("mensaje","El Producto : ".concat(producto.getNombre()).concat(" no existe en la base de datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    //guardar un nuevo producto
    @PostMapping("/productos")
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody Producto producto, BindingResult result){

        Producto productoNuevo;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Error en el campo ' "+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            productoNuevo = productoService.save(producto);
        }catch(DataAccessException e){
            response.put("mensaje", "error al insertar datos en el producto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El producto a sido creado con exito");
        response.put("cliente", productoNuevo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //actualizar un producto
    @PutMapping("/producto/{id}")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody Producto producto, @PathVariable Long id, BindingResult result){
        Producto productoActual = productoService.findById(id);
        Producto productoActualizado;

        Map<String,Object> response = new HashMap<>();// lugar donde ira los mensajes de exito o error, tambien nuestra respuesta
        if(result.hasErrors()){

            List<String> errors = result.getFieldErrors()//forma con streams para capturar errores
                    .stream()
                    .map(err -> "El campo en producto '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        //si el id del cliente es nulo
        if( productoActual == null){
            response.put("mensaje","Error: no se pudo editar el producto con Id: ".concat(id.toString()).concat(" porque no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            productoActual.setNombre(producto.getNombre());
            productoActual.setPrecio(producto.getPrecio());
            productoActual.setCreateAt(producto.getCreateAt());

            productoActualizado = productoService.save(productoActual);

        }catch(DataAccessException e){
            response.put("mensaje","Error al actualizar el cliente en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje","El Producto ha sido actualizado");
        response.put("producto", productoActualizado);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //eliminar productos
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){

        Map<String,Object> response = new HashMap<>();

        try{
            productoService.delete(id);

        }catch(DataAccessException e){
            response.put("mensaje","Error al eliminar el producto en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Producto ha sido eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
