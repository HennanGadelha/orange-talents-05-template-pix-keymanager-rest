package br.com.zup.pix.remove

import br.com.zup.RemoveChavePixRequest
import br.com.zup.RemoveChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/clientes/{clientId}")
class RemoveChavePixController(private val removeChavePixClient: RemoveChavePixServiceGrpc.RemoveChavePixServiceBlockingStub) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Delete("/pix/{pixId}")
    fun delete(clientId: UUID, pixId: UUID) : HttpResponse<Any> {

        LOGGER.info("[$clientId] removendo chave com id $pixId")

        removeChavePixClient.remove(
            RemoveChavePixRequest
                .newBuilder().setClientId(clientId.toString())
                .setPixId(pixId.toString())
                .build()
        )

        return HttpResponse.ok()

    }


}