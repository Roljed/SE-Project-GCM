package user.manager;

import product.content.Editor;

public class CompanyManager extends Manager
{
	
	public CompanyManager(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ClientConsole client_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
	}
	
	public void approveMapCost(MapsCost mapCost,int mapID){
		Scanner in = new Scanner(System.in);
		if(mapCost.getMapID() != mapID){
			System.out.println("Yout map no. is wrong. please check it and try again!");
			return;
		}
		while(1){
			System.out.println("Here are the details of the map that you wish to update:");
			mapCost.showDetails();
			System.out.println("Are you sure about the update? if you do, type 'Y'. Otherwise, type 'N':");
			String ans = in.nextLine();
			if(ans.equals("Y")){
				mapCost.approve();
				System.out.println("The price has been updated.");
				return;
			}
			if(ans.equals("N")){
				System.out.println("The price has not been updated.");
				return;
			}
			System.out.println("Your answer is incorrect. Please try again.");
		}
	}
}
