package br.com.coffeeandit.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Objeto de transporte para o envio de uma promessa de transação")
public class RequestTransactionDto extends TransactionDto{

    private SituacaoEnum situacao;
    private LocalDateTime data;

}
