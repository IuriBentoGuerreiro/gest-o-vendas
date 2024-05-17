package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import com.gvendas.gestaovendas.dto.venda.ItemVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.gvendas.gestaovendas.models.ItemVenda;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.models.Venda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaService {

    protected ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itemVendaList){
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
                Arrays.asList(criandoVendaResponseDTO(venda, itemVendaList)));
    }

    protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDTO , Venda venda) {
        return new ItemVenda(itemVendaDTO.getQuantidade(), itemVendaDTO.getPrecoVendido(),
                new Produto(itemVendaDTO.getIdProduto()), venda);
    }

    protected VendaResponseDTO criandoVendaResponseDTO(Venda venda, List<ItemVenda> itemVendaList){
        List<ItemVendaResponseDTO> itemVendaResponseDTO = itemVendaList
                .stream().map(this::criandoItensVendaResposeDTO).collect(Collectors.toList());
        return new VendaResponseDTO(venda.getId(), venda.getData(), itemVendaResponseDTO);
    }

    protected ItemVendaResponseDTO criandoItensVendaResposeDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getId(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProduto().getId(), itemVenda.getProduto().getDescricao());
    }
}
