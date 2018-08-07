package Java.LamdaTestFile;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LamdaAsyncTest {

    public static void main(String[] args){
        /*
        * [CompletableFuture 의 등장배경]
        * 여러가지 장치로서 쓰레드의 문제를 해결하고 나서도, 비동기식 멀티스레드 프로그래밍을 자유롭게 하기 위해서는 부족한 면이 있었다.
        * 비동기 타스크간의 연결, 중간에 발생하는 예외처리가 어려웠는데, 이것을 해결하기 위해 CompletableFuture가 등장하게 되었다.
        *
        * CompletableFuture는 새로운 개념은 아니고,
        * 이미 스칼라에서 Futures와 Promise, 하스켈같은 순수 함수형언어에서 말하는 모나드의에서 그 컨셉을 빌려왔다고 보면 된다.
        * 남의 좋은 것은 아주 늦게라도 가져오는 것이 자바의 습성인데, 다른 언어보다 항상 두발정도 늦게 언어 요소를 도입한다.
        * */

        /*
         * [비동기식 프로그램을 위한 일반적인 과정]
         * 예제에서 확인되듯이 두개의 비동기 타스크가 자연스럽게 연결되었다. ( A완료시 B실행)
         *
         * 1) Executor를 통해 비동기 타스크가 수행될 쓰레드를 생성하고
         * 2) CompletableFuture.runAsync를 통해 다른 쓰레드에서 비동기 식으로 동작할 로직를 선언하고
         * 3) CompletableFuture.thenRun 를 통해 첫번째 타스크가 완료된 이후에 연속적으로 동작할 로직을 선언했다.
         * */

        //CompletableFuture 기본 예제


        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(1000);
                System.out.println("Hello!");
                Thread.sleep(1000);
            }catch(Exception e){}
        },executorService).thenRun(()->System.out.println("World!"));

        System.out.println("async request is ready");



        //아래 예제는 1초 이후에 supplyAsync을 이용해 다른 쓰레드에서 스트링을 리턴시키고,
        //리턴값을 thenApply 를 통해 다른 스트링과의 조합을 한후, thenAccept를 통해 화면에 뿌려주는 형태의 체이닝이다.

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            try{Thread.sleep(3000);}catch(Exception e){}
            return "Result A on thread "+Thread.currentThread().getId();
        }).thenApply(str->str+"+ tailed")
                .thenAccept(finalResult->System.out.println(finalResult));

        System.out.println("Task exception requested on thread "+Thread.currentThread().getId());


        // [참조] https://m.blog.naver.com/2feelus/220714398973
    }
}
