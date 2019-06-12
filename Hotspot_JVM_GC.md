# Hotspot JVM Garbage Collection

## 개요

Hotspot JVM은 기본적으로 Heap 영역을 Object의 Generation 별로 구분하는 Generational Collection 방식을 사용합니다.

GC 메커니즘은 Week Generational Hypothesis로 두가지 가설을 중점으로 두고 있습니다.
 - 대부분의 Object는 생성된 후 금방 Garbage가 된다.
 ~~~
 새로 할당되는 Object가 모인 곳은 단편화 발생 확률이 높다고 간주합니다.
 왜냐하면 Memory 할당은 기존 Object 다음 주소에서 계속 수행을 하기 때문에 Garbage는 먼저 할당된 부분에서 많이 생길것이라 생각하기 때문입니다.
 이 때




 ~~~
 - 오래된 Object는 만들어진지 얼마 안 된 Object를 참조할 일은 드물다.
 
