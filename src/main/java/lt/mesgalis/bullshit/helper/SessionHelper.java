package lt.mesgalis.bullshit.helper;

import javaslang.collection.Set;
import javaslang.collection.TreeSet;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionHelper {

	private static final String SESSION_ATTR_USED_QUESTION_IDS = "USED_QUESTION_IDS";
	private static final String SESSION_ATTR_TOTAL_TRIES = "TOTAL_TRIES";
	private static final String SESSION_ATTR_SUCCESS_TRIES = "SUCCESS_TRIES";
	private static final String SESSION_ATTR_ADDED_QUESTIONS = "ADDED_QUESTIONS";
	private static final String SESSION_ATTR_CURRENT_QUESTION = "CURRENT_QUESTION";

	private static final int PERCENTS_TO_BE_WORTHY = 50;

	private HttpSession session;

	@Autowired
	public SessionHelper(HttpSession session) {
		this.session = session;
	}

	public Set<Long> getUsedQuestions() {
		return (Set<Long>) session.getAttribute(SESSION_ATTR_USED_QUESTION_IDS);
	}

	public int getTotalTries() {
		return (int) Option.of(session.getAttribute(SESSION_ATTR_TOTAL_TRIES)).getOrElse(0);
	}

	public int getSuccessTries() {
		return (int) Option.of(session.getAttribute(SESSION_ATTR_SUCCESS_TRIES)).getOrElse(0);
	}

	public Long getCurrentQuestion() {
		return (Long) session.getAttribute(SESSION_ATTR_CURRENT_QUESTION);
	}

	public void setCurrentQuestion(Long questionId) {
		session.setAttribute(SESSION_ATTR_CURRENT_QUESTION, questionId);
	}

	public void clearCurrentQuestion() {
		session.removeAttribute(SESSION_ATTR_CURRENT_QUESTION);
	}

	public int getAddedQuestions() {
		return (int) Option.of(session.getAttribute(SESSION_ATTR_ADDED_QUESTIONS)).getOrElse(0);
	}

	public void addToUsedIDS(Long usedID) {
		session.setAttribute(SESSION_ATTR_USED_QUESTION_IDS,
				Option.of(getUsedQuestions())
						.getOrElse(TreeSet.empty())
						.add(usedID));
	}

	public void addQuestionTry(boolean success) {
		session.setAttribute(SESSION_ATTR_TOTAL_TRIES,
				Option.of(getTotalTries())
						.getOrElse(0) + 1);
		if (success) {
			addQuestionTrySuccess();
		}
	}

	private void addQuestionTrySuccess() {
		session.setAttribute(SESSION_ATTR_SUCCESS_TRIES,
				Option.of(getSuccessTries())
						.getOrElse(0) + 1);
	}

	private void addAddedQuestion() {
		session.setAttribute(SESSION_ATTR_ADDED_QUESTIONS,
				Option.of(getSuccessTries())
						.getOrElse(0) + 1);
	}

	// TODO make 'worthy' rules more configurable
	public boolean isWorthyToAddQuestion(int questionsCount) {
		return getTotalTries() == questionsCount / 2 || getTotalTries() == questionsCount
				&& (getSuccessTries() / getTotalTries()) * 100 > (PERCENTS_TO_BE_WORTHY);
	}

	public void forgetMe() {
		session.invalidate();
	}
}
