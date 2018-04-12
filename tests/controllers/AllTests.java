package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerTest.class, AdminFuncControllerTest.class, DBControllerTest.class,
		SearchControllerTest.class, UserFuncControllerTest.class })
public class AllTests {

}
