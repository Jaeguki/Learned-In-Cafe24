# Hotspot JVM Garbage Collection

## 개요

Hotspot JVM은 기본적으로 Heap 영역을 객체의 Generation 별로 구분하는 Generational Collection 방식을 사용합니다.

GC 메커니즘은 Week Generational Hypothesis로 두가지 가설을 중점으로 두고 있습니다.
 - 대부분의 객체는 생성된 후 금방 Garbage가 된다.
 - 오래된 객체는 만들어진지 얼마 안 된 객체를 참조할 일은 드물다.

새로 할당되는 객체가 모인 곳은 단편화 발생 확률이 높다고 간주합니다.

왜냐하면 Memory 할당은 기존 객체 다음 주소에서 계속 수행을 하기 때문에 Garbage는 먼저 할당된 부분에서 많이 생길것이라 생각하기 때문입니다.

만약 이 때, Sweep 작업(마크되지 않은 객체를 제거하는 작업)을 수행하면 단편화가 발생하게 되며 이후 Compaction 처럼 비싼 작업을 해야합니다.
~~~
-> Compaction이 비싼 이유?
   Compaction을 수행하기 위해서는 다른 모든 작업의 수행을 멈추어야 합니다.
   사용중인 작업들에 대한 재배치 정보를 잘 유지해야 합니다.
   때문에, Compaction이 자주 수행되면 시스템의 속도가 느려질 수 있습니다.
~~~
그렇기 때문에 객체 할당만을 위한 전용 공간인 Eden Area를 만들게 되었고, GC 당시 생존되어있는 객체들을 피신시키는 Survivor Area를 따로 구성한 것입니다.

즉, Garbage가 될 확률이 적은 객체를 따로 관리한다는 것이 포인트입니다.

Hotspot JVM에서 Garbage를 추적하는 부분은 Tracing 알고리즘을 사용합니다.

Root Set에서 참조 관계를 추적하고, 생존되어있는 객체는 마킹합니다.

이러한 마킹 작업도 Young Generation에 국한됩니다.

왜냐하면 마킹 작업은 Memory Suspend 상황에서 수행이 되는데, 전체 Heap 영엑에 대한 마킹 작업은 긴 Suspend Time을 가져갈 것이기 때문입니다.

그런데 만약 이런 상황에서 오래된 객체가 얼마 안 된 객체를 참조하는 상황이 있다고 가정하면, 존재 여부 확인을 위해 Old Generation을 모두 찾아 다니게 되고, 따라서 Suspend Time이 길어지게됩니다.

그렇기 때문에 Hotspot JVM은 Card Table이란 장치를 마련했습니다.

<br>

### Card Table

**Card Table**은 Old Generation의 Memory를 대표하는 Memory 구조로,

만약 오래된 객체가 얼마 안 된 객체를 참조하면, **오래된 객체의 시작 주소에 카드(Flag)를 Dirty로 표시**하고 해당 내용을 **Card Table에 기록**하는 형식입니다.

~~~
Card는 Old Area의 Memory 512bytes 당 1byte의 공간을 차지합니다.
~~~

이후 해당 **참조**가 해제되면 Dirty Card도 사라지게끔 하여 참조관계를 쉽게 파악할 수 있게 했습니다.

때문에 Hotspot JVM은 이러한 장치를 통해 Minor GC 수행 중에 Card Table의 Dirty Card만 검색하면 빠르게 참조 관계를 파악할 수 있습니다.

그렇게하여 **오래된 객체는 만들어진지 얼마 안 된 객체를 참조할 일은 드물다.** 라는 두번째 가설을 보완했습니다.

<br>

### TLAB(Theread-Local Allocation Buffers)

Garbage Collection이 발생하거나 객체가 다른 영역이로 이동할 때 애플리케이션의 병목이 발생하여 성능에 영향을 주게 됩니다.

Hotspot JVM은 메모리 할당의 효율성을 높이기 위해 **Bump-the-Pointer**라는 기술을 사용합니다.

이것은 할당된 메모리의 바로 뒤에 메모리를 할당하는 방법으로 사용 가능한 Free List를 검색하는 등의 부가적인 작업이 없이 신속하게 할당을 처리합니다.

그러나 할당하는 작업은 기본적으로 메모리에 변경을 가하는 것이기 때문에 Lock과 같은 동기화 작업이 수반되어야 합니다.

JVM은 기본적으로 Multi-Thread 환경이기 때문에 여러 Thread가 최근 할당된 객체 뒤의 공간을 동시에 요청하면 동기화 이슈가 발생합니다.

