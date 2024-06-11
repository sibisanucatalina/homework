package school;

import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Teacher extends Agent{
protected void setup() {
		
		List<Question> questionList = new ArrayList<>();
		
		Question question1 = new Question("3 + 2", 5);
		Question question2 = new Question("5/1", 5);
		Question question3 = new Question("2 * 3", 6);
		Question question4 = new Question("11 + 7", 18);
		Question question5 = new Question("9-3", 6);
		Question question6 = new Question("9 * 9", 81);
		Question question7 = new Question("51 + 42", 93);
		Question question8 = new Question("7 * 4", 24);
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
		
		Integer score = 0;
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		 msg.addReceiver(new AID("student",AID.ISLOCALNAME));
		 for(int i = 0; i < questionList.size(); i++) {
			 msg.setContent(questionList.get(i).getQuestion());
			 send(msg);
			 
			 ACLMessage msgReply = blockingReceive();
			 System.out.println("\nStudent answer");
			 System.out.println("Message sender:" + msgReply.getSender().getLocalName());
			 System.out.println("Message content (student's answer):" + msgReply.getContent());
			 
			 try {
				 Integer questionAnswer = Integer.valueOf(msgReply.getContent());
				 if (questionAnswer == questionList.get(i).getAnswer()) {
					 System.out.println("Congrats, you get one point");
					 score++;
				 }
				 if (questionAnswer != questionList.get(i).getAnswer()) {
					 System.out.println("Sorry, you missed this one");

				 }
				 
				 System.out.println("Score: " + score);
			 } catch (Exception e) {
				 System.out.println("Some kind of exception");
			 }
			 
		 }	
		 if (score > 7) {
			 System.out.println("You passed, good job");
			 msg.setContent("You passed with score: " + score);
			 send(msg);
		 } else {
			 System.out.println("You failed :(");
			 msg.setContent("You failed with score: " + score);
			 send(msg);
		 }

	}
}
