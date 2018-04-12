package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import entities.*;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerTest.class, AdminFuncControllerTest.class, DBControllerTest.class,
		SearchControllerTest.class, UserFuncControllerTest.class, AdminTest.class, GeneralUserTest.class, UniversityTest.class})
public class AllTests {

}
