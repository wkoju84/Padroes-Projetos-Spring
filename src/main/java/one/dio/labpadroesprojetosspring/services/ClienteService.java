package one.dio.labpadroesprojetosspring.services;

import one.dio.labpadroesprojetosspring.models.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente.
 * Com isso, se necessário, podemos ter múltiplas implementações dessa mesma interface.
 *
 * @author wkoju84
 */

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscaPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
