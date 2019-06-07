package command;

import product.content.Content;
import product.City;

import java.io.IOException;

import client.ChatClient;
import client.ClientConsole;
import product.content.Content;


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

    //TO BE CONTINUED
	
}
