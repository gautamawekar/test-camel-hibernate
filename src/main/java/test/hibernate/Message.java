package test.hibernate;

public class Message {
	private Long id;
	private String message;
	private Message nextMessage;
	
	public Message(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Message getNextMessage() {
		return nextMessage;
	}
	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
}
