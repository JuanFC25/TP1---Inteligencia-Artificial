package frsf.ia.search.tp1;

import java.util.List;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokemonUniteEnvironment extends Environment{

	
	public PokemonUniteEnvironment( ) {
		// Create the environment state
		this.environmentState = new PokemonUniteEnvironmentState();
	}
	
	
	public PokemonUniteEnvironmentState getEnvironmentState() {
		return (PokemonUniteEnvironmentState) super.getEnvironmentState();
	}
	
	
	
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<List<Integer>> getAdyacencias() {
		// TODO Auto-generated method stub
		return ((PokemonUniteEnvironmentState) this.environmentState).getAdyacencias();
	}

	
}
