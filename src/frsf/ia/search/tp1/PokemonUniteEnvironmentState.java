package frsf.ia.search.tp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.state.EnvironmentState;



public class PokemonUniteEnvironmentState extends EnvironmentState {

	
	 public static void main(String[] args) {
	       
		 new PokemonUniteEnvironmentState();
	  
	    }
	
	
	private Map<Integer, List<Integer>> mapAdyacencias; // Map que tiene la lista de nodos y sus adyacencias  tic
	private List<Integer> pokemonAgente;  // Lista que contiene posicion y energia  tic
	private List<Integer> pokemonMaestro;  // Lista que contiene posicion y energia  tic
	private Map<Integer, Integer> pokebolas;  //Lista de duplas que contiene posicion y energia de las pokebolas  tic
	private Map<Integer, List<Integer>> pokemonsAdversarios;  //Lista de que contiene posicion, energia y ciclos sin moverse de los enemigos
	
	private Integer cantCiclosDesdeUltimoUsoSatelite;  // Cantidad de ciclos desde el ultimo uso del satelite  tic
	private Map<String, List<Integer>> mapAtaquesEspeciales;  //Map de ataques especiales con nombre, nivel y energia  tic
	
	
	
	
	
	
	
	public PokemonUniteEnvironmentState() {
		super();
		initState();
	}


	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
		iniciarMap();
		pokemonMaestro = Arrays.asList(20, 30);
		pokemonAgente = Arrays.asList(1, 20);
		iniciarPokebolas();
		cantCiclosDesdeUltimoUsoSatelite = 10;
		iniciarAtaquesEspeciales();
		iniciarAdversarios();
		/* Sets some nodes with foods and enemies. */
		System.out.println(pokemonsAdversarios);

	}
	
	
	private void iniciarAtaquesEspeciales() {
		
		mapAtaquesEspeciales = new HashMap<String, List<Integer>>();
		
		mapAtaquesEspeciales.put("Ataque 1", Arrays.asList(2, 25));
		mapAtaquesEspeciales.put("Ataque 2", Arrays.asList(3, 50));
		mapAtaquesEspeciales.put("Ataque 3", Arrays.asList(4, 75));
	}


	private void iniciarAdversarios() {

		Integer cantidadMaximaEnemigos = mapAdyacencias.size() - pokebolas.size() - 2; //preguntar cuantos enemigos generar.
		//Integer cantidadEnemigos = getEnergia(1, cantidadMaximaEnemigos); //genero un valor random de enemigos
		Integer cantidadEnemigos = 3;
		List<Integer> nodosVacios = obtenerNodosVacios();  //lista con nodos vacios
		pokemonsAdversarios = new HashMap<>();
		for (int i = 0 ; i < cantidadEnemigos; i++) {
			Integer randomEnemigo = getEnergia(0, nodosVacios.size()-1);
			Integer nodo = nodosVacios.get(randomEnemigo);
			pokemonsAdversarios.put(nodo,Arrays.asList(getEnergia(10, 20), 0));
			nodosVacios.remove(nodo);
		}
		System.out.println("nodos vacios: " + nodosVacios);
		
		
		//List<Integer> nodosVacios =  IntStream.rangeClosed(1, 29)
		//	    .boxed().collect(Collectors.toList());
		
		//nodosVacios = nodosVacios.stream().filter(i-> i!= pokemonAgente.get(0)).collect(Collectors.toList());
		
	
		
		
	}


	
	//funcion que devuelve los nodos vacios
	private List<Integer> obtenerNodosVacios() {
		List<Integer> nodosVacios =  IntStream.rangeClosed(1, 29).boxed().collect(Collectors.toList());
		List<Integer> nodosOcupados = new ArrayList<>();
		
		nodosOcupados.add(pokemonAgente.get(0));  //agrego la posicion del agente a la lista de nodos ocupados
		nodosOcupados.add(pokemonMaestro.get(0)); //agrego la posicion del pokemon maestro a la lista de nodos ocupados
		
		
		for (Integer pokebola : pokebolas.keySet()) {
			nodosOcupados.add(pokebola);   //agrego la posicion de las pokebolas a los nodos ocupados
		}
		
		nodosVacios.removeAll(nodosOcupados);
		System.out.println(nodosOcupados);
		return nodosVacios;
		
	}


	//funcion que genera las pokebolas
	private void iniciarPokebolas() {
		pokebolas = new HashMap<>();
		Integer min = 10;
		Integer max = 30;
		
        pokebolas.put(8, getEnergia(min, max));
        pokebolas.put(11, getEnergia(min, max));
        pokebolas.put(19, getEnergia(min, max));
        pokebolas.put(21, getEnergia(min, max));
        pokebolas.put(29, getEnergia(min, max));	
	}

	
	//funcion que genera un numero aleatorio en un rango
	private Integer getEnergia(Integer min, Integer max) {
		Random aleatorio = new Random();
		return min+aleatorio.nextInt( (max+1) - min);
	}


	//funcion que crea el map con los nodos y sus adyacencias
	private void iniciarMap() {
		mapAdyacencias = new HashMap<Integer, List<Integer>>();
		mapAdyacencias.put(1, Arrays.asList(2));
		mapAdyacencias.put(2, Arrays.asList(1,3,10));
		mapAdyacencias.put(3, Arrays.asList(2, 4, 8));
		mapAdyacencias.put(4, Arrays.asList(3,5,6,9));
		mapAdyacencias.put(5, Arrays.asList(4,7));
		mapAdyacencias.put(6, Arrays.asList(4,7));
		mapAdyacencias.put(7, Arrays.asList(5,6));
		mapAdyacencias.put(8, Arrays.asList(3,9));
		mapAdyacencias.put(9, Arrays.asList(4,8,10,15));
		mapAdyacencias.put(10, Arrays.asList(2,9,11,13));
		mapAdyacencias.put(11, Arrays.asList(10,12,13));
		mapAdyacencias.put(12, Arrays.asList(11,18));
		mapAdyacencias.put(13, Arrays.asList(10,11,14));
		mapAdyacencias.put(14, Arrays.asList(13,16,18));
		mapAdyacencias.put(15, Arrays.asList(9,16));
		mapAdyacencias.put(16, Arrays.asList(14,15,17,18));
		mapAdyacencias.put(17, Arrays.asList(16,20));
		mapAdyacencias.put(18, Arrays.asList(12,14,16,21,22));
		mapAdyacencias.put(19, Arrays.asList(20,22));
		mapAdyacencias.put(20, Arrays.asList(17,19,28));
		mapAdyacencias.put(21, Arrays.asList(18,23,24));
		mapAdyacencias.put(22, Arrays.asList(18,19,25,26));
		mapAdyacencias.put(23, Arrays.asList(21));
		mapAdyacencias.put(24, Arrays.asList(21,25));
		mapAdyacencias.put(25, Arrays.asList(22,24,27));
		mapAdyacencias.put(26, Arrays.asList(22,28));
		mapAdyacencias.put(27, Arrays.asList(25,29));
		mapAdyacencias.put(28, Arrays.asList(20,26,29));
		mapAdyacencias.put(29, Arrays.asList(27,28));
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<List<Integer>> getAdyacencias() {
		//metodo para devolver los nodos adyacentes al pokemon agente.
		List<Integer> listaNodosAdyacentes = mapAdyacencias.get(pokemonAgente.get(0));
		List<List<Integer>> adyacentes = new ArrayList<>();
		for (Integer nodo : listaNodosAdyacentes) {
			if (pokemonsAdversarios.containsKey(nodo)) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.ENEMIGO_PERCEPTION,nodo,pokemonsAdversarios.get(nodo).get(1)));
			} else if (pokebolas.containsKey(nodo)) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.POKEBOLA_PERCEPTION,nodo,pokebolas.get(nodo)));
			} else if (pokemonMaestro.get(0) == nodo) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.POKEMON_MAESTRO_PERCEPTION,nodo,pokemonMaestro.get(1)));
			} else {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.EMPTY_PERCEPTION,nodo));
			}
		}
		
		return adyacentes;
	}

	
	public PokemonUniteEnvironmentState usoSatelite() {
		return this;
	}
	
	
}
