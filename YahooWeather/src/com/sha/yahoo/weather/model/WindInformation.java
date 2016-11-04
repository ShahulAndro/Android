package com.sha.yahoo.weather.model;

/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class WindInformation {
	
		
		private double speed;
		private String direction="";
		
		public WindInformation() {
			// TODO Auto-generated constructor stub
		}

		public double getSpeed() {
			return speed;
		}

		public void setSpeed(double speed) {
			this.speed = speed;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}
		
}
