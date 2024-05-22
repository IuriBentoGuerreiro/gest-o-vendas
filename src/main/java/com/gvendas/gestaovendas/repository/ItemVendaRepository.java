package com.gvendas.gestaovendas.repository;

import com.gvendas.gestaovendas.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query(name = "Select new com.gvendas.gestaovendas.models.ItemVenda("
            + "iv.id, iv.quantidade, iv.precoVendido, iv.produto, iv.venda)"
            + "from ItemVenda"
            +"where. iv.venda.id = : idVenda)")
    List<ItemVenda> findByVendaId(Long idVenda);
}
