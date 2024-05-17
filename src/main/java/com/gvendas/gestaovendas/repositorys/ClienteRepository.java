package com.gvendas.gestaovendas.repositorys;

import com.gvendas.gestaovendas.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
}
