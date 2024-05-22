# Gestão de vendas 
api de vendas com SpringBoot

## Diagrama de classes

```mermaid

classDiagram
    class Cliente {
        Long id
        String nome
        String telefone
        boolean ativo
    }

    class Endereco {
        Long id
        String logradouro
        Integer numero
        String complemento
        String bairro
        String cep
        String cidade
        String estado
    }

    class ItemVenda {
        Long id
        Integer quantidade
        BigDecimal precoVendido
        Produto produto
        Venda venda
    }

    class Produto {
        Long id
        String descricao
        Integer quantidade
        BigDecimal precoCusto
        BigDecimal precoVenda
        String observacao
        Categoria categoria
    }

    class Categoria {
        Long id
        String nome
    }

    class Venda {
        Long id
        LocalDate data
        Cliente cliente
    }

    Cliente --> Endereco
    ItemVenda --> Produto : id_produto
    ItemVenda --> Venda : id_venda
    Produto --> Categoria : id_categoria
    Venda --> Cliente : cliente

