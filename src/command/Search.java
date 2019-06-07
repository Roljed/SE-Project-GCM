package command;

import product.content.Content;
import product.City;
import client.ClientConsole;
import product.content.Site;


public class Search
{
	private ClientConsole client;
	
	public Search(ClientConsole client_)
	{
		client = client_;
	}
	
	public City searchByCity(String cityName)
	{
		client.handleMessageFromClientUI(cityName);
		return (City)input.readObject();
	}
	
	public Content SearchByContent(String contentName)
	{
		client.handleMessageFromClientUI(contentName);
		return (Content) input.readObject();
	}

    public Site searchByContent(String request)
	{
		client.handleMessageFromClientUI(contentName);
		return (Content) input.readObject();
    }

    //TO BE CONTINUED
	
}
