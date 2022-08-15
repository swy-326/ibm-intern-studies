==========================================================================
2022-08-05
==========================================================================

/* JAVA STREAM */

list.stream().map(  FUNCAO LAMBDA A SER APLICADA  )
   pega objetos de tipo Person e devolve stream


LAMBDA
x는 Person임

Passar por lista 
pega objetos de tipo Person e transforma para um fluxo e faz alguma coisa

List<String> nomes = peoples.steam()
   .map(x -> x.getName())
   .filter(nome -> nome.charAt(0) == 'A')   // filter은 오케이냐 아니냐로 구분해서 false면 collect로 들어가지 않음
   .collect(Collectors.toList());


people.stream().max( AQUI ) 
// recebe um comparador (que eh uma funcao lambda e retorna um inteiro representando se um eh maior ou igual 1, -1, 0)

// comparar duas idades:
people.stream().max((p1, p2) -> p1.getAge() - p2.getAge()).get(); 
// como eh funcao de reducao, ja retorna optional, nao precisa collect. Precisa do .get() por causa do optional

Eh bom para filtrar varias vezes





==========================================================================
2022-08-11
==========================================================================


