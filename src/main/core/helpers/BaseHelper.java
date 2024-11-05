package core.helpers;

import core.ApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseHelper {

    public static final Logger logger = LogManager.getLogger();
    protected ApplicationManager app = ApplicationManager.get();

}
