package com.xxc.xia.test.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/18 20:44
 */
public class FluxExampleTest {

    @Test
    public void test_fluxMap() {
        int[] cnt = { 0 };
        Integer[] array = new Integer[20];
        Arrays.fill(array, 0);
        Flux<Integer> data1 = Flux.fromArray(array).map(x -> {
            return cnt[0]++;
        }).doOnError(e -> {
            System.out.println("receive error:" + e.getMessage());
        }).doOnComplete(() -> {
            //            throw new RuntimeException("error,");
            System.out.println("do on complete");
        });
        Flux<Integer> data2 = data1.map(x -> x * 2).doOnComplete(() -> {
            System.out.println("do on complete2");
        });
        Flux<String> data3 = Flux.create(sink -> {
            data2.subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println("accept: " + integer);
                    sink.next("sink: " + integer);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable e) {
                    System.out.println("subscribe receive error:" + e.getMessage());
                    sink.error(e);
                }
            }, new Runnable() {
                @Override
                public void run() {
                     sink.complete();
                }
            });
        });
        Mono<List<String>> data4 = data3.collectList();
        List<String> res = data4.block();
        System.out.println(res);
    }
}
