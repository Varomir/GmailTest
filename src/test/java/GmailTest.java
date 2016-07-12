import core.AbstractTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import springConstructors.UserData;
import testdata.DataContainer;
import pageObjects.AttachContainerPage;
import pageObjects.GmailPage;
import pageObjects.MainPage;

public class GmailTest extends AbstractTestRunner {

    private UserData accountSender, accountReceiver;
    private MainPage homeSender, homeReceiver;
    private GmailPage gmailSender, gmailReceiver;
    private AttachContainerPage attachContainer;
    private static final long TIMESTAMP = System.currentTimeMillis();

    @BeforeClass
    public void prepareUserData(){
        accountSender = DataContainer.getAccount01();
        accountReceiver = DataContainer.getAccount02();
        homeSender = new MainPage(driverSender);
        homeReceiver = new MainPage(driverReceiver);
    }

    @Test
    public void sendReceiveLetterWithAttach(){
        gmailSender = homeSender.login(accountSender).goToMailService("#inbox?compose=new");
        gmailSender.sendLetter(accountReceiver, TIMESTAMP);

        gmailReceiver = homeReceiver.login(accountReceiver).goToMailService("#inbox");
        attachContainer = gmailReceiver.findLetters(TIMESTAMP);

        Assert.assertEquals(attachContainer.getAttachSize(), 516, "Size of attached file was not as expected");
        Assert.assertTrue(attachContainer.getFullAttachText().contains("Loren Ipsum"), "Content of attached file was not as expected");
    }
}
