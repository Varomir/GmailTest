package testdata;

import springConstructors.TestApp;
import springConstructors.UserData;

public class DataContainer {

    private static UserData account01;
    private static UserData account02;
    private static TestApp testApp;

    public static TestApp getTestEnv() {
        return testApp;
    }

    public static void setTestEnv(TestApp testApp) {
        DataContainer.testApp = testApp;
    }

    public static UserData getAccount01() {
        return account01;
    }

    public static void setAccountSender(UserData userData) {
        DataContainer.account01 = userData;
    }

    public static UserData getAccount02() {
        return account02;
    }

    public static void setAccountReceiver(UserData userData) {
        DataContainer.account02 = userData;
    }
}
