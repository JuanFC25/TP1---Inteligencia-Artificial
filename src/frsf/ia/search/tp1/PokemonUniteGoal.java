package frsf.ia.search.tp1;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PokemonUniteGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
        if (((PokemonUniteAgentState) agentState).isInMasterPokemonPosition() ) {
            return true;
        }
        return false;
	}

}
