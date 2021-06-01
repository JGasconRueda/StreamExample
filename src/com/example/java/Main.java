package com.example.java;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        // ===============
        // Stream creation
        // ===============

        // Creacion de un Stream vacio
        Stream<String> streamEmpty = Stream.empty();

        // Creacion de un Stream a partir de una Collection (List, ArrayList, LinkedList, Set,..)
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();
        HashSet<Integer> set=new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        Stream<Integer> streamOfSet = set.stream();

        //Creacion de un Stream a partir de un Array
        Stream<String> streamOfArray = Stream.of("a", "b", "c");
        Stream<Integer> streamOfInteger = Stream.of(1, 2, 3);
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        // Stream of Primitives
        double[] arrayDouble = new double[]{1.5,2.3,3,4.5,5.8};
        DoubleStream streamOfArrayDouble = Arrays.stream(arrayDouble);
        // El método range crea un Stream ordenado desde el primer parámetro hasta el segundo sin incluir éste último
        // Incrementa el valor de los elementos en cada paso en 1
        IntStream intStream = IntStream.range(1,4);
        // rangeClosed : Igual que range pero incluye el segundo parámetro
        LongStream longStream = LongStream.rangeClosed(1, 3);
        //boxed devuelve un Stream con los elementos del IntStream, LongStream o DoubleStream
        Stream intStreamBoxed = intStream.boxed();
        Stream doubleStreamBoxed = streamOfArrayDouble.boxed();
        Stream longSteamBoxed = longStream.boxed();

        // Creacion de un Stream con builder
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        // Creacion de un Stream con generate
        // //Requiere un Supplier (proveedor)
        //Cada elemento es generado por el Supplier (proveedor) sin que nosotros le pasemos ningún parámetro
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);// Inserta 10 veces "element"

        // Stream Iterate
        // Devuelve una secuencia infinita empezando en el primer párametro (seed)
        // limit(long maxSize) -> Devuelve un string truncado al tamaño pasado
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

        // ==========================
        // Stream Métodos intermedios
        // ==========================

        // Operadores que procesan los datos de un Stream y devuelven un nuevo Stream como respuesta

        Persona p1 = new Persona("Pepe", "Perez", "Ramirez", 20);
        Persona p2 = new Persona("Juan", "Dominguez", "Gutierrez", 15);
        Persona p3 = new Persona("Antonio", "Bermudez", "Sanchez", 36);
        Persona p4 = new Persona("Jose", "Santos", "Calle", 26);
        Persona p5 = new Persona("Juan", "Dominguez", "Gutierrez", 15);
        Persona p6 = new Persona("Juan", "Dominguez", "Gutierrez", 18);

        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas.add(p1);
        listaPersonas.add(p2);
        listaPersonas.add(p2);
        listaPersonas.add(p3);
        listaPersonas.add(p4);
        listaPersonas.add(p4);
        listaPersonas.add(p4);
        listaPersonas.add(p5);
        listaPersonas.add(p5);
        listaPersonas.add(p6);
        listaPersonas.add(p6);

        // Distinct
        // Devuelve los elementos diferentes de una lista basado en el metodo equal() del objeto
        // de la lista
        List<Persona> listaPersonasNoRepetidas = listaPersonas.stream().distinct().collect(Collectors.toList());
        System.out.println("Lista Personas No repetidas "+listaPersonasNoRepetidas);

        // Map
        // Sirve para transformar los datos del Stream
        System.out.println("Lista Mayusculas "+streamBuilder.map(String::toUpperCase).collect(Collectors.toList()));

        List<Integer> listaEdadPersonas = listaPersonas.stream().map(Persona::getEdad).collect(Collectors.toList());
        List<Empleado> listaEmpleados = listaPersonas.stream().map(
                                                temp -> {
                                                    Empleado empleado = new Empleado();
                                                    empleado.setNombre(temp.getNombre());
                                                    empleado.setApellido1(temp.getApellido1());
                                                    empleado.setApellido2(temp.getApellido2());
                                                    empleado.setEdad(temp.getEdad());
                                                    if(temp.getEdad() >= 18) {
                                                        empleado.setEmpresa("Empresa de Prueba");
                                                    }
                                                    else{
                                                        empleado.setEmpresa("Sin Empresa");
                                                    }
                                                    return empleado;
                                                }
                                            ).collect(Collectors.toList());
        System.out.println("Lista Personas "+listaPersonas);
        System.out.println("Lista Empleados "+listaEmpleados);
        
        // MapInt y MapDouble
        // Metodos orientado a calculos estadisticos
        int totalEdades = listaPersonas.stream().mapToInt(Persona::getEdad).sum();
        OptionalInt edadMinima = listaPersonas.stream().mapToInt(Persona::getEdad).min();
        OptionalInt edadMaxima = listaPersonas.stream().mapToInt(Persona::getEdad).max();
        OptionalDouble edadPromedia = listaPersonas.stream().mapToDouble(Persona::getEdad).average();

        // FlatMap
        // Se usa para convertir un Stream de Stream en un Stream
        List<Persona> listaNueva = Arrays.asList(p1,p2,p3);
        List<List<Persona>> grupoPersonas = new ArrayList<>();
        grupoPersonas.add(listaPersonas);
        grupoPersonas.add(listaNueva);
        List<Persona> listaTodos = grupoPersonas.stream().flatMap(lista->lista.stream()).collect(Collectors.toList());
        System.out.println("Lista todos "+listaTodos);

        // Filter
        // Se usa para filtrar los elementos del stream segun alguna condicion/condiciones
        List<Persona> mayoresEdad = listaPersonas.stream().filter(p->p.getEdad() >= 18).collect(Collectors.toList());
        System.out.println("Mayores de Edad "+mayoresEdad);

        // Limit-Sorted
        // Limit reduce el tamaño del string
        // Sorted ordena el stream segun el comparador definido
        List<Persona> listaOrdenada = listaPersonas.stream()
                                                    .sorted(Comparator.reverseOrder())
                                                    .limit(3)
                                                    .collect(Collectors.toList());
        System.out.println("Lista de 3 personas ordenada "+listaOrdenada);

        // Peek
        // Se usa para debugear
        // Devuelve un nuevo Stream con el contenido del stream original aplicando la accion pasada
        listaPersonas.stream().filter(p->p.getEdad() >= 18).peek(System.out::println); // Escribe Persona en la consola

        // ======================
        // Stream métodos Finales
        // ======================

        // El Stream pipeline debe terminar con un método final.
        // La salida de un método final puede ser:
        //  - Un valor primitivo (long o boolean)
        //  - Un tipo de objetos Optional (Optional<List<Object>>)
        //  - Sin respuesta (void)

        // Collect
        // Combina el resultado de los elementos procesados en una colección y aplica la lógica a los
        // datos de salida (como la concatenación de cadenas).
        // Stream API proporciona la funcionalidad de recopilación con la libreria Collectoras.
        // Al usar esta libreria, puede recopilar elementos de transmisión en una list, set, or map.
        // List
        List<Persona> mayoresEdadCollectList = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                                                                .collect(Collectors.toList());
        System.out.println("Mayores de Edad con collect toList "+mayoresEdadCollectList);

        // ArrayList
        ArrayList<Persona> mayoresEdadCollectAList = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                                                                .collect(Collectors.toCollection(ArrayList::new));
        // LinkedList
        LinkedList<Persona> mayoresEdadCollectLList = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                .collect(Collectors.toCollection(LinkedList::new));

        // Con Java 10 se pueden usar unmodifiable list
        List<Persona> mayoresEdadUnmodifiable = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                                                                .collect(Collectors.toUnmodifiableList());
        System.out.println("Mayores de Edad con collect unmodifiable list "+mayoresEdadCollectList);

        // Set
        Set<Persona> mayoresEdadCollectSet = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                                                .collect(Collectors.toSet());
        System.out.println("Mayores de Edad con collect "+mayoresEdadCollectSet);

        // HashSet
        HashSet<Persona> mayoresEdadCollectHSet = listaPersonas.stream().filter(p->p.getEdad() >= 18)
                                                .collect(Collectors.toCollection(HashSet::new));

        // Map (no puede contener duplicados, hay q decidir que hacer en ese caso)
        Map<Persona,Integer> personasAparicionesMap = listaPersonas.stream()
                                        .collect(Collectors.toMap(Function.identity(),persona->1, (a1, a2)->a1=a1+1));
                                                            // toMap(clave, valor, que hacer en caso de conflicto)
                                            // Function.identity() sirve para usar el elemento del Stream como clave
        // HashMap
        HashMap<Persona,Integer> personasAparicionesHMap = listaPersonas.stream()
                .collect(Collectors.toMap(Function.identity(),persona->1, (a1, a2)->a1=a1+1,HashMap::new));
                                    //toMap(clave, valor, que hacer en caso de conflicto,mapFactory)

        // Joining
        String personas = listaPersonas.stream().map(p->p.getNombre()+" "+p.getApellido1()+" "+p.getApellido2())
                                                .collect(Collectors.joining(","));
        System.out.println(personas);

        // Reducing
        Integer suma = listaPersonas.stream().map(p->p.getEdad()).collect(Collectors.reducing(Integer::sum)).get();
        System.out.println("Suma de edades "+suma);

        // Count

        // AllMatch

        // AnyMatch

        // ForEach
    }
}
