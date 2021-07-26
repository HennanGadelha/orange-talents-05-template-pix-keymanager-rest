package br.com.zup.pix.registra

import br.com.zup.ChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory
import java.util.*
import javax.validation.Valid

@Controller("/api/v1/clientes/{clientId}")
open class RegistraChavePixController(private val  registraChavePixClient: ChavePixServiceGrpc.ChavePixServiceBlockingStub) {


    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Post("/pix")
    open fun registra(clientId: UUID, @Valid @Body chavePix: NovaChavePixRequest) : HttpResponse<Any>{

        LOGGER.info("[$clientId] criando uma nova chave pix com $chavePix")

        val grpcResponse = registraChavePixClient.cadastrar(chavePix.toModel(clientId))

        return HttpResponse.created(location(clientId, grpcResponse.pixId))

    }

    private fun location(clientId: UUID, pixId: String) = HttpResponse.uri("/api/v1/clientes/$clientId/pix/${pixId}")

}