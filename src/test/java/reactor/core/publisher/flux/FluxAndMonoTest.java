package reactor.core.publisher.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> fluxString = Flux.just("Spring Boot", "Reactive Spring", "Spring Microservice");
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void fluxTest1() {
        Flux<String> fluxString = Flux.just("Spring Boot", "Reactive Spring", "Spring Microservice")
                .concatWith(Flux.error(new RuntimeException("Exception Occured"))).log();
        fluxString.subscribe(System.out::println,
                e -> System.out.println(e));
    }

    /**
     * Here There is error happened .
     * Hence there is no chance of onComplete event and Completed message
     * But onError event will come with Runtime Exception
     */
    @Test
    public void fluxTest2() {
        Flux<String> fluxString = Flux.just("Spring Boot", "Reactive Spring", "Spring Microservice")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .concatWith(Flux.just("Added Some More Text")) // this will never print as onError event raised .
                .log();
        fluxString.subscribe(System.out::println,
                e -> System.out.println("Exception is :" + e),
                () -> System.out.println("Completed")
        );
    }

    /**
     * Here There is no error happened .
     * Hence onComplete event will come with Completed message
     */
    @Test
    public void fluxTest3() {
        Flux<String> fluxString = Flux.just("Spring Boot", "Reactive Spring", "Spring Microservice")
                //.concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .concatWith(Flux.just("Added Some More Text"))
                .log();
        fluxString.subscribe(System.out::println,
                e -> System.out.println("Exception is :" + e),
                () -> System.out.println("Completed")
        );
    }
}
