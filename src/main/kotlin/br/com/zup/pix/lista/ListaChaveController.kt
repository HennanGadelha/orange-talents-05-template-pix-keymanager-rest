package br.com.zup.pix.lista

import br.com.zup.ListarChavePixServiceGrpc
import br.com.zup.ListarChavesPixRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/api/v1/clientes/{clientId}")
class ListaChaveController(val listaClient: ListarChavePixServiceGrpc.ListarChavePixServiceBlockingStub) {


    @Get("/pix")
    fun lista(clientId: UUID) : HttpResponse<Any> {

        val chavePix = listaClient.listar(
            ListarChavesPixRequest
                .newBuilder()
                .setClientId(clientId.toString())
                .build())


        val chavesPix = chavePix.chavesList.map { ListaChaveResponse(it) }

        return  HttpResponse.ok(chavesPix)
    }
}