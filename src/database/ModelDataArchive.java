package database;

import command.Message;
import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;

import java.util.HashMap;

// Holds all product related data
public class ModelDataArchive implements Message
{
    HashMap<Integer, Content> contentHub;
    HashMap<Integer, DigitalMap> digitalMapHub;
    HashMap<Integer, City> cityHub;
    HashMap<Integer, Tour> tourHub;

}
