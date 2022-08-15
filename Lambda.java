https://www.youtube.com/playlist?list=PLuYctAHjg89ZkhgOQo0zcTtmHY5nuRYud

===================================================================


SAM: Single Abstract Method
- Qualquer inteface que tenha apenas um método abstrato



List<Integer> asList = Array.asList(1,2,3,4);
asList.stream().forEach(e -> System.out.println(e));
// manda imprimir cada elemento e da lista

asList.stream()
   .filter(e -> e%2 == 0) // se for valido aqui
   .forEach(e -> System.out.println(e)); // passa por aqui



====================================================================



List<Integer> lista = Arrays.asList(1,2,3,4,5,6,7,8,9);

// JAVA 5
for (Integer i : lista){
   System.out.println(i);
}

// JAVA 8
// stream = fluxo de dados
lista.stream().forEach(e -> System.out.println(e));

lista.stream()
   .skip(2) // pula os dois primeiros dados do stream
            // operacao intermediaria
   .skip(2) // operacoes intermediarias는 또 해도 됨
   .limit(2) // qnts elementos processar
   .distinct() // retorna uma unica vez os valores, ignorando repetidos 
               // usa equals() e hashcode() portanto deve ter essa funcao implementada
   .map() // eh uma transoformacao
   .map(e -> e*2) // a lista original nao eh modificada
   .filter(e -> e%2 == 0)

   .forEach(e -> System.out.println(e));



======================================================================



Operacoes Finais
= Stream estah fechado, ou seja, n pode ser usado mais q uma vez

ex:
- forEach(e -> System.out.println(e));
- count(); // retorna a quantidade
- min(Comparator.naturalOrder()); // classe que ja ta implementada em algumas classes
Optional<Integer> = lista.stream().min(Comparator.naturalOrder());
   // ㄴ n precisa ficar comparando se tme null
- max(Comparator.naturalOrder);

// metoro mais customizado
- collect(); // com tres parametros, mto customizavel
- collect(Collectors.toList()); // usa coletores prontos 
   // ㄴ List<Integer> = list.stream().collect(Collectors.toList()) pega o stream e armazena em uma nova lista
- collect(Collectors.groupingBy(e -> e % 2 == 0)); // agrupar por um parametro
   // Map<Boolean, List<Integer>> = list.stream().collect(Collectors.groupingBy())
   // return: {false = [1,3,5,7,9], true=[2,4,6,8]}
- collect(Collectors.groupingBy(e -> e % 3));
   // Map<Integer, List<Integer>> agrupa pelo resto da divisao por 3
- collect(Collectors.joining()); // agrupa strings
- collect(Collectors.joining(';')); // == ''.join(stringLista)


PQ USAR STREAM
- stream: loops implicitos => n sei como eh feito esse loop
- for, while, dowhile: loops explicitos => geralm. eh pior pq cria variaveis intermediarios etc


parallelStream() 으로 변경도 가능 (nao eh sempre q pode usar; eh mais complicado)



=========================================================================



JAVA OPTIONAL
// objeto que pode conter ou n um numero inteiro

public static Optional<Integer> converteEmNumero(String numeroStr){
   
   try {
      Integer integer = Integer.valueOf(numeroStr);
      return Optional.of(integer);
      // return Optional.ofNullable(null);
   }

   catch (Exception e) {
      return Optional.empty();
   }
}

numero.isPresent(); // retorna se tem valor ou n (true, false)
numero.get(); // pegar os valores que estao no Optional / se n tiver nda da excecao


EXPRESSOES LAMBDA
numero.ifPresent(n -> System.out.println(n) ); // n tem prolema de dar excecao, se n tiver esse valor, retorna vazio
numero.converteEmNumero(s).orElse(2); // converte em numero, se sim imprime, se n imprime 2
                                      // util qnd o metodo null ou vazio
numero.orElseGet(); // igual q orElse, recebe funcao lambda
numero.orElseGet( () -> {return operacaoPesada()} ); // se receber empty, realiza a operacao lambda
numero.orElseThrow( () -> new NullPointerException("Valor vazio") ); // recebe lambda e retorna exception

Stream.of(1,2,3).findFirst(); // retorna um Optional de inteiro
Stream.of(1,2,3).findFirst().ifPresent(n -> System.out.println(n));
// procure por primeiro valor, se tiver imrprima, se n tiver n imprima



OptionalInt // optional de tipo primitivo int
public OptionalInt converteEmNumero(String str){
   try {
      int integer = Integer.parseInt(str);
      return OptionalInt.of(integer);
   }
   catch (Exception e) {
      return OptionalInt.empty();
   }
}


Optional foi criado para retorno de um metodo, nunca usar como argumento!



==========================================================================



REDUCE

List<Intger> list = Arrays.asList(1,2,3,4,5,6)
Optional<Intger> = list.stream()
   .reduce( (n1, n2) -> n1 + n2 ) // soma todos os numeros
/* 1 + 2 (이 계산이 다음 계산의 첫 elemento가 됨) ==> 계산 후 리스트의 첫 elemento로 넣음
 * 3 + 3
 * 6 + 4
 * ...
 */


// reduce == pegar todos os elementos e transformar eles em um unico elemento


String s = "Seventeen bu seung gwan sol yeong won hae";
String[] split = s.split(" ");
List<String> listStr = Arrays.asList(split);
Optional<String> concatenacao - listStr.stream()
   .reduce( (s1, s2) -> sc.concat(s2) ); // s1 + s2 해도 됨


// Substracao nao eh associativa, portanto, nao usar.
// Se usar em parallell, o metodo separa em grupos diferentes, resultadno cada processo em resultados diferentes
// 그 때 부를 때마다 다를 수도 있음

   
Integer soma = list.stream()
   .reduce(0, (n1, n2) -> n1+n2); // 0 eh valor de identidade (tipo identidade na matriz, soma, mult, stringvazio)
                                  // 만약에 값이 하나만 있으면, 이 함수를 불렀을 때, 그 값을 돌려줘야함
                                  // pra q? -> pra n ter opcional vazio, sempre vai retornar a indentidade



// reduce - menor valor
double menorValor = DoubleStream.of(1.5, 2.9, 6.7)
   .reduce(Double.POSITIVE_INFINITY, (d1, d2) -> Math.min(d1, d2)) // 여기서 identidade는 최대값임. 항상 비교하면 비교되는 수가 작아야 하니까.


Integer soma = list.stream()
   .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2); // 3o arg: funcao de combinacao



// reduce - map + combiner
Optional<String> reduce = list.stream()
   .map(n1 -> n1.toString())
   .reduce((n1, n2) -> n1.concat(n2))
   // 아래와 동일
   .reduce("", 
      (n1, n2) -> n1.toString().concat(n2.toString()), // funcao de acumulacao
      (n1, n2) -> n1.concat(n2) // combina n1 e n2
   ); // 얘는 String 값 반환


// reduce -> mexer com valores/objetos imutaveis (num, str)
// collect -> mexer com valores/objetos mutaveis (strbuilder when concat str) 





==========================================================================




