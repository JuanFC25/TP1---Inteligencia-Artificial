package frsf.ia.search.tp1.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.tp1.PokemonUniteAgentState;
import frsf.ia.search.tp1.PokemonUniteEnvironmentState;
import frsf.ia.search.tp1.PokemonUnitePerception;

public class PelearConAtaqueEspecial extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonUniteAgentState pokemonState = (PokemonUniteAgentState) s;
		
		Integer nodoActual = pokemonState.getNodoPosicion();
		List<Integer> pokemonAdversario = pokemonState.getPokemonsAdversarios().get(nodoActual);
		Integer energia = pokemonState.getEnergia();
		Map<String, Integer> ataquesDisponibles = pokemonState.getAtaquesDisponibles();
		Integer energiaTemporal;
		
		if(pokemonAdversario != null && energia != null) {
			if(energia < pokemonAdversario.get(0) && ataquesDisponibles.size() > 0) {
				for (int i = 0 ; i<ataquesDisponibles.size() ; i++) {
					if(ataquesDisponibles.get("Ataque " + (i+1)) >= 3){
						
						energiaTemporal =(int) energia + energia * (pokemonState.getMapAtaquesEspeciales().get("Ataque " + (i+1)).get(1))/100;
						if(energiaTemporal > pokemonAdversario.get(0)) {
							pokemonState.eliminarAdversario(nodoActual);
							pokemonState.setCantidadAdversarios(pokemonState.getCantidadAdversarios()-1);
							energia = (int)(energiaTemporal + pokemonAdversario.get(0) * 0.2 - pokemonAdversario.get(0));
							pokemonState.setEnergia(energia);
							pokemonState.evaluarSubirDeNivel();
							pokemonState.incrementarContadoresAtaquesDisponibles();
							pokemonState.actualizarContadorAtaquesDisponibles("Ataque " + (i+1), 0);
							
							return pokemonState;
						}
					}
				}
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
		Map<String, Integer> ataquesDisponibles = pokemonState.getAtaquesDisponibles();
		Integer energiaTemporal;
		
		
		
		if(pokemonAgente.get(1) != null && pokemonAgente.get(1) < pokemonAdversario.get(0) && ataquesDisponibles.size() > 0) {
			for (int i = 0 ; i<ataquesDisponibles.size() ; i++) {
				if(ataquesDisponibles.get("Ataque " + (i+1)) >= 3){
					
					energiaTemporal =(int) pokemonAgente.get(1) + pokemonAgente.get(1) * (pokemonState.getMapAtaquesEspeciales().get("Ataque " + (i+1)).get(1))/100;
					if(energiaTemporal > pokemonAdversario.get(0)) {
						pokemonState.eliminarAdversario(pokemonAgente.get(0));
						pokemonState.setCantidadAdversarios(pokemonState.getCantidadAdversarios()-1);
						Integer energia = (int)(energiaTemporal + pokemonAdversario.get(0) * 0.2 - pokemonAdversario.get(0));
						pokemonState.setEnergia(energia);
						pokemonState.incrementarContadoresAtaquesDisponibles();
						pokemonState.actualizarContadorAtaquesDisponibles(("Ataque " + (i+1)), 0);
						pokemonState.evaluarSubirDeNivel();
						pokemonEnvironmentState.actualizarNodoPercepcion(pokemonAgente.get(0), PokemonUnitePerception.EMPTY_PERCEPTION);
						pokemonEnvironmentState.eliminarAdversario(pokemonAgente.get(0));
						pokemonEnvironmentState.setPokemonAgente(List.of(pokemonAgente.get(0),energia,pokemonAgente.get(2)));
						
						pokemonEnvironmentState.actualizarCicloSatelite(true);
						return pokemonEnvironmentState;
					}
				}
			}
			
		}
		return null;
	}

	@Override
	public String toString() {
		return "Pelear con ataques especiales";
	}

}
