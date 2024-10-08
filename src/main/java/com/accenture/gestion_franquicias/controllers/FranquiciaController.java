package com.accenture.gestion_franquicias.controllers;

import com.accenture.gestion_franquicias.entities.Franquicia;
import com.accenture.gestion_franquicias.entities.Producto;
import com.accenture.gestion_franquicias.entities.Sucursal;
import com.accenture.gestion_franquicias.services.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @PostMapping
    public ResponseEntity<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        List<Sucursal> sucursales = new ArrayList<>();
        franquicia.setSucursales(sucursales);
        Franquicia franquicia1 = franquiciaService.crearFranquicia(franquicia);
        return new ResponseEntity<>(franquicia1, HttpStatus.CREATED);
    }

    @PostMapping("/{franquiciaId}/sucursales")
    public ResponseEntity<Franquicia> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        System.out.println("Ingreso al metodo agregarSucursal");
        List<Producto> productos = new ArrayList<>();
        sucursal.setProductos(productos);
        Franquicia franquicia = franquiciaService.agregarSucursal(franquiciaId, sucursal);
        return new ResponseEntity<>(franquicia, HttpStatus.OK);
    }

    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Sucursal> agregarProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
        Sucursal sucursal = franquiciaService.agregarProducto(franquiciaId, sucursalId, producto);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }
}
