package one.dio.labpadroesprojetosspring.services.impl;

import one.dio.labpadroesprojetosspring.models.Cliente;
import one.dio.labpadroesprojetosspring.models.ClienteRepository;
import one.dio.labpadroesprojetosspring.models.Endereco;
import one.dio.labpadroesprojetosspring.models.EnderecoRepository;
import one.dio.labpadroesprojetosspring.services.ClienteService;
import one.dio.labpadroesprojetosspring.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser injetada
 * pelo string (via {@link Autowired}). Com isso, como essa classe é um {@link Service},
 * ela será tratada como um <b>Singleton</b>.
 *
 * @author wkoju84
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    //Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

   @Autowired
    private ViaCepService viaCepService;

    //Strategy: Implementar os métodos definidos na interface.
    //Facade: Abstrair integrações com subsistemas, provendo interfaces simples.

    @Override
    public Iterable<Cliente> buscarTodos(){
        //buscar todos os clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscaPorId(Long id){
        //buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
       salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {

        //Buscar Cliente por ID, caso exista.
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()){
           salvarClienteComCep(cliente);
        // Verficar se o Endereço do Cliente já existe (pelo cep).
        // Caso não exista, integrar com o ViaCEP e persistir o retorno.
        //Alterar Cliente, vinculando o endereço (novo ou existente).

        }
    }

    private void salvarClienteComCep(Cliente cliente){

        //Verificar se o endereço do Cliente já existe (pelo Cep).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            //Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);

        //Inserir Cliente, vinculando o endereço (novo ou existente).
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {

        //deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }
}
