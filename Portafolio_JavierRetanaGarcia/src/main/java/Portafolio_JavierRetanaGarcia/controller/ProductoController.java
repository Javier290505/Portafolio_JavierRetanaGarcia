/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Portafolio_JavierRetanaGarcia.controller;

import Portafolio_JavierRetanaGarcia.domain.Producto;
import Portafolio_JavierRetanaGarcia.service.CategoriaService;
import Portafolio_JavierRetanaGarcia.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService,
            CategoriaService categoriaService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {

        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);

        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto, Model model) {

        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("categorias", categorias);

        return "/producto/modifica";
    }

    @PostMapping("/guardar")
    public String productoGuardar(
            Producto producto,
            RedirectAttributes redirectAttributes) {

        productoService.save(producto);

        redirectAttributes.addFlashAttribute(
                "todoOk",
                "El producto se guardó correctamente."
        );

        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(
            @PathVariable Long idProducto,
            Model model) {

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);

        producto = productoService.getProducto(producto);

        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);

        return "/producto/modifica";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(
            @PathVariable Long idProducto,
            RedirectAttributes redirectAttributes) {

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);

        productoService.delete(producto);

        redirectAttributes.addFlashAttribute(
                "todoOk",
                "El producto se eliminó correctamente."
        );

        return "redirect:/producto/listado";
    }
}
