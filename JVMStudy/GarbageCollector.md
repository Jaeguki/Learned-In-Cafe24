# Garbage Collection

## GC 개요

이미 할당된 Memory는 GC에 의해서 해제가 되는데 이때 Garbage는 Heap과 Method Area에 사용되지 않는 Object를 의미합니다. 그리고 소스상의 close()는 Object 사용중지 의사표현일뿐 Object를 Memory에서 삭제하겠다는 의미가 아닙니다. 개발자는 System.GC()를 명시적으로 사용함으로 GC를 발생시킬 수 있지만, 이 경우에는 **Full GC**가 발생합니다.

(**Full GC**란 Old Generation을 GC하는 것으로, 각 GC 별 알고리즘에 따라서 처리 과정중에 Stop The World를 발생하므로 추천하지 않습니다.)

(**Stop The World**란 GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것입니다. Stop The World가 발생하는 순간에는 GC를 실행하는 쓰레드를 제외한 나머지 쓰레드는 모두 작업을 멈춥니다.)

## GC로 인한 문제점

GC라는 자동화 메커니즘으로 인해 프로그래머는 직접 Memory를 다룰 필요가 없고, 잘못된 Memory 접근으로 인해 Memory Crash 현상의 소지도 없게 되었습니다. 그러나 GC는 명시적은 Memory 해제보다 느리며, GC 순간 발생하는 Suspend Time으로 인해 다양한 문제를 야기시킵니다.

## Root Set과 Garbage

Garbage Collection은 말 그대로 Garbage를 모으는 작업인데 여기서 Garbage란 사용되지 않는 Object를 말합니다. 좀 더 명확히 설명하면 Object의 사용여부는 Root Set과의 관계로 판단하게되는데 Root Set에서 어떤식으로든 Reference 관계가 있다면 Reachable Object라고 하며 이를 현재 사용하고있는 Object로 간주합니다.

Root Set이란 한 객체는 여러 다른 객체를 참조하고, 참조된 다른 객체들 또한 다른 객체들을 참조할 수 있으므로 객체들은 참조 사슬을 이룹니다. 이런 상황에서 유효한 참조 여부를 파악하려면 최초의 참조가 있어야 하는데 이를 객체 참조의 Root Set 이라고 합니다.

Root Set은 좀 더 구체적으로 말하면 아래와 같이 세 가지 참조 형태를 나뉘고, 이를 통해서 Reachable Object를 판별하고, 결론적으로 Garbage Collector는 Heap을 재활용하기 위해 Root Set에서 Unreachable Object(Garbage)를 제거해 가용 공간을 만듭니다.

~~~Java
자바 스택 내의 지역 변수, 피연산자 스택에 객체의 참조 정보가 있는 객체.
즉 자바 메서드 실행시 사용하는 지역 변수와 파라미터들에 의한 참조 정보가 있는 객체.
 -> 피연산자 스택이란, 메서드의 실제 작업공간으로 각 메서드는 피연산자 스택과 지역 변수 배열 사이에서 데이터를 교환하며 다른 메서드 호출 결과를 추가하거나 꺼내는 작업을 합니다. 

메서드 영역의 Constant Pool에 있는 참조 정보를, Constant Pool을 통해 간접 연결을 하고 있는 객체.
 -> Constant Pool 영역이란, 힙 영역의 Permanent area(고정 영역)에 생성되어 Java 프로세스의 종료까지 계속 유지되는 메모리 영역입니다.
    Constant Pool은 기본적으로 JVM에서 관리하며 프로그래머가 작성한 상수에 대해서 최우선적으로 찾아보고 없으면 상수풀에 추가한 이후 그 주소값을 리턴해줍니다.
 -> String Constant Pool은 Java 6에서 7로 넘어갈 때 OutOfMemory(OOM) 문제로 Java7에서는 Heap 영역으로 변경되었습니다.
    그 이유는 Perm 영역은 고정되어있고 Runtime 시에도 확장되지 않기 때문에 Java6 이하 버전에서 intern 메소드를 자주 호출하면 OOM이 발생할 수 있다.
 -> intern 메소드란,
    String pool에 있는 각종 문자열에 equals해서 같은게 있다면 같은 것을 반환합니다.
    같은게 없다면 String pool에 String object를 추가하고, 추가한 것을 반환합니다.
    
Native Method Stack, JNI(Java Native Interfate)를 통해 생성된 객체.
 -> Java 프로그램으로 컴파일 되어 생성되는 바이트 코드가 아닌, 실제 실행할 수 있는 기계어로 작성된 프로그램을 실행 시키는 영역.
    Java가 아닌 다른 언어로 작성된 코드를 위한 공간으로, JNI를 통해 호출하는 C/C++등의 코드를 수행하기 위한 스택 공간입니다.
~~~

## 참고 문헌

피연산자스택 [https://stackoverflow.com/questions/24427056/what-is-an-operand-stack]

intern 메소드에 대한 고찰 [https://blog.ggaman.com/918]

콘스탄트풀에 대한 고찰 [https://bbchu.tistory.com/13]

스트링풀에 대한 고찰 및 가비지 컬렉터 대상 [http://java-performance.info/string-intern-in-java-6-7-8/]

JNI 예제 [https://mommoo.tistory.com/71]
