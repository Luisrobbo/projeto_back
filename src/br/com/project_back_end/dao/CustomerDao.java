package br.com.project_back_end.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.project_back_end.jdbc.ConnectionFactory;
import br.com.project_back_end.model.Ativo;
import br.com.project_back_end.model.Customer;

public class CustomerDao {

	private Connection connection;

	public CustomerDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insert(Customer customer) {

		String sql = " INSERT INTO tb_customer_account (CPF, NOME, VALOR, ATIVO) " 
				   + " VALUES (?, ?, ?, ?) ";

		try (PreparedStatement stmt = this.connection.prepareStatement(sql);) {

			stmt.setString(1, customer.getCpf());
			stmt.setString(2, customer.getNome());
			stmt.setBigDecimal(3, customer.getValor());
			stmt.setInt(4, customer.getAtivo().getCodigo());

			stmt.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
	public List<Customer> searchCustomersWithValueGreaterThan560(BigDecimal valor, Ativo ativo, Integer intervaloInicial, Integer intervaloFinal) {
		
		String sql = " SELECT ca.ID, ca.CPF, ca.NOME, ca.VALOR, ca.ATIVO "
				   + " FROM tb_customer_account ca "
				   + " WHERE ca.VALOR >= ? "
				   + " AND ca.ATIVO = ? "
				   + " AND ca.ID between ? and ? ";
		
		List<Customer> customers = new ArrayList<>();
		Customer customer = null;
		
		try (
				PreparedStatement stmt = this.connection.prepareStatement(sql);
			) 
		{
			
			stmt.setBigDecimal(1, valor);
			stmt.setInt(2, ativo.getCodigo());
			stmt.setInt(3, intervaloInicial);
			stmt.setInt(4, intervaloFinal);
			
			ResultSet rsContact = stmt.executeQuery();
			
			while (rsContact.next()) {
				
				customer = new Customer();
				customer.setId(rsContact.getInt("ID"));
				customer.setCpf(rsContact.getString("CPF"));
				customer.setNome(rsContact.getString("NOME"));
				customer.setValor(rsContact.getBigDecimal("VALOR"));
				customer.setAtivo(Ativo.obterPorCodigo(rsContact.getInt("ATIVO")));
				
				customers.add(customer);
				
			}
			
			rsContact.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return customers;
		
	}
	
	public Double avgValueGreaterThan560(BigDecimal valor, Ativo ativo, Integer intervaloInicial, Integer intervaloFinal) {
		
		String sql = " SELECT avg(ca.VALOR) as MEDIA "
				   + " FROM tb_customer_account ca "
				   + " WHERE ca.VALOR >= ? "
				   + " AND ca.ATIVO = ? "
				   + " AND ca.ID between ? and ? ";
		
		Double media = null;
		
		try (
				PreparedStatement stmt = this.connection.prepareStatement(sql);
			) 
		{
			
			stmt.setBigDecimal(1, valor);
			stmt.setInt(2, ativo.getCodigo());
			stmt.setInt(3, intervaloInicial);
			stmt.setInt(4, intervaloFinal);
			
			ResultSet rsContact = stmt.executeQuery();
			
			if (rsContact.next()) {
				media = rsContact.getDouble("MEDIA");
			}
			
			rsContact.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return media;
		
	}

}
