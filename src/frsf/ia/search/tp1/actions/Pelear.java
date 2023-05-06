package frsf.ia.search.tp1.actions;

import java.util.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;
import frsf.ia.search.tp1.PokemonUniteEnvironmentState;

public class Pelear extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		
		Integer nodoActual = pokemonState.getNodoPosicion();
		List<Integer> pokemonAdversario = pokemonState.getPokemonsAdversarios().get(nodoActual);
		Integer energia = pokemonState.getEnergia();
		
		if(energia > pokemonAdversario.get(0)) {
			energia += (int) ((pokemonAdversario.get(0) * 0.2) - pokemonAdversario.get(0));
			pokemonState.eliminarAdversario(nodoActual);
			pokemonState.setCantidadAdversarios(pokemonState.getCantidadAdversarios()-1);
			pokemonState.setEnergia(energia);
			pokemonState.evaluarSubirDeNivel();
			
			return pokemonState;
		}
		
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
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
		
		if(energia > pokemonAdversario.get(0)) {
			energia += (int) ((pokemonAdversario.get(0) * 0.2) - pokemonAdversario.get(0));
			pokemonEnvironmentState.eliminarAdversario(pokemonAgente.get(0));
			pokemonState.setCantidadAdversarios(pokemonState.getCantidadAdversarios()-1);
			pokemonState.setEnergia(energia);
			pokemonEnvironmentState.setEnergia(energia);
			pokemonEnvironmentState.evaluarSubirDeNivel();
			
			return pokemonState;
		}
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
