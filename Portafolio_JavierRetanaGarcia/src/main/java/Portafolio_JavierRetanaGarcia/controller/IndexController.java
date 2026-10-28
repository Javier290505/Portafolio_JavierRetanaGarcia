/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Portafolio_JavierRetanaGarcia.controller;

import Portafolio_JavierRetanaGarcia.domain.Categoria;
import Portafolio_JavierRetanaGarcia.service.CategoriaService;
import Portafolio_JavierRetanaGarcia.service.ProductoService;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public IndexController(ProductoService productoService,
            CategoriaService categoriaService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {

        model.addAttribute(
                "productos",
                productoService.getProductos(true)
        );

        model.addAttribute(
                "categorias",
                categoriaService.getCategorias(true)
        );

        return "index";
    }

    @GetMapping("/consultas/{idCategoria}")
    public String listado(
            @PathVariable Long idCategoria,
            Model model) {

        model.addAttribute(
                "idCategoriaActual",
                idCategoria
        );

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);

        categoria = categoriaService.getCategoria(categoria);

        var productos = categoria != null
                ? categoria.getProductos()
                : Collections.emptyList();

        model.addAttribute(
                "productos",
                productos
        );

        model.addAttribute(
                "categorias",
                categoriaService.getCategorias(true)
        );

        return "index";
    }
}
