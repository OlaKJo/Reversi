package project_reversi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agent {
	
	char agentMarker;
	char opponentMarker;
	
	public Agent(char player) {
		agentMarker = player;
		opponentMarker = Util.inversePlayer(agentMarker);
	}
	
	public Tuple chooseMove(Game state, int depth) {
		List<Tuple> validMoves = state.getValidMoves(agentMarker);
		List<Integer> moveFitnesses = new ArrayList<Integer>();
		for(Tuple p : validMoves) {
			Game newState = new Game(state.getBoard(), agentMarker);
			newState.makeMove(p.getX(), p.getY(), agentMarker);
			moveFitnesses.add(minFunc(newState));
		}
		int maxVal = Collections.max(moveFitnesses);
		int index = moveFitnesses.indexOf(Integer.valueOf(maxVal));
		
		return validMoves.get(index);
	}
	
	private int minFunc(Game state) {
		List<Tuple> opponentValidMoves = state.getValidMoves(opponentMarker);
		List<Tuple> AgentValidMoves = state.getValidMoves(opponentMarker);

		List<Integer> moveFitnesses = new ArrayList<Integer>();
		
		if(opponentValidMoves.isEmpty() && AgentValidMoves.isEmpty()) {
			return fitness(state);
		}
		if(opponentValidMoves.isEmpty()) {
			return maxFunc(state);
		}
		
		for(Tuple p : opponentValidMoves) {
			Game newState = new Game(state.getBoard(), opponentMarker);
			newState.makeMove(p.getX(), p.getY(), opponentMarker);
			moveFitnesses.add(maxFunc(newState));
		}
		int minVal = Collections.min(moveFitnesses);
		
		return minVal;
	}
	
	private int maxFunc(Game state) {
		List<Tuple> opponentValidMoves = state.getValidMoves(agentMarker);
		List<Tuple> AgentValidMoves = state.getValidMoves(agentMarker);

		List<Integer> moveFitnesses = new ArrayList<Integer>();
		
		if(AgentValidMoves.isEmpty() && opponentValidMoves.isEmpty()) {
			return fitness(state);
		}
		if(AgentValidMoves.isEmpty()) {
			return maxFunc(state);
		}
		
		for(Tuple p : AgentValidMoves) {
			Game newState = new Game(state.getBoard(), agentMarker);
			newState.makeMove(p.getX(), p.getY(), agentMarker);
			moveFitnesses.add(minFunc(newState));
		}
		int maxVal = Collections.max(moveFitnesses);
		
		return maxVal;
		
	}
	
	//if (depth == 0) {
	//return fitness(state);
	//}
	
	private int fitness(Game state) {
		Tuple scores = state.calculateScore();
		int agentScore, opponentScore;
		
		if (agentMarker == Util.PLAYER1) {
			agentScore = scores.getX();
			opponentScore = scores.getY();
		} else {
			agentScore = scores.getY();
			opponentScore = scores.getX();
		}
		
		return agentScore - opponentScore;
	}
}
