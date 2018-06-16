package br.com.project_back_end.main;

import java.math.BigDecimal;
import java.util.Random;

import br.com.project_back_end.dao.CustomerDao;
import br.com.project_back_end.model.Ativo;
import br.com.project_back_end.model.Customer;
import br.com.project_back_end.util.GerarCPF;

public class InsertCustomer {
	
	public static void main(String[] args) {
		
		Random gerarValor = new Random();
		
		CustomerDao customerDAO = new CustomerDao();
		
		Customer customer = null;
		
		for (int i = 0; i < 2999; i++) {
			
			customer = new Customer();
			
			customer.setCpf(GerarCPF.geraCPF());
			customer.setNome("Customer_" + (i + 1));
			customer.setValor(new BigDecimal(gerarValor.nextInt(1000)));
			
			if (i % 2 == 0) {
				customer.setAtivo(Ativo.SIM);
			} else {
				customer.setAtivo(Ativo.NAO);
			}
			
			customerDAO.insert(customer);
			
		}
		
	}

}
