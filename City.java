package application;

public class City {
	
	
	 String cityName ;
	 double Latitude;
	 double Longitude;
	
	
	 double X;
	 double Y;

	
	
	
	public City() {
		super();
	}


	public City(String cityName, double latitude, double longitude, double X, double Y) {
		super();
		this.cityName = cityName;
		this.Latitude = latitude;
		this.Longitude = longitude;
		this.X = X;
		this.Y = Y;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public double getLatitude() {
		return Latitude;
	}


	public void setLatitude(double latitude) {
		Latitude = latitude;
	}


	public double getLongitude() {
		return Longitude;
	}


	public void setLongitude(double longitude) {
		Longitude = longitude;
	}


	@Override
	public String toString() {
		return cityName;
	}
	
	
	

	



}
