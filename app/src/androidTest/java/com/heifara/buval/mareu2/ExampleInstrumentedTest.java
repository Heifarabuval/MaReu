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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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

    private  static int ITEM_COUNT;

    @Rule
    public final ActivityTestRule<ListMeetActivity> meetActivityActivityTestRule =new ActivityTestRule(ListMeetActivity.class);

    @Before
    public void setup(){
        meetActivity = meetActivityActivityTestRule.getActivity();
        assertThat(meetActivity,notNullValue());



    }

    @Test
    public void meetList_deleteAction(){
         ITEM_COUNT = 6;
        // Given : We remove the element at position 2
        onView(allOf(isDisplayed(),withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT));
        // When perform a click on a delete icon
        onView(allOf(isDisplayed(),withId(R.id.meet_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(isDisplayed(),withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT-1));
    }
@Test
public void addSameMeet(){

    ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.meet_add),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            1),
                    isDisplayed()));
    floatingActionButton.perform(click());

    ViewInteraction appCompatAutoCompleteTextView = onView(
            allOf(withId(R.id.room_name),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.room_name_layout),
                                    0),
                            0),
                    isDisplayed()));
    appCompatAutoCompleteTextView.perform(replaceText("Room 1"), closeSoftKeyboard());

    ViewInteraction textInputEditText = onView(
            allOf(withId(R.id.topic),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.topic_layout),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText.perform(replaceText("Conf"), closeSoftKeyboard());



    ViewInteraction textInputEditText2 = onView(
            allOf(withId(R.id.date),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.date_layout),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText2.perform(replaceText("31/08/2020"), closeSoftKeyboard());



    ViewInteraction textInputEditText3 = onView(
            allOf(withId(R.id.from),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.from_layout),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText3.perform(replaceText("10:00"),closeSoftKeyboard());



    ViewInteraction textInputEditText4 = onView(
            allOf(withId(R.id.until),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.until_layout),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText4.perform(replaceText("12:00"),closeSoftKeyboard());



    ViewInteraction textInputEditText5 = onView(
            allOf(withId(R.id.emails),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.participants),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText5.perform(replaceText("aa@cc.bb"), closeSoftKeyboard());

    ViewInteraction textInputEditText6 = onView(
            allOf(withId(R.id.emails), withText("aa@cc.bb"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.participants),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText6.perform(pressImeActionButton());

    ViewInteraction textInputEditText7 = onView(
            allOf(withId(R.id.emails),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.participants),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText7.perform(click());

    ViewInteraction textInputEditText8 = onView(
            allOf(withId(R.id.emails),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.participants),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText8.perform(replaceText("dd@ee.ff"), closeSoftKeyboard());

    ViewInteraction textInputEditText9 = onView(
            allOf(withId(R.id.emails), withText("dd@ee.ff"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.participants),
                                    0),
                            0),
                    isDisplayed()));
    textInputEditText9.perform(pressImeActionButton());

    ViewInteraction actionMenuItemView = onView(
            allOf(withId(R.id.action_add_meeting), withContentDescription("action_add"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.action_bar),
                                    2),
                            0),
                    isDisplayed()));
    actionMenuItemView.perform(click());





}
    @Test
    public void addMeetAndCheckList() {
        ITEM_COUNT=4;
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.meet_add),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.room_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.room_name_layout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("Room 2"), closeSoftKeyboard());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.topic),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.topic_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Conf"), closeSoftKeyboard());



        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.date_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("23/06/2020"), closeSoftKeyboard());



        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.from),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.from_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("10:40"),closeSoftKeyboard());



        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("12:40"),closeSoftKeyboard());



        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.emails),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("aa@cc.bb"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.emails), withText("aa@cc.bb"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(pressImeActionButton());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.emails),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.emails),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("dd@ee.ff"), closeSoftKeyboard());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.emails), withText("dd@ee.ff"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(pressImeActionButton());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_add_meeting), withContentDescription("action_add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.meet_list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0)),
                        4),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
        onView(allOf(isDisplayed(),withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT+1));
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
