package reactor.core.publisher.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    public void monoTest() {
        Mono<String> stringMono = Mono.just("Spring").log();
        StepVerifier.create(stringMono)
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTestWithCount() {
        Mono<String> stringMono = Mono.just("Spring");
        StepVerifier.create(stringMono.log())
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void monoTestWithError() {
        Mono<Object> stringMono =
                Mono.error(new RuntimeException("Exception Occured"))
                        .log();
        StepVerifier.create(stringMono)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void monoTestWithExceptionMessage() {
        Mono<Object> stringMono =
                Mono.error(new RuntimeException("Exception Occured"))
                        .log();
        StepVerifier.create(stringMono)
                .expectErrorMessage("Exception Occured")
                .verify();
    }
}
