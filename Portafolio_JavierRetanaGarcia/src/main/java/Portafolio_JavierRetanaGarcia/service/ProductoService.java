/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Portafolio_JavierRetanaGarcia.service;

import Portafolio_JavierRetanaGarcia.domain.Producto;
import Portafolio_JavierRetanaGarcia.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {

        var lista = productoRepository.findAll();

        if (activos) {
            lista.removeIf(producto -> !producto.isActivo());
        }

        return lista;
    }

    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {

        return productoRepository
                .findById(producto.getIdProducto())
                .orElse(null);
    }

    @Transactional
    public void save(Producto producto) {

        productoRepository.save(producto);
    }

    @Transactional
    public void delete(Producto producto) {

        productoRepository.delete(producto);
    }
}
