package br.com.zup.pix.carrega

import br.com.zup.CarregaChavePixResponse
import br.com.zup.TipoDaConta
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
 class DetalhesChavePixResponse(  chaveResponse: CarregaChavePixResponse) {

    val pixId = chaveResponse.pixId
    val tipo = chaveResponse.chave.tipo
    val chave = chaveResponse.chave.chave

    val criadaEm = chaveResponse.chave.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()), ZoneOffset.UTC).toString()
    }
    val tipoDaConta = when(chaveResponse.chave.conta.tipo){

        TipoDaConta.CONTA_CORRENTE -> "CONTA_CORRENTE"
        TipoDaConta.CONTA_POUPANCA -> "CONTA_POUPANCA"
        else -> "DESCONHECIDA"

    }
    val conta = mapOf(
        Pair("tipo", tipoDaConta),
        Pair("instituicao", chaveResponse.chave.conta.instituicao),
        Pair("nomeDoTitular", chaveResponse.chave.conta.nomeDoTitular),
        Pair("cpfDoTitular", chaveResponse.chave.conta.cpfDoTitular),
        Pair("agencia", chaveResponse.chave.conta.agencia),
        Pair("numeroDaConta", chaveResponse.chave.conta.numeroDaConta)
    )

}