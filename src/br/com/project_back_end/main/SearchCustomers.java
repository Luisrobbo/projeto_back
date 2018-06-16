package br.com.project_back_end.main;

import java.math.BigDecimal;
import java.util.List;

import br.com.project_back_end.dao.CustomerDao;
import br.com.project_back_end.model.Ativo;
import br.com.project_back_end.model.Customer;

public class SearchCustomers {
	
	public static void main(String[] args) {
	
		CustomerDao customerDAO = new CustomerDao();
		
		int valor = 560;
		int intervaloInicial = 1500;
		int intervaloFinal = 2700;
		
		List<Customer> customers = customerDAO.searchCustomersWithValueGreaterThan560(new BigDecimal(valor), Ativo.SIM, intervaloInicial, intervaloFinal);
		
		System.out.println("########## CLIENTES (" + customers.size() + ") ##########");
		
		for (Customer customer : customers) {
			
			System.out.println("CPF: " + customer.getCpf() + ", NOME: " + customer.getNome() + ", VALOR: " + customer.getValor());
			
		}
		
		System.out.println();
		System.out.println("########## VALOR ##########");
		
		System.out.println("MÉDIA: " + customerDAO.avgValueGreaterThan560(new BigDecimal(valor), Ativo.SIM, intervaloInicial, intervaloFinal));
	}

}
