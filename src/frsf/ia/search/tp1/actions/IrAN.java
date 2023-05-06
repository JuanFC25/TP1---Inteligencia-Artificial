package frsf.ia.search.tp1.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;

public class IrAN extends SearchAction {

	private Integer nodo; 
	
	
	
	
	public IrAN(Integer nodo) {
		super();
		this.nodo = nodo;
	}

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		
		Integer nodoActual = pokemonState.
		
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
