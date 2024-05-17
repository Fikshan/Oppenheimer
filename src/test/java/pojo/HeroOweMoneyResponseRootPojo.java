package pojo;

public class HeroOweMoneyResponseRootPojo {

	MessagePojo MessageObject;
	private String timestamp;

	// Getter Methods 
	public MessagePojo getMessage() {
		return MessageObject;
	}

	public String getTimestamp() {
		return timestamp;
	}

	// Setter Methods
	public void setMessage(MessagePojo messageObject) {
		this.MessageObject = messageObject;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
