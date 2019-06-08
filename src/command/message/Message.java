package command.message;

public interface Message	// abstract Message class
{
	default void AdvanceNotice(){
		System.out.println("Advanced Notice");
	}
	default void EndSubscription(){
		System.out.println("Your Subscription period has ended");
	}
	default void NewVersion()
	{
		System.out.println("Your Maps have been updated to the new version");
	}
}