package frsf.ia.search.tp1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class PokemonUniteAgentState extends SearchBasedAgentState{

	
	private Map<Integer, List<Integer>> ambienteAgente; // Map que tiene la lista de nodos y sus adyacencias 
	private Map<Integer, List<Integer>> pokemonsAdversarios;//Lista de que contiene posicion, energia y ciclos sin moverse de los enemigos
	private Integer nodoPosicion;
	private Integer energia;
	private Integer nivel;
	private Integer cantidadAdversarios;
	private Map<String, Integer> ataquesDisponibles;
	private List<Integer> pokemonMaestro;  // Lista que contiene posicion y energia  
	private Integer energiaInicial = 20;
	
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
		
		mapAtaquesEspeciales = new HashMap<String, List<Integer>>();
		
		mapAtaquesEspeciales.put("Ataque 1", Arrays.asList(2, 20));
		mapAtaquesEspeciales.put("Ataque 2", Arrays.asList(3, 30));
		mapAtaquesEspeciales.put("Ataque 3", Arrays.asList(4, 50));
	}
	
	
	public PokemonUniteAgentState() {
		ambienteAgente = new HashMap<>();
		pokemonsAdversarios = new HashMap<>();
		ataquesDisponibles = new HashMap<>();
		nodoPosicion = 1;
		energia = 20;
		nivel = 1;
		cantidadAdversarios = 5; // ver como obtener del ambiente
		
	}
	
	
	@Override
	public SearchBasedAgentState clone() {
		Map<Integer, List<Integer>> newAmbienteAgente = this.ambienteAgente;
		Map<Integer, List<Integer>> newPokemonsAdversarios = this.pokemonsAdversarios;
		Map<String, Integer> newAtaquesDisponibles = this.ataquesDisponibles;
		PokemonUniteAgentState newState = new PokemonUniteAgentState(newAmbienteAgente, newPokemonsAdversarios, this.nodoPosicion, this.energia, this.nivel, newAtaquesDisponibles);
		
		return newState;
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub HACERR
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

	public boolean isInMasterPokemonPosition() {
		return (nodoPosicion == 20);
		
	}

	public boolean isMasterPokemonAlive() {
		// TODO Auto-generated method stub
		return false;
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


	public Map<String, Integer> getataquesDisponibles() {
		return ataquesDisponibles;
	}


	public void setataquesDisponibles(Map<String, Integer> ataquesDisponibles) {
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

	
	
	
	
	
	
	
}
