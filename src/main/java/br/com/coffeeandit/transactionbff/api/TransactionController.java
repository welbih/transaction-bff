package br.com.coffeeandit.transactionbff.api;

import br.com.coffeeandit.transactionbff.domain.TransactionService;
import br.com.coffeeandit.transactionbff.dto.RequestTransactionDto;
import br.com.coffeeandit.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@Tag(name = "/transaction", description = "Grupo de API's para manipulação de transações financeiras")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "API para criar uma transação financeira")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK com a transação criada."),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
        @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> sendTransaction(@RequestBody final RequestTransactionDto requestTransactionDto) {
        Optional<TransactionDto> transactionDto = transactionService.save(requestTransactionDto);
        if(transactionDto.isPresent()) {
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @Operation(description = "API para buscar as transações persistidas por Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da lista de transações."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> searchTransaction(@PathVariable("id") final String uuid) {
        Optional<TransactionDto> transactionDto = transactionService.find(uuid);
        if(transactionDto.isPresent())
            return Mono.just(transactionDto.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @Operation(description = "API para remover as transações persistidas por Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoção."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> deleteTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

    @Operation(description = "API para autorizar a transação financeira")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da confirmação da transação."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @PatchMapping(value = "/{id}/confirmar")
    public Mono<TransactionDto> confirmTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }
}
