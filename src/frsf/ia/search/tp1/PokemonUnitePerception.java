package frsf.ia.search.tp1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokemonUnitePerception extends Perception {

	public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ENEMIGO_PERCEPTION = 1;
    public static int POKEMON_MAESTRO_PERCEPTION = 2;
    public static int POKEBOLA_PERCEPTION = 3;
    public static int SATELITE_PERCEPTION = 4;
    
    PokemonUniteEnvironment pokemonEnvironment = new PokemonUniteEnvironment();
    private List<List<Integer>> nodosAdyacentes;
    private Map<Integer, List<Integer>> mapAdyacencias;
    private Integer energy;  //preguntar
	
    public PokemonUnitePerception() {energy = 20;}
	
	public PokemonUnitePerception(Agent agent, Environment environment) {
        super(agent, environment);
        System.out.println("INIT PERCEPTION: ESTOY ACA 2" );
	}
	
	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		PokemonUniteAgent pokemonAgent = (PokemonUniteAgent) agent;
		pokemonEnvironment = (PokemonUniteEnvironment) environment;
		PokemonUniteEnvironmentState pokemonEnvironmentState = pokemonEnvironment.getEnvironmentState();
		
		this.setNodosAdyacentes(pokemonEnvironment.getAdyacencias());
		System.out.println("INIT PERCEPTION: ESTOY ACA" );
		//VER QUE PONER ACA
		this.setMapAdyacencias(new HashMap<Integer, List<Integer>>());
		
	}

	
	public List<List<Integer>> getNodosAdyacentes() {
		return nodosAdyacentes;
	}

	public void setNodosAdyacentes(List<List<Integer>> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}

	public Map<Integer, List<Integer>> getMapAdyacencias() {
		return mapAdyacencias;
	}

	public void setMapAdyacencias(Map<Integer, List<Integer>> mapAdyacencias) {
		this.mapAdyacencias = mapAdyacencias;
	}
	
	 @Override
	    public String toString() {
	        return "NODOS ADYACENTES(Percepcion, nodo)" + nodosAdyacentes;
	    }

}
