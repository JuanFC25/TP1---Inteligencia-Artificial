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

	private Map<Integer, List<Integer>> mapAdyacencias; // Map que tiene la lista de nodos y sus adyacencias  
	private List<Integer> pokemonAgente;  // Lista que contiene posicion, energia y nivel  
	private List<Integer> pokemonMaestro;  // Lista que contiene posicion y energia  
	private Map<Integer, Integer> pokebolas;  //Lista de duplas que contiene posicion y energia de las pokebolas  
	private Map<Integer, List<Integer>> pokemonsAdversarios;  //Lista de que contiene posicion, energia y ciclos sin moverse de los enemigos
	private Map<Integer, Integer> percepcionesAmbiente = new HashMap<>(); //Map con el nodo y la percepcion
	private Integer cantCiclosDesdeUltimoUsoSatelite;  // Cantidad de ciclos desde el ultimo uso del satelite  
	private Map<String, List<Integer>> mapAtaquesEspeciales;  //Map de ataques especiales con nombre, nivel y energia  
	private Integer energiaInicial = 20;

	
	
	public PokemonUniteEnvironmentState() {
		super();
		initState();
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
		iniciarMap();
		pokemonMaestro = Arrays.asList(20, 30);
		percepcionesAmbiente.put(20, PokemonUnitePerception.POKEMON_MAESTRO_PERCEPTION);
		
		pokemonAgente = Arrays.asList(1, 20, 1);
		percepcionesAmbiente.put(1, PokemonUnitePerception.EMPTY_PERCEPTION);
		
		iniciarPokebolas();
		cantCiclosDesdeUltimoUsoSatelite = 10;
		iniciarAtaquesEspeciales();
		iniciarAdversarios();
		
		/* Sets some nodes with foods and enemies. */
		System.out.println(pokemonsAdversarios);

	}
	
	
	public Map<Integer, Integer> getPercepcionesAmbiente() {
		return percepcionesAmbiente;
	}

	public void setPercepcionesAmbiente(Map<Integer, Integer> percepcionesAmbiente) {
		this.percepcionesAmbiente = percepcionesAmbiente;
	}

	public Integer getEnergiaInicial() {
		return energiaInicial;
	}

	public void setEnergiaInicial(Integer energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	private void iniciarNodosVacios(List<Integer> nodosVacios) {
		System.out.println("NODOS VACIOS: " + nodosVacios);
		nodosVacios.forEach(n -> percepcionesAmbiente.put(n, PokemonUnitePerception.EMPTY_PERCEPTION));
	}

	private void iniciarAtaquesEspeciales() {
		
		mapAtaquesEspeciales = new HashMap<String, List<Integer>>();
		
		mapAtaquesEspeciales.put("Ataque 1", Arrays.asList(2, 20));
		mapAtaquesEspeciales.put("Ataque 2", Arrays.asList(3, 30));
		mapAtaquesEspeciales.put("Ataque 3", Arrays.asList(4, 50));
	}


	private void iniciarAdversarios() {

		Integer cantidadMaximaEnemigos = mapAdyacencias.size() - pokebolas.size() - 2; //preguntar cuantos enemigos generar.
		//Integer cantidadEnemigos = getEnergia(1, cantidadMaximaEnemigos); //genero un valor random de enemigos
		Integer cantidadEnemigos = 5;   // despues ver esto
		List<Integer> nodosVacios = obtenerNodosVacios();  //lista con nodos vacios
		pokemonsAdversarios = new HashMap<>();
		for (int i = 0 ; i < cantidadEnemigos; i++) {
			Integer randomEnemigo = getRandom(0, nodosVacios.size()-1);
			Integer nodo = nodosVacios.get(randomEnemigo);
			
			percepcionesAmbiente.put(nodo, PokemonUnitePerception.ENEMIGO_PERCEPTION);
			pokemonsAdversarios.put(nodo,Arrays.asList(getRandom(10, 20), 0));
			nodosVacios.remove(nodo);
			
			
			
		}
		iniciarNodosVacios(nodosVacios);
		System.out.println("nodos vacios: " + nodosVacios);		
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
		List<Integer> nodos = List.of(8,11,19,21,29);
		nodos.forEach(n -> {
			pokebolas.put(n, getRandom(min, max));
			percepcionesAmbiente.put(n, PokemonUnitePerception.POKEBOLA_PERCEPTION);
		});
        
	}

	
	//funcion que genera un numero aleatorio en un rango
	private Integer getRandom(Integer min, Integer max) {
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
		return "\n" + "\n" + "--------------------------------------ESTADO AMBIENTE:" + "\n" + 
				mapAdyacencias.toString() + "\n" + "Pokemon agente(pos, energia, nivel): " +  pokemonAgente
				+ "\n" 	+ "Pokemons adversarios(pos, energia, ciclos): " + pokemonsAdversarios
				+ "\n" 	+ "Pokemon Maestro(pos, energia): " + pokemonMaestro
				+ "\n" 	+ "Pokebolas(pos, energia): " + pokebolas
				+ "\n" 	+ "Nodo percepcion: " + percepcionesAmbiente
				+ "\n" 	+ "Cantidad de ciclos desde ultimo uso del satelite: " + cantCiclosDesdeUltimoUsoSatelite
				+ "\n" 	+ "Ataques especiales(nombre, nivel, porcentaje energia): " + mapAtaquesEspeciales;
	}


	public List<List<Integer>> getAdyacencias() {
		//metodo para devolver los nodos adyacentes al pokemon agente.
		// percepcion, nodo, energia(opcional)
		List<Integer> listaNodosAdyacentes = mapAdyacencias.get(pokemonAgente.get(0));
		List<List<Integer>> adyacentes = new ArrayList<>();
		for (Integer nodo : listaNodosAdyacentes) {
			if (pokemonsAdversarios.containsKey(nodo)) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.ENEMIGO_PERCEPTION, nodo, pokemonsAdversarios.get(nodo).get(0)));
			} else if (pokebolas.containsKey(nodo)) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.POKEBOLA_PERCEPTION, nodo, pokebolas.get(nodo)));
			} else if (pokemonMaestro.get(0) == nodo) {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.POKEMON_MAESTRO_PERCEPTION, nodo, pokemonMaestro.get(1)));
			} else {
				adyacentes.add(Arrays.asList(PokemonUnitePerception.EMPTY_PERCEPTION, nodo));
			}
		}
		
		return adyacentes;
	}

	//retorna el ambiente cuando se usa el satelite 
	public PokemonUniteEnvironmentState usoSatelite() {
		if(cantCiclosDesdeUltimoUsoSatelite >= 5 && cantCiclosDesdeUltimoUsoSatelite <= 10) {
			cantCiclosDesdeUltimoUsoSatelite = 0;
			return this;
		}
		return null;
	}
	
	
	//devuelve la energia del agente
	public Integer getAgentEnergy() {
		return pokemonAgente.get(1);
	}

	public List<Integer> getPokemonAgente() {
		return pokemonAgente;
	}


	public void setPokemonAgente(List<Integer> pokemonAgente) {
		this.pokemonAgente = pokemonAgente;
	}


	public Map<Integer, List<Integer>> getMapAdyacencias() {
		return mapAdyacencias;
	}

	public void setMapAdyacencias(Map<Integer, List<Integer>> mapAdyacencias) {
		this.mapAdyacencias = mapAdyacencias;
	}


	public List<Integer> getPokemonMaestro() {
		return pokemonMaestro;
	}


	public void setPokemonMaestro(List<Integer> pokemonMaestro) {
		this.pokemonMaestro = pokemonMaestro;
	}


	public Map<Integer, Integer> getPokebolas() {
		return pokebolas;
	}


	public void setPokebolas(Map<Integer, Integer> pokebolas) {
		this.pokebolas = pokebolas;
	}


	public Map<Integer, List<Integer>> getPokemonsAdversarios() {
		return pokemonsAdversarios;
	}


	public void setPokemonsAdversarios(Map<Integer, List<Integer>> pokemonsAdversarios) {
		this.pokemonsAdversarios = pokemonsAdversarios;
	}


	public Integer getCantCiclosDesdeUltimoUsoSatelite() {
		return cantCiclosDesdeUltimoUsoSatelite;
	}


	public void setCantCiclosDesdeUltimoUsoSatelite(Integer cantCiclosDesdeUltimoUsoSatelite) {
		this.cantCiclosDesdeUltimoUsoSatelite = cantCiclosDesdeUltimoUsoSatelite;
	}


	public Map<String, List<Integer>> getMapAtaquesEspeciales() {
		return mapAtaquesEspeciales;
	}


	public void setMapAtaquesEspeciales(Map<String, List<Integer>> mapAtaquesEspeciales) {
		this.mapAtaquesEspeciales = mapAtaquesEspeciales;
	}


	public void eliminarAdversario(Integer pos) {
		pokemonsAdversarios.remove(pos);
	}


	public void evaluarSubirDeNivel() {
		switch (pokemonAgente.get(2)) {
		case 1: {
			if(pokemonAgente.get(1) >= energiaInicial * 1.25) {
				//ataquesDisponibles.put("Ataque 1", 0);
				pokemonAgente.set(2, 2);
				
			}
		}
		case 2: {
			if(pokemonAgente.get(1) >= energiaInicial * 1.75) {
				//ataquesDisponibles.put("Ataque 2", 0);
				pokemonAgente.set(2, 3);
				
			}
		}
		case 3: {
			if(pokemonAgente.get(1) >= energiaInicial * 2.2) {
				//ataquesDisponibles.put("Ataque 3", 0);
				pokemonAgente.set(2, 4);
				
			}
		}
		}
	}


	public void eliminarPokebola(Integer pos) {
		pokebolas.remove(pos);
		
	}
	
	public void actualizarCicloSatelite(Boolean incrementar) {
		if(incrementar) {
			if(cantCiclosDesdeUltimoUsoSatelite<10) {
				cantCiclosDesdeUltimoUsoSatelite++;
			}
		}
		else cantCiclosDesdeUltimoUsoSatelite = 0;
	}

	public void actualizarNodoPercepcion(Integer nodo, int perception) {
		this.percepcionesAmbiente.replace(nodo, perception);
	}
	
	
	
	
	
	
}
