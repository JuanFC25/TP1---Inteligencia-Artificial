package frsf.ia.search.tp1.actions;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;
import frsf.ia.search.tp1.PokemonUniteEnvironmentState;
import frsf.ia.search.tp1.PokemonUnitePerception;

public class RecogerEnergiaPokebola extends SearchAction {

	
	//CREO QUE ESTA
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		
		Integer nodoActual = pokemonState.getNodoPosicion();
		Map<Integer, Integer> pokebolas = pokemonState.getPokebolas();
		
		//este if sirve para saber si hay una pokebola en el nodo actual, si es null no hay pokebola
		if ( pokebolas != null && pokebolas.get(nodoActual) != null ) {
			Integer energiaTotal = pokemonState.getEnergia()+pokebolas.get(nodoActual);
			pokemonState.setEnergia(energiaTotal);
			pokemonState.eliminarPokebola(nodoActual);
			pokemonState.evaluarSubirDeNivel();
			pokemonState.incrementarContadoresAtaquesDisponibles();
			
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
		
		Integer energiaPokebola = pokemonEnvironmentState.getPokebolas().get(pokemonAgente.get(0));
		
		
		//este if sirve para saber si hay una pokebola en el nodo actual, si es null no hay pokebola
		if (energiaPokebola != null) {
			Integer energiaTotal = pokemonState.getEnergia()+energiaPokebola;
			pokemonState.setEnergia(energiaTotal);
			pokemonState.eliminarPokebola(pokemonAgente.get(0));
			pokemonState.evaluarSubirDeNivel();
			pokemonState.incrementarContadoresAtaquesDisponibles();
			pokemonEnvironmentState.setPokemonAgente(List.of(pokemonAgente.get(0),energiaTotal,pokemonAgente.get(2)));
			pokemonEnvironmentState.eliminarPokebola(pokemonAgente.get(0));
			pokemonEnvironmentState.evaluarSubirDeNivel();
			pokemonEnvironmentState.actualizarCicloSatelite(true);
			pokemonEnvironmentState.actualizarNodoPercepcion(pokemonAgente.get(0), PokemonUnitePerception.EMPTY_PERCEPTION);
			
			return pokemonEnvironmentState;
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Recoger energia pokebola";
	}

}
