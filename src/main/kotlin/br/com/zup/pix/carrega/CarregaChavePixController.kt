package br.com.zup.pix.carrega

import br.com.zup.CarregaChavePixRequest
import br.com.zup.CarregaChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/api/v1/clientes/{clientId}")
class CarregaChavePixController(val carregaChaveClient: CarregaChavePixServiceGrpc.CarregaChavePixServiceBlockingStub) {

    @Get("/pix/{pixId}")
    fun carrega(clientId: UUID, pixId: UUID): HttpResponse<Any>{

        val response = carregaChaveClient.carrega(
            CarregaChavePixRequest
                .newBuilder()
                .setPixId(
                    CarregaChavePixRequest
                        .FiltroPorPixid
                        .newBuilder()
                        .setPixId(pixId.toString())
                        .setClientId(clientId.toString())
                        .build())
                .build()
        )

        return HttpResponse.ok(DetalhesChavePixResponse(response))

    }

}