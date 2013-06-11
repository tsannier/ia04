package fr.utc.ia04.perception;

import sim.util.Double2D;
import fr.utc.ia04.Module;
import fr.utc.ia04.agent.Agent;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public abstract class AbstractPerception extends Module {

	/*
	 * Constructor
	 */
	public AbstractPerception(Human h) {
		super(h);
	}

	/*
	 * Abstract Method
	 */
	public abstract StimulusBag makePerception(Beings beings, double dt);
	
	/*
	 * Method tools
	 */
	public Stimulus detectNearestMapBorder(Beings b){
		double size = SimulationConstants.ENV_SIZE/2;
		Double2D center = new Double2D(size,size);
		Double2D vect = h.getPosition().subtract(center);
		
		// La direction du stimulus
		double dir = vect.angle();
		double l = vect.length();
		
		return new Stimulus(-(l/size)*(l/size), dir);
	}
	
	public double distanceIntensity(Agent o) {
		double d = 1-h.distance(o)/h.getPerceptionSkills();
		return d*d;
	}
}
