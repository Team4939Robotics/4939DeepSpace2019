import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Ultrasonic;

public class RobotTemplate extends SampleRobot {

	Ultrasonic ultra = new Ultrasonic(1,1); // creates the ultra object and assigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for 
        // the echo pulse and DigitalInput 1 for the trigger pulse
    public void robotInit() {
    	ultra.setAutomaticMode(true); // turns on automatic mode
    }

    public void ultrasonicSample() {
    	double range = ultra.getRangeInches(); // reads the range on the ultrasonic sensor

    //public class getDistance(double inches, double voltage) scale/sensitivity factor calculation, distance conversion

    }
