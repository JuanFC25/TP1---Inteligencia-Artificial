package frsf.ia.search.tp1;

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
	private Map<String, Integer> ataquesEspeciales;
	private List<Integer> pokemonMaestro;  // Lista que contiene posicion y energia  
	
	
	
	
	
	
	
	
	public PokemonUniteAgentState(Map<Integer, List<Integer>> ambienteAgente,
			Map<Integer, List<Integer>> pokemonsAdversarios, Integer nodoPosicion, Integer energia, Integer nivel,
			Map<String, Integer> ataquesEspeciales) {
		super();
		this.ambienteAgente = ambienteAgente;
		this.pokemonsAdversarios = pokemonsAdversarios;
		this.nodoPosicion = nodoPosicion;
		this.energia = energia;
		this.nivel = nivel;
		this.ataquesEspeciales = ataquesEspeciales;
	}
	
	
	public PokemonUniteAgentState() {
		ambienteAgente = new HashMap<>();
		pokemonsAdversarios = new HashMap<>();
		ataquesEspeciales = new HashMap<>();
		nodoPosicion = 1;
		energia = 20;
		nivel = 1;
		cantidadAdversarios = 5; // ver como obtener del ambiente
		
	}
	
	
	@Override
	public SearchBasedAgentState clone() {
		Map<Integer, List<Integer>> newAmbienteAgente = this.ambienteAgente;
		Map<Integer, List<Integer>> newPokemonsAdversarios = this.pokemonsAdversarios;
		Map<String, Integer> newAtaquesEspeciales = this.ataquesEspeciales;
		PokemonUniteAgentState newState = new PokemonUniteAgentState(newAmbienteAgente, newPokemonsAdversarios, this.nodoPosicion, this.energia, this.nivel, newAtaquesEspeciales);
		
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

}
