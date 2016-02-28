/**
*This is programmed for the lighter tomohawks 
*on the practice robot
*/

package org.usfirst.frc.team5429.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CameraServer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	final String defaultAuto = "Default";
    final String RoughTerrainAuto = "RoughTerrainAuto";
    final String RampartsAuto = "RampartsAuto";
    
    String autoSelected;
    SendableChooser chooser;
    CANTalon _frontLeftMotor = new CANTalon(0); 		/* device IDs here (1 of 2) */
	CANTalon _rearLeftMotor = new CANTalon(1);
	CANTalon _frontRightMotor = new CANTalon(2);
	CANTalon _rearRightMotor = new CANTalon(3);
	//CameraServer.StartAutomaticCapture();
	/* extra talons for six motor drives */
	CANTalon _intake = new CANTalon(15);
	CANTalon _tomohawk = new CANTalon(14);
	
	RobotDrive _drive = new RobotDrive(_frontLeftMotor, _rearLeftMotor, _frontRightMotor, _rearRightMotor);
	
	Joystick _joy = new Joystick(0);
	Joystick _joy2 = new Joystick(1);
	Joystick _xBoxController = new Joystick(2);
	CameraServer server = CameraServer.getInstance();
	//server.setQuality(50);
//	stry.startAutomaticCapture();
	
	// Autonomous Variables
	int autoCounter = 0;
	int autoState = 0;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("Rough Terrain", RoughTerrainAuto);
        chooser.addObject("Ramparts", RampartsAuto);
        
        SmartDashboard.putData("Auto choices", chooser);
        //_leftSlave.changeControlMode(TalonControlMode.Follower);
    	//_rightSlave.changeControlMode(TalonControlMode.Follower);
    	//_leftSlave.set(11); 							/* device IDs here (2 of 2) */
    	//_rightSlave.set(14);
    
    		
    	
    	
    	server.startAutomaticCapture();
    	/* the Talons on the left-side of my robot needs to drive reverse(red) to move robot forward.
    	 * Since _leftSlave just follows frontLeftMotor, no need to invert it anywhere. */
    	_drive.setInvertedMotor(MotorType.kFrontLeft, true);
    	_drive.setInvertedMotor(MotorType.kRearLeft, true);

    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoCounter = 0;
    	autoState = 0;
    	
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    public void Run_Rough_Terrain_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		
    		_rearRightMotor.set(-.52);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.50);;
    		_frontLeftMotor.set(-.50);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 98.7)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(.30);;  // breaking for after rough terrain
    		_rearLeftMotor.set(.30);;
    		_frontRightMotor.set(.30);;
    		_frontLeftMotor.set(.30);;
    		if(autoCounter >= 25)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    		break;
    		
    	}
    		
    
    
    	
    }

    public void Run_Ramparts_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// drive toward ramparts
    		
    		_rearRightMotor.set(-.55);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.50);;
    		_frontLeftMotor.set(-.50);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 680)
    		{
    			autoState = 1;
    			autoCounter = 0;
    		}
    		break;
    		
    	case 1:
    	
    		_rearRightMotor.set(-.65);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.55);;
    		_frontLeftMotor.set(-.50);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 1500)
    		{
    			autoState = 2;
    			autoCounter = 0;
    		}
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    	}
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	autoCounter++;
       	switch(autoSelected) {
       	case RoughTerrainAuto:
    		Run_Rough_Terrain_Auto();
    		break;
    		
     	case RampartsAuto:
    		Run_Ramparts_Auto();
        	break;
        	
       	}
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double left = _joy.getRawAxis(1); // logitech gampad left X, positive is forward
    	double right = _joy2.getRawAxis(1); //logitech gampad right X, positive means turn right
    	_drive.tankDrive(left, -right);//(forward, turn);
    	if(_xBoxController.getRawAxis(2) == 1)
    	{
    		//intake
    		_intake.set(1.0);
    	}
    	else if(_xBoxController.getRawAxis(3) == 1)
    	{
    		//outake
          _intake.set(-1.0);
    	}
    	else
    	{
    	 //turn off	
    		_intake.set(0);
    	}
    	if(_xBoxController.getRawButton(5)){
    		//forward _tomohawk
    		_tomohawk.set(0.5);
    	}
    	else if(_xBoxController.getRawButton(6))
    	{
    		//reverse _tomohawk
    		_tomohawk.set(-0.35);
    	}
    	else
    	{
    		//turn off
    	  _tomohawk.set(0);	
    	}
    	}
    	//LiveWindow.run();
    

    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
