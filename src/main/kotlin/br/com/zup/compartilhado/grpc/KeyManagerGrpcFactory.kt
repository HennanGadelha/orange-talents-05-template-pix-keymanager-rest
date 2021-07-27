package br.com.zup.compartilhado.grpc

import br.com.zup.CarregaChavePixServiceGrpc
import br.com.zup.ChavePixServiceGrpc
import br.com.zup.ListarChavePixServiceGrpc
import br.com.zup.RemoveChavePixServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class KeyManagerGrpcFactory(@GrpcChannel("keyManager") val channel: ManagedChannel) {

    @Singleton
    fun registraChave() = ChavePixServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun removerChave() = RemoveChavePixServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun carregaChave() = CarregaChavePixServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun listaChave() = ListarChavePixServiceGrpc.newBlockingStub(channel)

}