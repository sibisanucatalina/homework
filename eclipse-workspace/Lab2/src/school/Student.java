package school;

import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Student extends Agent {

	protected void setup() {

		List<Question> questionList = new ArrayList<>();
//		
//		Question question1 = new Question("3 + 2", 5);
//		Question question2 = new Question("5/1", 5);
//		Question question3 = new Question("2 * 3", 6);
//		Question question4 = new Question("11 + 7", 18);
//		Question question5 = new Question("9-3", 6);
//		Question question6 = new Question("9 * 9", 81);
//		Question question7 = new Question("51 + 42", 93);
//		Question question8 = new Question("7 * 4", 25);
//		Question question9 = new Question("8-4", 4);
//		Question question10 = new Question("10/2", 5);
		
//		 wrong answers to test failure case
		Question question1 = new Question("3 + 2", 5);
		Question question2 = new Question("5/1", 6);
		Question question3 = new Question("2 * 3", 7);
		Question question4 = new Question("11 + 7", 8);
		Question question5 = new Question("9-3", 9);
		Question question6 = new Question("9 * 9", 1);
		Question question7 = new Question("51 + 42", 3);
		Question question8 = new Question("7 * 4", 25);
		Question question9 = new Question("8-4", 4);
		Question question10 = new Question("10/2", 5);
		
		questionList.add(question1);
		questionList.add(question2);
		questionList.add(question3);
		questionList.add(question4);
		questionList.add(question5);
		questionList.add(question6);
		questionList.add(question7);
		questionList.add(question8);
		questionList.add(question9);
		questionList.add(question10);

		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				

				ACLMessage msg = this.myAgent.receive();
				if (msg != null) {
					System.out.println("\nQuestion from teacher: ");
					System.out.println("Message sender:" + msg.getSender().getLocalName());
					System.out.println("Message content (teacher's question):" + msg.getContent());
					
					String questionAnswer = "";
					for(int i =0; i < questionList.size();i++) {
						if(questionList.get(i).getQuestion().equals(msg.getContent())) {
							questionAnswer = String.valueOf(questionList.get(i).getAnswer());
						}
					}
					
					ACLMessage replyAnswerToSend = msg.createReply();
					replyAnswerToSend.setPerformative(ACLMessage.INFORM);
					replyAnswerToSend.setContent(questionAnswer);
					send(replyAnswerToSend);
				} else {
					block();
				}
			}
			
		});
		
	}
	
}
