package school;

public class Question {
	public String question;
	public Integer answer;
	
	public Question(String question, Integer answer) {
		this.question = question;
		this.answer = answer;
	}
	
	
	public String getQuestion() {
		return this.question;
	}
	
	public Integer getAnswer() {
		return this.answer;
	}
}
