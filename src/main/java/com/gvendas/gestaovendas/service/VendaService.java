package com.gvendas.gestaovendas.service;

import com.gvendas.gestaovendas.dto.venda.*;
import com.gvendas.gestaovendas.exceptions.RegraNegocioExeption;
import com.gvendas.gestaovendas.model.Cliente;
import com.gvendas.gestaovendas.model.ItemVenda;
import com.gvendas.gestaovendas.model.Produto;
import com.gvendas.gestaovendas.model.Venda;
import com.gvendas.gestaovendas.repository.ItemVendaRepository;
import com.gvendas.gestaovendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService extends AbstractVendaService{

    private ClienteService clienteService;
    private VendaRepository vendaRepository;
    private ItemVendaRepository itemVendaRepository;
    private ProdutoService produtoService;
    @Autowired
    public VendaService(ClienteService clienteService, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long idCliente){
        Cliente cliente = validarClienteVendaExiste(idCliente);
        List<VendaResponseDTO> vendaResponseDTOList = vendaRepository.findByClienteId(idCliente).stream()
                .map(venda -> criandoVendaResponseDTO(venda, itemVendaRepository.findByVendaId(venda.getId())))
                .collect(Collectors.toList());
        return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOList);
    }

    public ClienteVendaResponseDTO listarVendaPorId(Long idVenda){
        Venda venda = validarVendaExiste(idVenda);
        return retornandoClienteVendaResponseDTO(venda, itemVendaRepository.findByVendaId(venda.getId()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO salvar(Long idCliente, VendaRequestDTO vendaDTO){
        Cliente cliente = validarClienteVendaExiste(idCliente);
        validarProdutoExisteEAtualizarQuantidade(vendaDTO.getItemVendaDTO());
        Venda vendaSalva = salvarVenda(cliente, vendaDTO);
        return retornandoClienteVendaResponseDTO(vendaSalva, itemVendaRepository.findByVendaId(vendaSalva.getId()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void deletar(Long idVenda){
        validarVendaExiste(idVenda);
        List<ItemVenda> itemVendas = itemVendaRepository.findByVendaId(idVenda);
        validarProdutoExisteEDevolverEstoque(itemVendas);
        itemVendaRepository.deleteAll(itemVendas);
        vendaRepository.deleteById(idVenda);
    }

    private void validarProdutoExisteEDevolverEstoque(List<ItemVenda> itemVendas){
        itemVendas.forEach(item -> {
            Produto produto = produtoService.validarProdutoExiste(item.getProduto().getId());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoService.atualizarQuantidadeEmEstoque(produto);
        });
    }

    private void validarQuantidadeProdutoExiste(Produto produto, Integer qtdeVendaDTO){
        if (!(produto.getQuantidade() >= qtdeVendaDTO)){
            throw new RegraNegocioExeption(String.format("A quantidade %s informada para o produto %s não está disponível em estoque",
                    qtdeVendaDTO, produto.getDescricao()));
        }
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendatDTO) {
        Venda vendaSalva = vendaRepository.save(new Venda(vendatDTO.getData(), cliente));
        vendatDTO.getItemVendaDTO().stream()
                .map(itemVendaDTO -> criandoItemVenda(itemVendaDTO, vendaSalva))
                .forEach(itemVendaRepository::save);
        return vendaSalva;
    }

    private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itemVendaDTO){
        itemVendaDTO.forEach(item -> {
            Produto produto = produtoService.validarProdutoExiste(item.getIdProduto());
            validarQuantidadeProdutoExiste(produto, item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoService.atualizarQuantidadeEmEstoque(produto);
        });
    }


    private Cliente validarClienteVendaExiste(Long idCliente){
        Optional<Cliente> cliente = clienteService.pegarClientePorId(idCliente);
        if (cliente.isEmpty()){
            throw new RegraNegocioExeption(String.format("O cliente de código %s não existe no cadastro", idCliente));
        }
        return cliente.get();
    }


    private Venda validarVendaExiste(Long idVenda) {
        Optional<Venda> venda = vendaRepository.findById(idVenda);
        if (venda.isEmpty()) {
            throw new RegraNegocioExeption(String.format("A venda de código %s não existe no cadastro", idVenda));
        }
        return venda.get();
    }

}