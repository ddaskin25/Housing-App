
public class OwnerOccupiedHouse extends House {

	public OwnerOccupiedHouse(Address address, int nRooms, int salePrice, boolean available, HeatingSystem heatingSystem) {
		super(address, nRooms, salePrice, available, heatingSystem);
		
	}


	@Override
	public String getAvailibilityText() {
		
		if(this.isAvailable())
		{
			return "FOR SALE";
		}
		return "SOLD";
	}

}
