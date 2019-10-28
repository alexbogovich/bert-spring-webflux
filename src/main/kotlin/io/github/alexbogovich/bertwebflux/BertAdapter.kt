package io.github.alexbogovich.bertwebflux

import com.robrua.nlp.bert.Bert
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class BertAdapter(private val bert: Bert) {
    fun embedSequence(string: String): Mono<FloatArray> {
        val tfProcessMono = Mono.create<FloatArray> { sink ->
            try {
                val embedSequence = bert.embedSequence(string)
                sink.success(embedSequence)
            } catch (e: Exception) {
                sink.error(e)
            }
        }
        return tfProcessMono.subscribeOn(Schedulers.boundedElastic())
    }

    fun embedSequence(strings: List<String>): Flux<FloatArray> {
        val tfProcessMono = Mono.create<List<FloatArray>> { sink ->
            try {
                val embedSequence = bert.embedSequences(strings).toList()
                sink.success(embedSequence)
            } catch (e: Exception) {
                sink.error(e)
            }
        }
        return tfProcessMono.subscribeOn(Schedulers.boundedElastic()).flatMapIterable { it }
    }
}
