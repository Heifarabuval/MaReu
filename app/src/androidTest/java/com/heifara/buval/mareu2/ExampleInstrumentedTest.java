package com.heifara.buval.mareu2;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.heifara.buval.mareu2.ui.fragment.meet_list.ListMeetActivity;
import com.heifara.buval.mareu2.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.heifara.buval.mareu2.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private ListMeetActivity meetActivity;

    private static final int ITEM_COUNT=4;

    @Rule
    public final ActivityTestRule<ListMeetActivity> meetActivityActivityTestRule =new ActivityTestRule(ListMeetActivity.class);

    @Before
    public void setup(){
        meetActivity = meetActivityActivityTestRule.getActivity();
        assertThat(meetActivity,notNullValue());

    }

    @Test
    public void checkIfListExist() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.meet_list),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }

    @Test
    public void meetList_deleteAction(){
        // Given : We remove the element at position 2
        onView(allOf(isDisplayed(),withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT));
        // When perform a click on a delete icon
        onView(allOf(isDisplayed(),withId(R.id.meet_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(isDisplayed(),withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT-1));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });
        return currentActivity[0];}
}
