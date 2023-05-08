package frsf.ia.search.tp1.actions;

import java.util.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;
import frsf.ia.search.tp1.PokemonUniteEnvironmentState;

public class IrAN extends SearchAction {

	private Integer nodo; 
	
	public IrAN(Integer nodo) {
		super();
		this.nodo = nodo;
	}

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		
		
		
		List<Integer> nodosAdyacentes = pokemonState.getAmbienteAgente().get(pokemonState.getNodoPosicion());
		
		System.out.println("NODO POSICION AGENTE:" + pokemonState.getNodoPosicion());
		System.out.println("NODOS ADYACENTES: " + nodosAdyacentes);
	
		if(nodosAdyacentes!= null && nodosAdyacentes.contains(nodo)) {
			System.out.println("ESTOY ACA");
			pokemonState.setNodoPosicion(this.nodo);
			pokemonState.getAmbienteAgente();
			pokemonState.incrementarContadoresAtaquesDisponibles();
			System.out.println(pokemonState.toString());
			
			return pokemonState;
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
		//posicion de los nodos adyacentes
		List<Integer> nodosAdyacentes = pokemonEnvironmentState.getAdyacencias().get(pokemonAgente.get(0));
		System.out.println("ENVIRONMENT STATE:  " + pokemonEnvironmentState.toString()  + "\n" + "AGENTE STATE:  " + pokemonState.toString());
		if(nodosAdyacentes.contains(nodo)) {
			pokemonState.setNodoPosicion(nodo);
			pokemonEnvironmentState.setPokemonAgente(List.of(nodo, pokemonAgente.get(1), pokemonAgente.get(2)));
			pokemonEnvironmentState.actualizarCicloSatelite(true);
			pokemonState.incrementarContadoresAtaquesDisponibles();
			
			
			
			return pokemonEnvironmentState;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Ir a N"+nodo;
	}
	

}
