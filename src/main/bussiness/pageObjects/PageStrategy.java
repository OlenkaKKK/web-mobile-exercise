package bussiness.pageObjects;

public class PageStrategy {

    public HomeWebPage homeWebPage() {
        return new HomeWebPage().waitUntilLoaded();
    }

    public FAQWebPage faqWebPage() {
        return new FAQWebPage().waitUntilLoaded();
    }

}
