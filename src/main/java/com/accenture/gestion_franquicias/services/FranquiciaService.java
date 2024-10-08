package com.accenture.gestion_franquicias.services;

import com.accenture.gestion_franquicias.dtos.MasProductosPorSucursal;
import com.accenture.gestion_franquicias.entities.Franquicia;
import com.accenture.gestion_franquicias.entities.Producto;
import com.accenture.gestion_franquicias.entities.Sucursal;
import com.accenture.gestion_franquicias.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Franquicia crearFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public Franquicia agregarSucursal(Long franquiciaId, Sucursal sucursal) {
        System.out.println("Ingreso al metodo agregarSucursal");
        Optional<Franquicia> franquiciaOptional = franquiciaRepository.findById(franquiciaId);

        if (franquiciaOptional.isPresent()) {
            Franquicia franquicia = franquiciaOptional.get();
            franquicia.getSucursales().add(sucursal);
            return franquiciaRepository.save(franquicia);
        }else{
            throw new RuntimeException("No se encontro la franquicia");
        }
    }

    public Sucursal agregarProducto(Long franquiciaId, Long sucursalId, Producto producto){
        Optional<Franquicia> franquiciaOptional = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOptional.isPresent()) {
            Franquicia franquicia = franquiciaOptional.get();
            Optional<Sucursal> sucursalOptional = franquicia.getSucursales()
                    .stream()
                    .filter(sucursal -> sucursal.getId().equals(sucursalId))
                    .findFirst();
            if(sucursalOptional.isPresent()){
                Sucursal sucursal = sucursalOptional.get();
                sucursal.getProductos().add(producto);
                return franquiciaRepository.save(franquicia).getSucursales().stream()
                        .filter(suc -> suc.getId().equals(sucursalId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontro la sucursal"));
            }else{
                throw new RuntimeException("No se encontro la sucursal");
            }
        }else{
            throw new RuntimeException("No se encontro la franquicia");
        }
    }

    public Sucursal eliminarProducto(Long franquiciaId, Long sucursalId, Long productoId){
        Optional<Franquicia> franquiciaOptional = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOptional.isPresent()) {
            Franquicia franquicia = franquiciaOptional.get();
            Optional<Sucursal> sucursalOptional = franquicia.getSucursales()
                    .stream()
                    .filter(sucursal -> sucursal.getId().equals(sucursalId))
                    .findFirst();
            if(sucursalOptional.isPresent()){
                Sucursal sucursal = sucursalOptional.get();
                Optional<Producto> productoOptional = sucursal.getProductos()
                        .stream()
                        .filter(producto -> producto.getId().equals(productoId))
                        .findFirst();
                if (productoOptional.isPresent()) {
                    sucursal.getProductos().remove(productoOptional.get());
                    return franquiciaRepository.save(franquicia).getSucursales().stream()
                            .filter(suc -> suc.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada después de la actualización"));
                } else {
                    throw new RuntimeException("Producto no encontrado en la sucursal");
                }
            }else{
                throw new RuntimeException("No se encontro la sucursal");
            }
        }else{
            throw new RuntimeException("No se encontro la franquicia");
        }
    }

    public Producto modificarStockProducto(Long franquiciaId, Long sucursalId, Long productoId, int cantidad){
        Optional<Franquicia> franquiciaOptional = franquiciaRepository.findById(franquiciaId);
        if (franquiciaOptional.isPresent()) {
            Franquicia franquicia = franquiciaOptional.get();
            Optional<Sucursal> sucursalOptional = franquicia.getSucursales()
                    .stream()
                    .filter(sucursal -> sucursal.getId().equals(sucursalId))
                    .findFirst();
            if(sucursalOptional.isPresent()){
                Sucursal sucursal = sucursalOptional.get();
                Optional<Producto> productoOptional = sucursal.getProductos()
                        .stream()
                        .filter(producto -> producto.getId().equals(productoId))
                        .findFirst();
                if (productoOptional.isPresent()) {
                    Producto producto = productoOptional.get();
                    producto.setCantidad(cantidad);
                    franquiciaRepository.save(franquicia);

                    return producto;
                } else {
                    throw new RuntimeException("Producto no encontrado en la sucursal");
                }
            }else{
                throw new RuntimeException("No se encontro la sucursal");
            }
        }else{
            throw new RuntimeException("No se encontro la franquicia");
        }
    }

    public List<MasProductosPorSucursal> obtenerProductoConMasCantidadPorSucursal() {
        List<Franquicia> franquicias = franquiciaRepository.findAll();
        List<MasProductosPorSucursal> resultado = new ArrayList<>();

        for (Franquicia franquicia : franquicias) {
            for (Sucursal sucursal : franquicia.getSucursales()) {

                Optional<Producto> productoConMasCantidad = sucursal.getProductos()
                        .stream()
                        .max(Comparator.comparingInt(Producto::getCantidad));

                if (productoConMasCantidad.isPresent()) {
                    Producto producto = productoConMasCantidad.get();
                    MasProductosPorSucursal dto = new MasProductosPorSucursal(
                            sucursal.getNombre(),
                            producto.getNombre(),
                            producto.getCantidad()
                    );
                    resultado.add(dto);
                }
            }
        }
        return resultado;
    }
}
