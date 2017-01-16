package acs.ssa.mpsit.service.util;

import java.util.Date;

import acs.ssa.mpsit.dto.CoffeeMachine;

public class CoffeeMachineExecution implements Runnable {

	private CoffeeMachine coffeeMachine;

	public CoffeeMachineExecution(CoffeeMachine coffeeMachine) {
		this.coffeeMachine = coffeeMachine;
	}

	@Override
	public void run() {

		while(true) {

			if(coffeeMachine.getStatus() && coffeeMachine.getBrewingTime().after(new Date())) {
				coffeeMachine.setStatus(false);
				coffeeMachine.setBrewingTime(new Date());
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
