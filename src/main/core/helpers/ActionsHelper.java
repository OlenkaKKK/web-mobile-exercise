package core.helpers;

import com.google.common.collect.ImmutableList;
import core.Configuration;
import core.constants.Direction;
import core.constants.MobileSpecific;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

public class ActionsHelper extends BaseHelper {

    public void swipe(Point startPoint, Point endPoint, long durationInMillis) {
        logger.debug("Swipe action initiated from startPoint({}, {}) to endPoint({}, {}), duration={}ms",
                startPoint.x, startPoint.y, endPoint.x, endPoint.y, durationInMillis);
        Duration duration = Duration.ofMillis(durationInMillis);
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.x, startPoint.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), endPoint.x, endPoint.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        app.appium().getDriver().perform(ImmutableList.of(swipe));
        logger.debug("Swipe action performed successfully.");
    }

    public void scroll(Direction direction, double scrollRatio, long durationInMillis) {
        logger.debug("Scroll action initiated in direction={} with scrollRatio={} and duration={}ms",
                direction.getValue(), scrollRatio, durationInMillis);
        if (scrollRatio < 0 || scrollRatio > 1)
            logger.error("Scroll ratio must be between 0 and 1. Provided scrollRatio={}", scrollRatio);
        Dimension screenSize = app.appium().getDriver().manage().window().getSize();
        Point midPoint = new Point((int) (screenSize.width * 0.5), (int) (screenSize.height * 0.5));

        int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
        int top = midPoint.y - (int) (midPoint.y * scrollRatio);
        int left = midPoint.x - (int) (midPoint.x * scrollRatio);
        int right = midPoint.x + (int) (midPoint.x * scrollRatio);

        switch (direction) {
            case UP:
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), durationInMillis);
                break;
            case DOWN:
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), durationInMillis);
                break;
            case LEFT:
                swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), durationInMillis);
                break;
            case RIGHT:
                swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), durationInMillis);
                break;
            default:
                logger.error("Direction {} is not implemented", direction.getValue());
        }
    }

    public void scrollToText(Direction direction, String text, int attempts) {
        logger.debug("Scrolling to text='{}' in direction={} with a maximum of {} attempts",
                text, direction.getValue(), attempts);
        scrollToText(direction, text, 15, attempts);
    }

    public void scrollToText(Direction direction, String text, double scrollRatio, int attempts) {
        logger.debug("Scrolling to text='{}' with scrollRatio={} in direction={} over a maximum of {} attempts",
                text, scrollRatio, direction.getValue(), attempts);
        while (attempts-- > 0) {
            if (app.waits().isTextPresent(text))
                return;
            else {
                //TODO: debug why scroll is not working on android and find a unified solution
                if (Configuration.PLATFORM_IOS) {
                    scroll(direction, scrollRatio, 2);
                } else {
                    swipe(Direction.DOWN);
                    swipe(Direction.DOWN);
                }
            }
        }
    }

    public void swipe(Direction direction) {
        logger.debug("Swipe initiated in direction: {}", direction);

        final int animationTimeInMillis = 300;
        final HashMap<String, Object> scrollObject = new HashMap<>();
        scrollObject.put(MobileSpecific.DIRECTION, direction.getValue());
        scrollObject.put(MobileSpecific.PERCENT, 1);
        scrollObject.put(Direction.LEFT.getValue(), 300);
        scrollObject.put(Direction.TOP.getValue(), 500);
        scrollObject.put(Direction.WIDTH.getValue(), 1000);
        scrollObject.put(Direction.HEIGHT.getValue(), 1200);

        try {
            scrollObject.put(MobileSpecific.DIRECTION, direction.getValue());
            app.appium().getDriver().executeScript("mobile: scrollGesture", scrollObject);
            Thread.sleep(animationTimeInMillis); // wait until swipe complete
        } catch (Exception e) {
            logger.error("Swipe failed in direction: {}. Error: {}", direction, e.getMessage(), e);
        }
    }

    public void tap(int x, int y) {
        logger.debug("Tap initiated at coordinates: (x: {}, y: {})", x, y);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        app.appium().getDriver().perform(Arrays.asList(tap));
    }
}
