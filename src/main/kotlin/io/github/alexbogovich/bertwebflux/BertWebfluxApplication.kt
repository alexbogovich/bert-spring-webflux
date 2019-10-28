package io.github.alexbogovich.bertwebflux

import com.robrua.nlp.bert.Bert
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.router
import java.nio.file.Path

@ConfigurationProperties("bert")
@ConstructorBinding
data class BertModelProps(
    val path: String
)

@SpringBootApplication
@EnableConfigurationProperties(BertModelProps::class)
class BertWebfluxApplication {
    @Bean
    fun bert(bertModelProps: BertModelProps): Bert {
        return Bert.load(Path.of(bertModelProps.path))
    }

    @Bean
    fun routes(bertHandler: BertHandler) = router {
        POST("/bert/sequence", bertHandler::sequence)
        POST("/bert/sequence/group", bertHandler::sequenceList)
    }
}

fun main(args: Array<String>) {
    runApplication<BertWebfluxApplication>(*args)
}


