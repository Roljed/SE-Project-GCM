package command;

import src.product.City;
import src.product.content.ContentInterestingPlaces;

public class Search
{
	private ClientConsole client;
	
	public Search(ClientConsole client_){
		client = client_;
	}
	
	public City searchByCity(String cityName){
		client.handleMessageFromClientUI(cityName);
		return (City)input.readObject();
	}
	
	public PlaceOfInterest SearchByContent(String contentName){
		client.handleMessageFromClientUI(contentName);
		return (PlaceOfInterest)input.readObject();
	}
	
	//TO BE CONTINUED
	
}
