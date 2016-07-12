package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import springConstructors.UserData;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class GmailPage extends BasePage {

    public GmailPage(WebDriver driver) {
        super(driver);
    }

    private static final String SUBJECT_PREFIX = "Unique subject ";
    @FindBy(name = "to")
    private WebElement emailReceiverField;

    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    @FindBy(css = "div.Am.Al[role='textbox']")
    private WebElement messageBodyTextArea;

    @FindBy(css = "div.a1.aaA.aMZ")
    private WebElement attachBtn;

    @FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
    private WebElement sendLetterBtn;

    @FindBy(css = "div.vh span#link_vsm")
    private WebElement succesfullMsg;

    @FindBy(css = "table.F.cf.zt")
    private WebElement tableSubjects;

    @FindBy(xpath = "//div[@class='aSH'][//span[@class='aV3 a6U']]")
    private WebElement downloadAttachBtn;

    @FindBy(css = "pre.aLF-aPX-K0-aPE.aLF-aPX-aLK-ayr-auR")
    private WebElement attachcContainer;

    public void sendLetter(UserData receiver, long timestamp) {
        fillLetterForm(receiver.getUsername(), timestamp);
        attachFiles();
        driver.quit();
    }

    public AttachContainerPage findLetters(final long timestamp) {
        List<WebElement> records = tableSubjects.findElements(By.cssSelector("tr.zA.zE td.a4W"));
        WebElement matchSubject = records.parallelStream().
                filter(e -> e.getText().contains(SUBJECT_PREFIX + timestamp)).
                findFirst().get();
        matchSubject.click();
        wait.until(ExpectedConditions.elementToBeClickable(downloadAttachBtn));
        downloadAttachBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(attachcContainer));
        return new AttachContainerPage(driver);
    }

    private void fillLetterForm(String email, long timestamp){
        wait.until(ExpectedConditions.elementToBeClickable(emailReceiverField));
        setText(emailReceiverField, email);
        wait.until(ExpectedConditions.elementToBeClickable(subjectField));
        setText(subjectField, SUBJECT_PREFIX + timestamp);
        wait.until(ExpectedConditions.elementToBeClickable(messageBodyTextArea));
        setText(messageBodyTextArea, "Loren ipsum");
    }

    private void attachFiles() {
        clickAttach();
        setClipboardData();
        try {
            Robot robot = new Robot();
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.dQ")));
        wait.until(ExpectedConditions.elementToBeClickable(sendLetterBtn));
        sendLetterBtn.click();
        wait.until(ExpectedConditions.visibilityOf(succesfullMsg));
    }

    private void clickAttach() {
        wait.until(ExpectedConditions.elementToBeClickable(attachBtn));
        attachBtn.click();
    }

    private static void setClipboardData() {
        String fileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\" + "attach_file.txt";
        StringSelection filepath = new StringSelection(fileLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
    }
}
