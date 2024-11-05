package bussiness.bussinessObjects;

import bussiness.pageObjects.PageStrategy;
import core.ApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BusinessObjectsBase implements BusinessStrategy {

    public static final Logger logger = LogManager.getLogger();
    protected ApplicationManager app = ApplicationManager.get();
    protected PageStrategy page = new PageStrategy();

}
