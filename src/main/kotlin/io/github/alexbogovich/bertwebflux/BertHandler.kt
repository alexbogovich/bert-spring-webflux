package io.github.alexbogovich.bertwebflux

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class BertHandler(private val bertAdapter: BertAdapter) {
    fun sequence(req: ServerRequest): Mono<ServerResponse> {
        return req.bodyToMono(BertEmbeddingRequest::class.java)
            .map(BertEmbeddingRequest::text)
            .flatMap(bertAdapter::embedSequence)
            .flatMap(ServerResponse.ok()::bodyValue)

    }

    fun sequenceList(req: ServerRequest): Mono<ServerResponse> {
        return req.bodyToFlux(BertEmbeddingRequest::class.java)
            .map(BertEmbeddingRequest::text)
            .collectList()
            .flatMapMany(bertAdapter::embedSequence)
            .let { ServerResponse.ok().body(it) }
    }
}
