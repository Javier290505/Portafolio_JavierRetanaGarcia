/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Portafolio_JavierRetanaGarcia.controller;

import Portafolio_JavierRetanaGarcia.domain.Categoria;
import Portafolio_JavierRetanaGarcia.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {

        var categorias = categoriaService.getCategorias(false);

        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());

        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @PostMapping("/guardar")
public String categoriaGuardar(Categoria categoria,
        RedirectAttributes redirectAttributes) {

    boolean nuevo = categoria.getIdCategoria() == null;

    categoriaService.save(categoria);

    if (nuevo) {
        redirectAttributes.addFlashAttribute(
                "todoOk",
                "La categoría se guardó correctamente."
        );
    } else {
        redirectAttributes.addFlashAttribute(
                "todoOk",
                "La categoría se actualizó correctamente."
        );
    }

    return "redirect:/categoria/listado";
}

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);

        return "/categoria/modifica";
    }

 @GetMapping("/eliminar/{idCategoria}")
public String categoriaEliminar(@PathVariable Long idCategoria,
        RedirectAttributes redirectAttributes) {

    Categoria categoria = new Categoria();
    categoria.setIdCategoria(idCategoria);

    categoriaService.delete(categoria);

    redirectAttributes.addFlashAttribute(
            "todoOk",
            "La categoría se eliminó correctamente."
    );

    return "redirect:/categoria/listado";
}
}


