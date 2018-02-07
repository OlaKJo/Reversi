package project_reversi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agent {
	
	char agentMarker;
	char opponentMarker;
	int thinkTime;
	final int cornerFitness = 10;
	
	public Agent(char player, int time) {
		agentMarker = player;
		opponentMarker = Util.inversePlayer(agentMarker);
		thinkTime = time;
	}
	
	public Tuple chooseMove(Game state) {
		Tuple bestMove;
		int i = 1;
		double startTime = System.currentTimeMillis();
		do {
			bestMove = moveIterator(state, i++);
		} while ((System.currentTimeMillis()) < ((startTime + (double)thinkTime)));
		return bestMove;
	}
	
	private Tuple moveIterator(Game state, int depth) {
		List<Tuple> validMoves = state.getValidMoves(agentMarker);
		List<Integer> moveFitnesses = new ArrayList<Integer>();
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		
		for(Tuple p : validMoves) {
			Game newState = new Game(state.getBoard(), agentMarker);
			newState.makeMove(p.getX(), p.getY(), agentMarker);
			moveFitnesses.add(minFunc(newState, depth - 1, alpha, beta));
		}
		int maxVal = Collections.max(moveFitnesses);
		int index = moveFitnesses.indexOf(Integer.valueOf(maxVal));
		
		return validMoves.get(index);
	}
	
	private int minFunc(Game state, int depth, int alpha, int beta) {
		List<Tuple> opponentValidMoves = state.getValidMoves(opponentMarker);
		List<Tuple> AgentValidMoves = state.getValidMoves(agentMarker);
		
		if((AgentValidMoves.isEmpty() && opponentValidMoves.isEmpty()) || depth == 0) {
			return fitness(state);
		}
		if(opponentValidMoves.isEmpty()) {
			return maxFunc(state, depth - 1, alpha, beta);
		}
		  int v = Integer.MAX_VALUE;

		for(Tuple p : opponentValidMoves) {
			Game newState = new Game(state.getBoard(), opponentMarker);
			newState.makeMove(p.getX(), p.getY(), opponentMarker);
		    v = Math.min(v, maxFunc(newState, depth - 1, alpha, beta));
		    
		    if(v <= alpha)
		    	return v;
		    beta = Math.min(beta,  v);

		}		
		return v;
	}
	
	private int maxFunc(Game state, int depth, int alpha, int beta) {
		  List<Tuple> opponentValidMoves = state.getValidMoves(opponentMarker);
		  List<Tuple> AgentValidMoves = state.getValidMoves(agentMarker);
		  
		  if((AgentValidMoves.isEmpty() && opponentValidMoves.isEmpty()) || depth == 0) {
		    return fitness(state);
		  }
		  if(AgentValidMoves.isEmpty()) {
		    return minFunc(state, depth - 1, alpha, beta);
		  }
		  
		  int v = Integer.MIN_VALUE;
		  for(Tuple p : AgentValidMoves) {
		    Game newState = new Game(state.getBoard(), agentMarker);
		    newState.makeMove(p.getX(), p.getY(), agentMarker);
		    v = Math.max(v, minFunc(newState, depth - 1, alpha, beta));
		    
		    if(v >= beta)
		      return v;
		    alpha = Math.max(alpha, v);
		  }
		  
		  return v;
		}

	
	private int fitness(Game state) {
		Tuple scores = state.calculateScore();
		int agentScore = 0;
		int opponentScore = 0;
		GameMatrix gameMat = state.getGameMatrix();
		
		//Give high scores for capturing corners, penalize losing corners to opponent.
		for(int i = 1; i <= 8; i += 7) {
			for(int j = 1; j <= 8; j += 7) {
				char val = gameMat.getCoord(i, j);
				if(val == agentMarker) {
					agentScore += cornerFitness;
				} else if(val == opponentMarker) {
					opponentScore += cornerFitness;
				}
			}
		}
		
		//Give score for capturing many markers
		if (agentMarker == Util.PLAYER1) {
			agentScore += scores.getX();
			opponentScore += scores.getY();
		} else {
			agentScore += scores.getY();
			opponentScore += scores.getX();
		}
		
		return agentScore - opponentScore;
	}
}
