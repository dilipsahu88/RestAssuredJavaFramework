package demopack;

public class AusterelianTraffic implements CentralTraffic, ContinentalTraffic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CentralTraffic ct = new AusterelianTraffic();
		ct.redStop();
		ct.greenGo();
		ct.flashYellow();
		
		
		AusterelianTraffic at = new AusterelianTraffic();
		at.handSignal();
		
		ContinentalTraffic cst = new AusterelianTraffic();
		
		cst.TrainSymbol();
		
	}

	@Override
	public void greenGo() {
		// TODO Auto-generated method stub
		System.out.println("When green is displayed get ready to GO");
		
	}
	
	public void handSignal()
	{
		System.out.println("When you see the sign stop before you cross the road");
	}

	@Override
	public void redStop() {
		// TODO Auto-generated method stub
		System.out.println("When red is displayed , you must STOP");
		
	}

	@Override
	public void flashYellow() {
		// TODO Auto-generated method stub
		System.out.println("When yellow is displayed get ready to STOP");
		
	}

	@Override
	public void TrainSymbol() {
		// TODO Auto-generated method stub
		System.out.println("when you see the sysmbol watchout from the train before you cross");
		
	}

}
