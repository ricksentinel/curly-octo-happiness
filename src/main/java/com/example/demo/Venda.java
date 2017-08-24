package com.example.demo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Vendas")
public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@Column(columnDefinition="DATE")
	//private LocalDate data;
	@OneToMany(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	private Collection<ItemDeVenda> itensDeVenda;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/*public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}*/
	
	public Collection<ItemDeVenda> getItensDeVenda() {
		return itensDeVenda;
	}
	public void setItensDeVenda(Collection<ItemDeVenda> itensDeVenda) {
		this.itensDeVenda = itensDeVenda;
	}

	
	public void vender(Produto prod, int quant) {
		ItemDeVenda item = new ItemDeVenda();
		item.setProduto(prod);
		item.setQuantidade(quant);
		itensDeVenda.add(item);
	}
	
	public double getTotal() {
		double soma = 0.0;
		for(ItemDeVenda item : itensDeVenda) {
			soma += item.getSubTotal();
		}
		return soma;
	}
	
}
