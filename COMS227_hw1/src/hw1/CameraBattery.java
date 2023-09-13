package hw1;

public class CameraBattery{
	
	/**
	 * @author Reza C 
	 * variable for the charge of the battery
	 */
	private double chargeOfBattery; 
	
	/**
	 * @author Reza C
	 * variable for the setting in which external charges at
	 */
	private int chargeSetting;
	
	/**
	 * @author Reza C
	 * variable for the battery capacity given
	 */
	private double batCap;
	
	/**
	 * @author 
	 * variable for the charge of the camera
	 */
	private double chargeOfCam;
	
	/**
	 * @author Reza C
	 * the amount of drain used in single given drain use
	 */
	private double drain;
	
	/**
	 * @author Reza C
	 * the total amount of drain used
	 */
	private double totalDrain;

	/**
	 * @author Reza C
	 * variable used to determine if the battery is plugged into camera
	 */
	private double chargeYN;
	
	/**
	 * @author Reza C
	 * amount of charger settings
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	
	/**
	 * @author Reza C
	 * rate at which charge happens per min
	 */
	public static final double CHARGE_RATE = 2.0;
	
	/**
	 * @author Reza C
	 * rate at which battery is drained per min
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	/**
	 * @author Reza C
	 * a variable i created to easy access the rate at drain happens
	 */
	private double camPower;
	
	/**
	 * @author Reza C method which takes the given parameters and puts them into variables
	 * @param batteryStartingCharge the charge the battery starts with
	 * @param batteryCapacity the max amount of charge the battery can have
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		batCap = batteryCapacity;
		chargeOfBattery = batteryStartingCharge;
		camPower = DEFAULT_CAMERA_POWER_CONSUMPTION;
		chargeOfBattery = Math.min(batteryStartingCharge, batteryCapacity);
	}
	/**
	 * @author Reza C method which changes the charge setting by adding 1 then modding at max
	 */
	public void buttonPress() {
		chargeSetting += 1;
		chargeSetting %= NUM_CHARGER_SETTINGS;
	}
	/**
	 * @author Reza C calculates the amount the camera and battery have been charged
	 * @param minutes the amount of minutes wanted to charge 
	 * @return the amount of charge that should have happened
	 */
	public double cameraCharge(double minutes) {
		double justCam = 0;
		chargeOfCam = Math.min(batCap, ((chargeYN * minutes * CHARGE_RATE) + chargeOfCam));
		justCam = (CHARGE_RATE * minutes * chargeYN);
		justCam = Math.min(batCap - chargeOfBattery, justCam);
		chargeOfBattery = Math.max(chargeOfBattery, (minutes * CHARGE_RATE));
		chargeOfBattery += justCam;
		chargeOfBattery = Math.min(chargeOfBattery, batCap);
		return justCam;
	}
	/**
	 * @author Reza C calculates the amount the battery should drain and how much the camera should drain
	 * @param minutes amount of minutes drained
	 * @return the charge drained
	 */
	public double drain(double minutes) {
		drain = (minutes * camPower * chargeYN);
		drain = Math.min(drain,chargeOfBattery);
		chargeOfBattery -= (minutes * camPower * chargeYN);
		chargeOfCam = chargeOfBattery * chargeYN;
		totalDrain += drain;
		chargeOfBattery = Math.max(chargeOfBattery, 0);
		chargeOfCam = Math.max(chargeOfCam, 0);
		return drain;
	}
	/**
	 * @author Reza C calculates the charge of the battery using the outer charger
	 * @param minutes amount of minutes wanted to charge
	 * @return the amount of charge gained
	 */
	public double externalCharge(double minutes) {
		double externalChargeWork = 0;
		externalChargeWork = (minutes * (chargeSetting) * CHARGE_RATE);
		externalChargeWork = Math.min(batCap - chargeOfBattery, externalChargeWork);
		chargeOfBattery = Math.max(chargeOfBattery, (minutes * (chargeSetting + 1)* CHARGE_RATE));
		chargeOfBattery += externalChargeWork;
		chargeOfBattery = Math.min(chargeOfBattery, batCap);
		return externalChargeWork;
	}
	
	/**
	 * @author Reza C resets the total drain back to zero
	 */
	public void resetBatteryMonitor() {
		totalDrain = 0;
	}
	
	/**
	 * @author Reza C returns battery capacity
	 * @return battery capacity
	 */
	public double getBatteryCapacity() {
		return batCap;
	}
	/**
	 * @author Reza C returns the charge of battery
	 * @return
	 */
	public double getBatteryCharge() {
		return chargeOfBattery;
	}
	/**
	 * @author Reza C returns the charge of camera
	 * @return
	 */
	public double getCameraCharge() {
		return chargeOfCam;
	}
	/**
	 * @author Reza C returns the rate of power consumption
	 * @return
	 */
	public double getCameraPowerConsumption() {
		return camPower;
	}
	
	/**
	 * @author Reza C returns the charger setting in external
	 * @return
	 */
	public int getChargerSetting() {
		return chargeSetting;
	}
	/**
	 * @author Reza C returns the total drain
	 * @return
	 */
	public double getTotalDrain() {
		return totalDrain;
	}
	/**
	 * @author Reza C acts like moving the battery to the external charger
	 */
	public void moveBatteryExternal() {
		chargeOfCam = 0;
	}
	/**
	 * @author Reza C acts like putting the battery inside the camera
	 */
	public void moveBatteryCamera() {
		chargeOfCam = chargeOfBattery;
		chargeYN = 1;
		
	}
	/**
	 * @author Reza C acts like removing the battery from the camera
	 */
	public void removeBattery() { 
		chargeOfCam = 0;
		chargeYN = 0;
		setCameraPowerConsumption(0);
	}
	/**
	 * @author Reza C sets the power consumption rate with the given parameter
	 * @param cameraPowerConsumption
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		camPower = cameraPowerConsumption;
	}
}