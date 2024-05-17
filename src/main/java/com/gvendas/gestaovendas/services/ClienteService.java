package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.execao.RegraNegocioExeption;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.repositorys.ClienteRepository;
import com.gvendas.gestaovendas.repositorys.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> pegarClientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente){
        validarClienteDuplicado(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente){
        Cliente  clienteAtualizado = validarClienteExiste(id);
        validarClienteExiste(id);
        validarClienteDuplicado(cliente);
        BeanUtils.copyProperties(cliente, clienteAtualizado,"id");
        return clienteRepository.save(clienteAtualizado);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    private Cliente validarClienteExiste(Long id){
        Optional <Cliente> cliente = pegarClientePorId(id);
        if (cliente.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return cliente.get();
    }
    private void validarClienteDuplicado(Cliente cliente){
        Cliente cliente1 = clienteRepository.findByNome(cliente.getNome());
        if (cliente1 != null && cliente1.getId() != cliente.getId()){
            throw new RegraNegocioExeption(String.format("O %s cliente já existe", cliente.getNome().toUpperCase()));
        }
    }

}
