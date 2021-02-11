package frc.libraries.Controls;
import edu.wpi.first.wpilibj.XboxController;

public class XboxArcade extends XboxController
{
	Hand hand;
	public XboxArcade(int position, Hand hand)
	{
		super(position);
		this.hand = hand;
	}
	
	public double[] GetDrive()
	{
		double[] val = new double[2];
		//Left
		val[0] = getX(hand) - getY(hand);
		//Right
		val[1] = getX(hand) + getY(hand);
		
		return val;
	}
	
}
