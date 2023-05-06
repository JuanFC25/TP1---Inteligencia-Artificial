package frsf.ia.search.tp1;

import java.util.List;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;


public class PokemonUniteEnvironment extends Environment{

	
	//CLASE LISTA
	
	public PokemonUniteEnvironment( ) {
		// Create the environment state
		this.environmentState = new PokemonUniteEnvironmentState();
	}
	
	
	@Override
	public PokemonUniteEnvironmentState getEnvironmentState() {
		return (PokemonUniteEnvironmentState) super.getEnvironmentState();
	}
	
	
	
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		PokemonUnitePerception perception = new PokemonUnitePerception();
		perception.setNodosAdyacentes(getAdyacencias());
		
		return perception;
	}

	
	

	public List<List<Integer>> getAdyacencias() {
		return ((PokemonUniteEnvironmentState) this.environmentState).getAdyacencias();
	}

	
	
	@Override
    public boolean agentFailed(Action actionReturned) {

        PokemonUniteEnvironmentState pokemonEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = pokemonEnvironmentState.getAgentEnergy();

        // If the agent has no energy, he failed
        if (agentEnergy <= 0)
            return true;

        return false;
    }
	
	
	@Override
	public String toString() {
	    return environmentState.toString();
	}
}
