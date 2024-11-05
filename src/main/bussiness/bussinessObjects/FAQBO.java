package bussiness.bussinessObjects;

public class FAQBO extends BusinessObjectsBase {

    public void openContextMenu() {
        logger.debug("Open context menu");
        page.faqWebPage().tapOpenMenuButton();
    }
}
