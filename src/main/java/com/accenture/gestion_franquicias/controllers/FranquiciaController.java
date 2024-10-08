package com.accenture.gestion_franquicias.controllers;

import com.accenture.gestion_franquicias.entities.Franquicia;
import com.accenture.gestion_franquicias.entities.Sucursal;
import com.accenture.gestion_franquicias.services.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
