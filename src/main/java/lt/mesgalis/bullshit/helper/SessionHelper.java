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

}
