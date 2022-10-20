package core.helper;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import core.report.Logger;

/**
 * Handle Assertion
 */
public class AssertHelper extends Assertion {

	/**
	 * Handle onAssertSucess interface via logger by test listener on passed test
	 * case
	 */
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		Logger.passedAssertion("PASS");
	}

	/**
	 * Handle onAssertFailure interface via logger by test listener on failed test
	 * case, it will get the failed line and add to logger
	 */
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		String _mess = "FAIL -";
		for (StackTraceElement ste : ex.getStackTrace()) {
			if (!ste.toString().toLowerCase().contains("assert")) {
				_mess += " Line# " + ste.getLineNumber() + " - " + ex.getMessage();
				break;
			}
		}
		Logger.failedAssertion(_mess);
	}

}
