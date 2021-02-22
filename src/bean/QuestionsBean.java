package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class QuestionsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;

	public QuestionsBean(int id, String question) {
		this.id = id;
		this.question = question;
	}

	public QuestionsBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}
