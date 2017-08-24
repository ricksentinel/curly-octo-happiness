package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendasController {
	
	private ProdutoRepositorioSpring pr;
	private VendaRepositorioSpring vr;
	
	@Autowired
	public VendasController(ProdutoRepositorioSpring pr, VendaRepositorioSpring vr) {
		this.pr = pr;
		this.vr = vr;
	}
	
	//GET localhost:8080/produtos
	@GetMapping("/produtos")
	public Iterable<Produto> consultarProdutos(){
		return pr.findAll();
	}
	
	@GetMapping("/vendas")
	public Iterable<Venda> consultarVendas(){
		return vr.findAll();
	}
	
	@GetMapping("/vendas/{id}")
	public ResponseEntity<Venda> consultaVenda(@PathVariable("id") int id){
		Venda v = vr.findOne(id);
		if(v != null) {
			return new ResponseEntity<>(v, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	/*public Venda consultarVenda(@PathVariable("id") int id) {
		return vr.findOne(id);
	}*/
	
	@PutMapping("/vendas")
	public ResponseEntity<Venda> inserirVenda(Venda v){  //inserirVenda(venda r)
		return null;
	}
	
	@PostMapping("/vendas/{codVenda}/{codProd}/{quant}")
	public ResponseEntity<Venda> adicionarProdutoVenda(/*@PathVariable("codVenda")*/ int codVenda,  //remover pathvariable codvenda
			@PathVariable("codProd") int codProduto,@PathVariable("quant") int quantidade){
		Venda v = vr.findOne(codVenda);
		
		if(v != null) {
			Produto p = pr.findOne(codProduto);
			if(p != null) {
				v.vender(p, quantidade);
				vr.save(v);
				return new ResponseEntity<>(v,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(v,HttpStatus.BAD_REQUEST);
	}
}
