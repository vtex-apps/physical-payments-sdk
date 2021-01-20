package sdk.android.seed;

import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sdk.android.seed.sdk.PaymentActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    static Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), PaymentActivity.class);

    @BeforeClass
    public static void setUp() {
        intent.putExtra("teste", "teste");
    }

    @Rule
    public ActivityScenarioRule<PaymentActivity> mActivityRule = new ActivityScenarioRule<>(intent);

    @Test
    public void pickContactButton_click_SelectsPhoneNumber() {
        // Check that the number is displayed in the UI.
        onView(withId(R.id.payment_intent_text_view))
                .check(matches(withText("test")));
    }
}