package fr.utc.ia04.behaviour;

import fr.utc.ia04.agent.Human;
import fr.utc.ia04.simulation.Beings;
import fr.utc.ia04.simulation.SimulationConstants;

public class SpeakBehaviour extends ProximityBehaviour {
	
	protected Human other = null;
	protected int nbStep = 0 ;
	
	public SpeakBehaviour(Human h, Human other) {
		super(h, SimulationConstants.STATE_SPEAKING, other, SimulationConstants.DIST_INTERACT);
		this.other = other;
	}
	
	@Override
	public void doAction(Beings b, double dt) {
		h.setSocial(h.getSocial()+dt); // TODO régler le coeff
		// TODO partager les infos sur les vampires
		nbStep++;
		
		int probability;
		
		if (nbStep >=10){
			//Si plus de 10 steps à parler, la proba d'échanger des infos est de 1;
			probability = 1;
		}
		else{
			//Si moins de 10 steps à parler, la proba est de step/10 (cad 20% si step 2, 40 si step 4)
			probability = nbStep/10;
		}
		
		
		if (Math.random() < probability && h.knowSomeone()) //Si l'acteur connaît au moins un vampire et que la proba est respectée alors l'acteur échange des infos
		{
				Human vamp = h.pickARandomKnownVampire();
				this.other.addKnownVampire(vamp);
		}
		
		
		
	}

	@Override
	public double evalGain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isDone() {
		return h.getSocial() >= SimulationConstants.CHAR_MAX_SOCIAL;
	}

}
