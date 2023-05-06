package frsf.ia.search.tp1;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PokemonUniteMain {
	public static void main(String[] args) throws PrologConnectorException {
        PokemonUniteAgent pokemonAgent = new PokemonUniteAgent();
        
        PokemonUniteEnvironment pokemonEnvironment = new PokemonUniteEnvironment();
        
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(pokemonEnvironment, pokemonAgent);
        
        simulator.start();
    }
}
