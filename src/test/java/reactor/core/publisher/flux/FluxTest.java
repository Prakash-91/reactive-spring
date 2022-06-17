package reactor.core.publisher.flux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void fluxTestWithOutError() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();
        StepVerifier.create(fluxString)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();

    }

    @Test
    @DisplayName("FluxTestSubscrivingOnSingleLine")
    public void fluxTestWithOutErrorSubscribingDataOnSingleLine() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();
        StepVerifier.create(fluxString)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .verifyComplete();

    }

    @Test
    @DisplayName("FluxTestCount")
    public void fluxTestCountWithOutError() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();
        StepVerifier.create(fluxString)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    public void fluxTestWithError() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .log();
        StepVerifier.create(fluxString)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();

    }

    @Test
    @DisplayName("FluxTestWithExceptionMessage")
    public void fluxTestWithErrorWithExceptionMessage() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .log();
        StepVerifier.create(fluxString)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectErrorMessage("Exception Occured")
                .verify();

    }

}
