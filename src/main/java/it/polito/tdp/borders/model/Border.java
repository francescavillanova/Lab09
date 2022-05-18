package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {

	private int stateUno;
	private int stateDue;
	
	public Border(int stateUno, int stateDue) {
		super();
		this.stateUno = stateUno;
		this.stateDue = stateDue;
	}

	public int getStateUno() {
		return stateUno;
	}

	public void setStateUno(int stateUno) {
		this.stateUno = stateUno;
	}

	public int getStateDue() {
		return stateDue;
	}

	public void setStateDue(int stateDue) {
		this.stateDue = stateDue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stateDue, stateUno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return stateDue == other.stateDue && stateUno == other.stateUno;
	}

	@Override
	public String toString() {
		return stateUno + " - "+ stateDue;
	}
	
	
}
