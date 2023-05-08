package frsf.ia.search.tp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * @author juank
 *
 */
public class PokemonUniteAgentState extends SearchBasedAgentState{

	
	private Map<Integer, List<Integer>> ambienteAgente; // Map que tiene la lista de nodos y sus adyacencias 
	private Map<Integer, Integer> percepcionesAmbiente;
	private Map<Integer, List<Integer>> pokemonsAdversarios;//Lista de que contiene posicion, energia y ciclos sin moverse de los enemigos
	private Map<Integer, Integer> pokebolas;  //Map que contiene posicion y energia de las pokebolas  
	private Integer nodoPosicion;
	private Integer energia;
	private Integer nivel;
	private Integer cantidadAdversarios;
	private Map<String, Integer> ataquesDisponibles; // nombre y ciclos desde ultimo uso
	private List<Integer> pokemonMaestro;  // Lista que contiene posicion y energia  
	private Integer energiaInicial = 20;
	
	//Map de ataques especiales con nombre, nivel y energia  
	private Map<String, List<Integer>> mapAtaquesEspeciales;
	
	public PokemonUniteAgentState(Map<Integer, List<Integer>> ambienteAgente,
			Map<Integer, List<Integer>> pokemonsAdversarios, Integer nodoPosicion, Integer energia, Integer nivel,
			Map<String, Integer> ataquesDisponibles) {
		super();
		this.ambienteAgente = ambienteAgente;
		this.pokemonsAdversarios = pokemonsAdversarios;
		this.nodoPosicion = nodoPosicion;
		this.energia = energia;
		this.nivel = nivel;
		this.ataquesDisponibles = ataquesDisponibles;
		cantidadAdversarios = 5; // ver como obtener del ambiente
		mapAtaquesEspeciales = new HashMap<String, List<Integer>>();
		
		mapAtaquesEspeciales.put("Ataque 1", Arrays.asList(2, 20));
		mapAtaquesEspeciales.put("Ataque 2", Arrays.asList(3, 30));
		mapAtaquesEspeciales.put("Ataque 3", Arrays.asList(4, 50));
	}
	
	
	public PokemonUniteAgentState() {
		ambienteAgente = new HashMap<>();
		pokemonsAdversarios = new HashMap<>();
		ataquesDisponibles = new HashMap<>();
		percepcionesAmbiente = new HashMap();
		pokebolas  = new HashMap();
		nodoPosicion = 1;
		energia = 20;
		nivel = 1;
		cantidadAdversarios = 5; // ver como obtener del ambiente
		this.initState();
	}
	
	@Override
	public void initState() {
		percepcionesAmbiente.put(1, PokemonUnitePerception.EMPTY_PERCEPTION);
		for(int i=2; i<=29; i++) {
			percepcionesAmbiente.put(i, PokemonUnitePerception.UNKNOWN_PERCEPTION);
		}
	}
	
	@Override
	public void updateState(Perception p) {
		PokemonUnitePerception pokemonPerception = (PokemonUnitePerception) p;
		
		int posicion = this.nodoPosicion;
		List<List<Integer>> infoAdyacentes =  pokemonPerception.getNodosAdyacentes(); //lista con percepcion, nodo, energia(opcional)
		System.out.println("POKEMON UNITED AGENT STATE | NODOS ADYACENTES: " + infoAdyacentes);
		
		List<Integer> nodosAdyacentes = new ArrayList<>(); //nodo
		infoAdyacentes.forEach(i -> {
			nodosAdyacentes.add(i.get(1));
			percepcionesAmbiente.replace(i.get(1), i.get(0));
			
			//ver id enemigo cuando se mueven
			if (i.get(0) == PokemonUnitePerception.ENEMIGO_PERCEPTION) {
				this.pokemonsAdversarios.put(i.get(1),List.of(i.get(2)));
			} else if(i.get(0) == PokemonUnitePerception.POKEMON_MAESTRO_PERCEPTION) {
				this.pokemonMaestro = List.of(i.get(1),i.get(2));
			}else if(i.get(0) == PokemonUnitePerception.POKEBOLA_PERCEPTION) {
				this.pokebolas.put(i.get(1),i.get(2));
			}else if(i.get(0) == PokemonUnitePerception.EMPTY_PERCEPTION) {
				if(pokemonsAdversarios.containsKey(i.get(1))) {
					pokemonsAdversarios.remove(i.get(1));
				} else if(pokebolas.containsKey(i.get(1))) {
					pokebolas.remove(i.get(1));
				}
			}
		});
		
		System.out.println("POKEMON UNITED AGENT STATE | NODOS ADYACENTES: " + percepcionesAmbiente);
		this.ambienteAgente.put(posicion, nodosAdyacentes);
		System.out.println("AMBIENTE AGENTE: "+ ambienteAgente);
	}
	
	@Override
	public SearchBasedAgentState clone() {
		Map<Integer, List<Integer>> newAmbienteAgente = this.ambienteAgente;
		Map<Integer, List<Integer>> newPokemonsAdversarios = this.pokemonsAdversarios;
		Map<String, Integer> newAtaquesDisponibles = this.ataquesDisponibles;
		PokemonUniteAgentState newState = new PokemonUniteAgentState(newAmbienteAgente, newPokemonsAdversarios, this.nodoPosicion, this.energia, this.nivel, newAtaquesDisponibles);
		
		return newState;
	}
	
