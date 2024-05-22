package com.gvendas.gestaovendas.repository;

import com.gvendas.gestaovendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByClienteId(Long idCliente);

}
