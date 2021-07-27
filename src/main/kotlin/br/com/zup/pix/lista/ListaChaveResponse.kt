package br.com.zup.pix.lista

import br.com.zup.ListarChavesPixResponse
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
data class ListaChaveResponse(val chavePix: ListarChavesPixResponse.ChavePix) {


    val id = chavePix.pixId
    val chave = chavePix.chave
    val tipo = chavePix.tipoDaChave
    val tipoDaConta = chavePix.tipoDaConta
    val criadaEm = chavePix.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()), ZoneOffset.UTC).toString()
    }


}