package br.com.coffeeandit.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class BeneficiarioDto implements Serializable {

    @Schema(description = "CPF do Beneficiário")
    @NotNull(message = "Informar o CPF.")
    private Long CPF;

    @Schema(description = "Código do banco destino")
    @NotNull(message = "Informar o código do banco de destino.")
    private Long codigoBanco;

    @Schema(description = "Informar a agência de destino.")
    @NotNull(message = "Informar a agência do destino.")
    private String agencia;

    @Schema(description = "Conta de destino")
    @NotNull(message = "Informar a conta de destino.")
    private String conta;

    @NotNull(message = "Informar o nome do Favorecido.")
    @Schema(description = "Nome do Favorecido")
    private String nomeFavorecido;
}
