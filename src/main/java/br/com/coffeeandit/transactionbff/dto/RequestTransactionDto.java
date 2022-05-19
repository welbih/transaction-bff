package br.com.coffeeandit.transactionbff.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestTransactionDto extends TransactionDto{

    private SituacaoEnum situacao;
    private LocalDateTime data;
}
