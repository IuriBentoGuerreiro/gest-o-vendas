package com.gvendas.gestaovendas.repositorys;

import com.gvendas.gestaovendas.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByClienteId(Long idCliente);

}