	 /**
	 * This method is used in the search process to verify if the node already
	 * exists in the actual search.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PokemonUniteAgentState))
			return false;
		
		int positionObj = ((PokemonUniteAgentState) obj).getNodoPosicion();
		if(positionObj != this.nodoPosicion) {
			return false;
		}
		
		Map<Integer, List<Integer>> ambienteObj = ((PokemonUniteAgentState) obj).getAmbienteAgente();
		if(ambienteObj != this.ambienteAgente) {
			return false;
		}
				
		return true;
	}

	@Override
	public String toString() {
		return  "\n" + "\n" + "-------------------------------------------ESTADO AGENTE:" + "\n" + 
				ambienteAgente.toString() + "\n" + "Pokemon agente(pos, energia, nivel): [" + nodoPosicion+", " + energia+", " + nivel+"]"
				+ "\n" 	+ "Cantidad adversarios: " + cantidadAdversarios
				+ "\n" 	+ "Pokemons adversarios(pos, energia, ciclos): " + pokemonsAdversarios
				+ "\n" 	+ "Pokemon Maestro(pos, energia): " + pokemonMaestro
				+ "\n" 	+ "Pokebolas(pos, energia): " + pokebolas
				+ "\n" 	+ "Nodo percepcion: " + percepcionesAmbiente
				+ "\n" 	+ "Ataques disponibles(nombre, ciclos): " + ataquesDisponibles;
	}


	public boolean isInMasterPokemonPosition() {
		return (nodoPosicion == 20);
		
	}

	//Se obtiene la percepcion del nodo del pokemon maestro (n20). Si esta es empty significa que ya murio, sino sigue vivo (unknown o masterpokemon)
	public boolean isMasterPokemonAlive() {
		return percepcionesAmbiente.get(20) != PokemonUnitePerception.EMPTY_PERCEPTION;
	}
	
	public Map<Integer, List<Integer>> getAmbienteAgente() {
		return ambienteAgente;
	}


	public void setAmbienteAgente(Map<Integer, List<Integer>> ambienteAgente) {
		this.ambienteAgente = ambienteAgente;
	}


	public Map<Integer, List<Integer>> getPokemonsAdversarios() {
		return pokemonsAdversarios;
	}


	public void setPokemonsAdversarios(Map<Integer, List<Integer>> pokemonsAdversarios) {
		this.pokemonsAdversarios = pokemonsAdversarios;
	}


	public Integer getNodoPosicion() {
		return nodoPosicion;
	}


	public void setNodoPosicion(Integer nodoPosicion) {
		this.nodoPosicion = nodoPosicion;
	}


	public Integer getEnergia() {
		return energia;
	}


	public void setEnergia(Integer energia) {
		this.energia = energia;
	}


	public Integer getNivel() {
		return nivel;
	}


	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}


	public Integer getCantidadAdversarios() {
		return cantidadAdversarios;
	}


	public void setCantidadAdversarios(Integer cantidadAdversarios) {
		this.cantidadAdversarios = cantidadAdversarios;
	}


	public Map<String, Integer> getAtaquesDisponibles() {
		return ataquesDisponibles;
	}


	public void setAtaquesDisponibles(Map<String, Integer> ataquesDisponibles) {
		this.ataquesDisponibles = ataquesDisponibles;
	}


	public List<Integer> getPokemonMaestro() {
		return pokemonMaestro;
	}


	public void setPokemonMaestro(List<Integer> pokemonMaestro) {
		this.pokemonMaestro = pokemonMaestro;
	}


	public void eliminarAdversario(Integer nodoActual) {	
		pokemonsAdversarios.remove(nodoActual);
	}


	public void evaluarSubirDeNivel() {
		
		switch (nivel) {
		case 1: {
			if(energia >= energiaInicial * 1.25) {
				ataquesDisponibles.put("Ataque 1", 0);
				nivel = 2;
			}
		}
		case 2: {
			if(energia >= energiaInicial * 1.75) {
				ataquesDisponibles.put("Ataque 2", 0);
				nivel = 3;
			}
		}
		case 3: {
			if(energia >= energiaInicial * 2.2) {
				ataquesDisponibles.put("Ataque 3", 0);
				nivel = 4;
			}
		}
		}
	}


	public Map<Integer, Integer> getPokebolas() {
		return pokebolas;
	}


	public void setPokebolas(Map<Integer, Integer> pokebolas) {
		this.pokebolas = pokebolas;
	}


	public void eliminarPokebola(Integer nodoActual) {
		pokebolas.remove(nodoActual);
		
	}
	
	public void actualizarContadorAtaquesDisponibles(String key, Integer value) {
		ataquesDisponibles.replace(key, value);
	}
	
	public void incrementarContadoresAtaquesDisponibles() {
		Set<String> nombresAtaques = ataquesDisponibles.keySet();
		nombresAtaques.forEach(a -> ataquesDisponibles.replace(a, ataquesDisponibles.get(a)+1));
	}


	public Map<String, List<Integer>> getMapAtaquesEspeciales() {
		return mapAtaquesEspeciales;
	}


	public void setMapAtaquesEspeciales(Map<String, List<Integer>> mapAtaquesEspeciales) {
		this.mapAtaquesEspeciales = mapAtaquesEspeciales;
	}


	

	
	
	
	
	
	
	
}
