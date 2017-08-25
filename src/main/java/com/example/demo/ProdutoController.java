package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController {
	
	private ProdutoRepositorioSpring pr;
	
//	@RequestMapping("/listaprodutos")
//	public String consultarProdutos(Model model){
//		model.addAttribute("products",pr.findAll());
//		return "listaprodutos";
//	}
}
