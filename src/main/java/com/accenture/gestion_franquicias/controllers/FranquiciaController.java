package com.accenture.gestion_franquicias.controllers;

import com.accenture.gestion_franquicias.dtos.MasProductosPorSucursal;
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

    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Sucursal> eliminarProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId) {
        Sucursal sucursal = franquiciaService.eliminarProducto(franquiciaId, sucursalId, productoId);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }

    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/cantidad")
    public ResponseEntity<Producto> modificarStockProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId, @RequestBody Integer cantidad) {
        Producto producto = franquiciaService.modificarStockProducto(franquiciaId, sucursalId, productoId, cantidad);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/sucursales/producto-max")
    public ResponseEntity<List<MasProductosPorSucursal>> listaSucursales(){
        List<MasProductosPorSucursal> lista = franquiciaService.obtenerProductoConMasCantidadPorSucursal();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{franquiciaId}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(@PathVariable Long franquiciaId, @RequestBody String nombre){
        Franquicia franquicia = franquiciaService.actualizarNombreFranquicia(franquiciaId, nombre);
        return new ResponseEntity<>(franquicia, HttpStatus.OK);
    }

    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/nombre")
    public ResponseEntity<Sucursal> actualizarNombreSucursal(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody String nombre){
        Sucursal sucursal = franquiciaService.actualizarNombreSucursal(franquiciaId, sucursalId, nombre);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }

    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/nombre")
    public ResponseEntity<Producto> actualizarNombreProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId, @RequestBody String nombre){
        Producto producto = franquiciaService.actualizarNombreProducto(franquiciaId, sucursalId, productoId, nombre);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
}
