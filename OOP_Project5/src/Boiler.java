
public class Boiler implements HeatingSystem {
	
	@Override
	public char getEnergyEfficiency(int rooms)
	{
		return House.getEfficiency(rooms, this);
	}

}
