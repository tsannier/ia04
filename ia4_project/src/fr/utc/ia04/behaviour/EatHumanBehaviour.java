package fr.utc.ia04.behaviour;

import sim.util.Bag;
import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class EatHumanBehaviour extends ProximityBehaviour {

	protected Human other;
	protected double chanceToGetVampire;
	
	public EatHumanBehaviour(Human h, Human o) {
		super(h, SimulationConstants.STATE_EATINGHUM, o, SimulationConstants.DIST_INTERACT);
		chanceToGetVampire = Math.random();
		this.other = o;
	}
	
	@Override
	public boolean preCond() {
		return (h.isVampire() && !other.isVampire());
	}

	@Override
	public void doAction(Beings b, double dt) {
		// TODO fixer coeffs
		other.setEnergy(other.getEnergy()-dt*SimulationConstants.VAMPIRE_ATTAK);  // décrémente l'énergy de l'humain
		h.setEnergy(h.getEnergy()+dt*SimulationConstants.VAMPIRE_REGEN);			// monte l'énergy du vampire
		
		Bag bag = b.yard.getNeighborsWithinDistance(h.getPosition(), SimulationConstants.DIST_MEDIUM);
		for (Object o : bag) {
			if (o instanceof Human) {
				((Human)o).addKnownVampire(h);
			}
		}
		if (this.chanceToGetVampire <= 0.1){
			other.makeVampire();
		}
		
	}

	@Override
	public double evalGain() {
		return SimulationConstants.GAIN_MAX;
	}
	
	@Override
	public boolean isDone() {
		return (h.getPrioCoefEnergy() <= 0.1 || other.isVampire() || other.getEnergy()<=0);
	}

}
