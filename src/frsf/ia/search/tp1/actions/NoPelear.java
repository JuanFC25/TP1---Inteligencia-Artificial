package frsf.ia.search.tp1.actions;

import java.util.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;
import frsf.ia.search.tp1.PokemonUniteEnvironmentState;
import frsf.ia.search.tp1.PokemonUnitePerception;

public class NoPelear extends SearchAction {	
	//CREO QUE ESTA
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		Integer nodoActual = pokemonState.getNodoPosicion();
		List<Integer> pokemonAdversario = pokemonState.getPokemonsAdversarios().get(nodoActual);
		Integer energia = pokemonState.getEnergia();
		
		//sirve para saber si hay un enemigo en el nodo actual, si es null no hay enemigo
		if(pokemonAdversario != null && energia != null) {
			if(energia < pokemonAdversario.get(0)) {
				energia -= (int) ( energia - (pokemonAdversario.get(0) * 0.25)); 
				pokemonState.setEnergia(energia);
				pokemonState.incrementarContadoresAtaquesDisponibles();
				
				return pokemonState;
			}
		}
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) ast;
		PokemonUniteEnvironmentState pokemonEnvironmentState = (PokemonUniteEnvironmentState) est;
		
		//pos, energia, nivel
		List<Integer> pokemonAgente = pokemonEnvironmentState.getPokemonAgente();
		//Lista de que contiene energia y ciclos sin moverse de los enemigos
		List<Integer> pokemonAdversario = pokemonEnvironmentState.getPokemonsAdversarios().get(pokemonAgente.get(0));
				
		Integer energia = pokemonAgente.get(1);
		
		if(energia < pokemonAdversario.get(0)) {
			energia -= (int) ( energia - (pokemonAdversario.get(0) * 0.25)); 
			pokemonState.setEnergia(energia);
			pokemonState.incrementarContadoresAtaquesDisponibles();
			pokemonEnvironmentState.setPokemonAgente(List.of(pokemonAgente.get(0),energia,pokemonAgente.get(2)));
			pokemonEnvironmentState.actualizarCicloSatelite(true);
			
			
			return pokemonEnvironmentState;
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "No pelear";
	}

}
