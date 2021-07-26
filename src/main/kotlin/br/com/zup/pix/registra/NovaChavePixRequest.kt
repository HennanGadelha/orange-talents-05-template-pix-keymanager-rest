package br.com.zup.pix.registra

import br.com.zup.CadastraChavePixRequest
import br.com.zup.TipoChavePix
import br.com.zup.TipoDaConta
import br.com.zup.compartilhado.validacoes.ValidPixKey
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Introspected
@ValidPixKey
data class NovaChavePixRequest(
    @field:NotNull val tipoDeconta: TipoDeContaRequest,
    @field:Size(max = 77) val chave: String?,
    @field:NotNull val tipoDeChave: TipoDeChaveRequest
) {

    fun toModel(clientId: UUID): CadastraChavePixRequest{

        return CadastraChavePixRequest
            .newBuilder()
            .setClientId(clientId.toString()).setTipoDaConta(tipoDeconta?.tipoDeConta ?: TipoDaConta.TIPO_DE_CONTA_DESCONHECIDO)
            .setTipoChavePix(tipoDeChave.tipoDaChave ?: TipoChavePix.CHAVE_DESCONHECIDA)
            .setChave(chave ?: "")
            .build()
    }

}

enum class TipoDeChaveRequest(val tipoDaChave: TipoChavePix){

    CPF(TipoChavePix.CPF){

        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            if (!chave.matches("[0-9]+".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },


    CELULAR(TipoChavePix.CELULAR){

        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },


    EMAIL(TipoChavePix.EMAIL){
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },

    ALEATORIA(TipoChavePix.ALEATORIA){
        override fun valida(chave: String?) = chave.isNullOrBlank()
    };

  abstract fun valida(chave: String?): Boolean

}


enum class TipoDeContaRequest(val tipoDeConta: TipoDaConta){

    CONTA_CORRENTE(TipoDaConta.CONTA_CORRENTE),
    CONTA_POUPANCA(TipoDaConta.CONTA_POUPANCA)

}