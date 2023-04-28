package frsf.ia.search.tp1;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokemonUniteGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
        if (((PokemonAgentState) agentState).isInMasterPokemonPosition() &&
                !((PokemonAgentState) agentState).isMasterPokemonAlive()) {
            return true;
        }
        return false;
	}

}
