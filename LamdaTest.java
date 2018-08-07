package Java.LamdaTestFile;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LamdaTest {
    private static Runnable lamdaTest(Runnable runnable){
        runnable.run();
        return ()->System.out.println("return lamda");
    }

    private static void lineSlide(){
        System.out.println("================================");
    }

    public static void main(String[] args){
        lamdaTest(()->System.out.println("input lamda")).run();

        Predicate<String> stringPredicate = (String str) -> str.compareTo("abc")==0?true:false;
        System.out.println("stringPredicate:"+stringPredicate.test("abc"));

        Function<String,Integer> intFunctionLambda = (String str)-> str.compareTo("abc")==0?1:0;
        System.out.println("intFunctionLamda:"+intFunctionLambda.apply("abc"));

        Function<String,Float> floatFunctionLambda = (String str)-> str.compareTo("abc")==0?(float)1.0:(float)0.0;
        System.out.println("floatFunctionLambda test for abc="+ floatFunctionLambda.apply("abc"));

        //대표적으로 사용되는 곳은 Collections.sort명령의 인자값
        List list = new ArrayList(10);
        list.add(1);
        list.add(2);
        Collections.sort(list,(Integer a,Integer b)->a>b?0:-1);

        BiConsumer<String, String> biConsumer = (x, y) ->  System.out.println(x+","+y);
        biConsumer.accept("First", "Second");

        lineSlide();

        List<String> listString = new ArrayList<>();
        listString.add("A");
        listString.add("B");

        for(String strObj:listString){
            System.out.println("foreach) List item="+strObj);
        }

        lineSlide();

        listString.forEach((x)->System.out.println("List item="+x));

        lineSlide();

        Map<String,String> mapString = new HashMap<>();
        mapString.put("apple","사과");
        mapString.put("banana","바나나");

        for ( String key : mapString.keySet() ) {
            System.out.println("foreach) key : " + key +" / value : " + mapString.get(key));
        }

        lineSlide();

        Iterator<String> keys = mapString.keySet().iterator();
        while ( keys.hasNext() ) {
            String key = keys.next();
            System.out.println("iterator) key : " + key +" / value : " + mapString.get(key));
        }

        lineSlide();

        mapString.forEach((x,y)->System.out.println("Map item x="+x+", y="+y));

        lineSlide();

        Set<String> setString = new HashSet<>();
        setString.add("set1");
        setString.add("set2");

        for(String strObj:setString){
            System.out.println("foreach) Set item:"+strObj);
        }

        lineSlide();

        setString.forEach((x)->System.out.println("Set item x="+x));

        lineSlide();


    }
}
