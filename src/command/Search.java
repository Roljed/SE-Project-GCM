package command;

import product.content.Content;
import product.City;
import product.DigitalMap;
import java.io.IOException;
import java.util.List;
import client.ChatClient;


public class Search
{
	private ChatClient chat;

	public Search(ChatClient chat_)
	{
		chat = chat_;
	}

	public City searchByCity(String cityName)
	{
		try {
			chat.sendToServer(cityName);
			return (City)chat.recieveObjectFromServer();
		}
		catch(IOException ex) {
			return null;
		}
	}

	public Content searchByContent(String contentName)
	{
		try {
			chat.sendToServer(contentName);
			return (Content)chat.recieveObjectFromServer();
		}
		catch(IOException ex) {
			return null;
		}
	}

	public City searchByDetails(String detail) {
		try {
			chat.sendToServer(detail);
			return (City)chat.recieveObjectFromServer();
		}
		catch(IOException ex) {
			return null;
		}
	}
	public void displayCityMaps(int cityID) {
		try {
			chat.sendToServer(cityID);
			List<DigitalMap> cityMaps = (List<DigitalMap>)chat.recieveObjectFromServer();
			for (DigitalMap map : cityMaps)
			{
				System.out.println("\tMap ID: " + map.getDigitalMapID() + "\n\tMap Description: \n\t\t" + map.getDigitalMapDescription() + ".\n");
			}
		}
		catch(IOException ex) {
			return;
		}
	}

}
