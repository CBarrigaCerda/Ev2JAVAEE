package cl.inacap.evaluacion2Model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.inacap.evaluacion2Model.dto.Cliente;

@Stateless
@LocalBean
public class ClientesDAO implements ClientesDAOLocal {

	private static List<Cliente> clientes =
			new ArrayList<>();
	
    public ClientesDAO() {
        
    }

	@Override
	public void save(Cliente cliente) {
		clientes.add(cliente);
		
	}

	@Override
	public List<Cliente> getAll() {
		return clientes;
	}

	@Override
	public void delete(Cliente cliente) {
		clientes.remove(cliente);
		
	}

	@Override
	public List<Cliente> filterByName(String nombre) {
		
		return clientes.stream().filter(c->c.getNombre().
				contains(nombre)).collect(Collectors.
						toList());
	}

}
