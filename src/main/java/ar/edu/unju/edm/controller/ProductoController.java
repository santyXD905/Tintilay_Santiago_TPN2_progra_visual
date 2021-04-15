package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class ProductoController {
	
	//traer un objeto de la clase "inyeccion de dependencia"
	@Autowired
	ProductoService iProductoService;
	
	//peticion desde el punto de vista de dirrecion
	@GetMapping("/producto")
	public String cargarProducto(Model model)//model trae un objeto de la vista
	{ 
		model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("producto");
	}

	//despues del mappeo permite modificar 
	@PostMapping("/producto")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model)//Model atribute es una anotacion que permite recibir un objeto del modelo
	{
		iProductoService.guardarProducto(nuevoProducto);
		//mostrar el listado de producto luego de la carga de un producto
		System.out.println(iProductoService.obtenerTodosProductos().get(0).getMarca());//permite ver el producto guardado
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());//permite mandar los productos 
		return "resultado";
	}
	
	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		//model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("redirect:/producto");
	}
}