그래서 Hotspot JVM에서는 TLAB라는 것을 사용하여, 각 스레드별 메모리 버퍼를 사용하여, 서로에게 영향을 주지 않는 메모리 작업이 가능하게 합니다.

~~~
 TLAB을 사용하지 않으면 가장 먼저 요청한 Thread 가 Heap lock을 걸고 Allocation을 수행하며 다른 Thread들은 대기하게 됩니다.
 그러나 TLAB을 사용하면 Heap을 N개의 Thread 별로 공간을 나누어 주어 그 안에서 아무런 대기 현상 없이 Allocation 가능합니다.
 단, TLAB를 Thread에게 최초로 할당하거나 할당된 TLAB가 부족하여 새로이 할당을 받을때 는 동기화 이슈가 발생합니다.
 그러나 객체 할당 횟수에 비하면 동기화 이슈가 대폭 줄어들어 할당에 걸리는 시간은 상당히 줄어듭니다.
~~~

<br>

## GC 대상 및 범위

GC 대상 ***Area는 Young, Old Generation과 Permanent Area*** 인데 Hotspot JVM은 Generation 별로 각각 GC를 수행합니다.

이 때 Young Generation 에서 발생하는 GC를 Minor GC라고 하며, 이 영역은 번번하게 GC가 수행되는 영역이며, GC에서 성숙된 객체는 Old Area로 Promotion하게 됩니다.
~~~
(여기서 성숙된 객체란 말은 애플리케이션에서 특정 회수 이상 참조되어 기준 Age를 초과한 객체를 말합니다.)
~~~

Promotion 하는 과정에서 Old Generation의 Memory도 충분하지 않으면 해당 영역에도 GC가 발생하게 되는데 이를 가리켜 Full GC(=Major GC)라고 합니다.

그리고 Permanent Area의 Memory가 부족해도 GC가 발생할 수 있는데 이때는 너무 많은 수의 Class 객체가 적재되어 Free Space가 없어졌기 때문입니다.

Permanent 영역 Memory가 부족하면 Young, Old에 사용할 수 있는 공간이 많더라고 Full GC가 발생합니다.

~~~
Java 8 버전에서는 Permanent 영역이 사라지고 Metaspace가 등장했습니다.
Java 7 까지의 Perm영역에서는 다음과 같은 정보들이 저장되어 있었습니다.
 1. Class의 Meta 정보
 2. Method의 Meta 정보
 3. Static Object
 4. 상수화된 String Object
 5. Class와 관련된 배열 객체 Meta 정보
 6. JVM 내부적인 객체들과 최적화된 컴파일러의 최적화 정보
 
이중에서 Perm영의 Memory 부족을 일으키는 상대는
 1. Static Object의 잘못된 사용
 2. Class Method Meta data의 증가 (hot deploy가 원인)
   -> Hot deploy란 톰캣 재실행 없이 class 변경 사항을 적용하는 방법
   
두 가지가 가장 큰 문제였고, Java 8버전에서 결국 Perm 영역을 없애 버리기고 Metaspace로 재탄생 했습니다.

Java 8 버전의 Metaspace이 되며 Static Object 가 Heap 영역으로 이동하고, 상수회된 String Object도 Heap영으로 이동하였습니다.
~~~

<br>

## GC 관련 옵션들

기본적인 메모리 설정 방식

JAVA_OPTS = "-DJava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms1024m -Xmx1024m -XX:NewSize=512m -XX:MaxNewSize=512m -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+DisableExplicitGC"

-Xms value : Java Heap의 초기 크기를 value 만큼 지정합니다.
 
-Xmx value : Java Heap의 최대 크기를 value 만큼 지정합니다.
~~~
Sun Hotspot JVM 계열에서는 최초 크기와 최대 크기를 동일하게 부여할 것을 권장합니다.
왜냐하면 크기의 동적인 변경으로 인한 비용을 최소화 하기 위해서입니다.
~~~
 
-XX:NewSize=value : Young Generation의 초기 크기를 value 만큼 지정합니다.
 
-XX:MaxNewSize=value : Young Generation의 최대 크기를 value 만큼 지정합니다.
~~~
객체가 생성되어 저장되는 초기공간의 value Eden + Survivor 영역입니다.
MaxNewSize는 1.4버전 이후 NewRatio에 따라 자동 계산됩니다.
NewRatio의 기본 값은 2이며, 이는 Young Generation 과 Old Generation의 비율이 영향을 주며 1:2 가 됩니다.
~~~
 
-XX:MetaspaceSize=value : Metaspace의 초기 크기를 value 만큼 지정합니다.
 
-XX:MaxMetaspaceSize=value : Metaspace의 최대 크기를 value 만큼 지정합니다.
