import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

public class TestObservableConstructs {
    public static void main(String[] args) throws Exception{
        Observable<String> obs = Observable.just("Hello RxJava");
        obs.subscribe(System.out::println);

        //Simple Observable stream using the create() method
        Observable obs1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String s1 = "Howdy!";
                    subscriber.onNext(s1);
                    subscriber.onCompleted();
                } catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }
        ).map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                return s + " Vivek";
            }
        });

        obs1.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });

        //Above example implemented using Java 8 lambdas removing the boiler plate code
        Observable obs2 = Observable.create(subscriber -> {
            try {
                String s2 = "Howdy! ";
                subscriber.onNext(s2);
                subscriber.onCompleted();
            } catch (Exception e){
                subscriber.onError(e);
            }
        }
        ).map(s -> s + "Lambdas");

        obs2.subscribe(System.out::println);

        //Simple chaining of Observable and subscribe
        List<Long> numbers = Arrays.asList(1l,2l,3l,4l);
        Subscription obs3 = Observable.from(numbers).subscribe(n->System.out.println(Math.pow(2,n)));

    }
}
