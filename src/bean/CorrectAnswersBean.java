package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class CorrectAnswersBean implements Serializable  {
	private static final long serialVersionUID = 1L;

	private int id;
	private int question_id;
	private int point;
	private Timestamp created_at;

	public CorrectAnswersBean(int id, int question_id, int point) {
		this.id = id;
		this.question_id = question_id;
		this.point = point;

	}

	public CorrectAnswersBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return question_id;
	}

	public void setQuestionId(int question_id) {
		this.question_id = question_id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}



}
