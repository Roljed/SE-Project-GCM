package command;

public interface Message
{
	default void advanceNotice(){
		System.out.println("Advanced Notice");
	}
	default void endSubscription(){
		System.out.println("Your Subscription period has ended");
	}
	default void newVersion()
	{
		System.out.println("Your Maps have been updated to the new version");
	}
}