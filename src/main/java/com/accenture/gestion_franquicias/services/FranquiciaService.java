package com.accenture.gestion_franquicias.services;

import com.accenture.gestion_franquicias.entities.Franquicia;
import com.accenture.gestion_franquicias.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Franquicia crearFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }
}
