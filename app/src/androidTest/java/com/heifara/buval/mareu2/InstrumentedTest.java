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
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstrumentedTest {

    private static final int ITEM_COUNT = 4;

    @Rule
    public final ActivityTestRule<ListMeetActivity> meetActivityActivityTestRule = new ActivityTestRule(ListMeetActivity.class);

    @Before
    public void setup() {
        ListMeetActivity meetActivity = meetActivityActivityTestRule.getActivity();
        assertThat(meetActivity, notNullValue());


    }


    @Test
    public void cMeetList_deleteAction() {

        // Given : We remove the element at position 2
        onView(allOf(isDisplayed(), withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT + 1));
        // When perform a click on a delete icon
        onView(allOf(isDisplayed(), withId(R.id.meet_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(isDisplayed(), withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT));
    }

    @Test
    public void dAddSameMeet() { //start = & end =

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
        textInputEditText3.perform(replaceText("10:00"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("12:00"), closeSoftKeyboard());


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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.room_name_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }

    @Test
    public void eAddSameMeet() { //start in end in

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
        textInputEditText3.perform(replaceText("10:30"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("11:40"), closeSoftKeyboard());


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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.room_name_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }
    @Test
    public void afilteredByDate() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.filter), withContentDescription("menu_filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.date_filter),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("31/08/2020"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                1)));
        materialButton.perform(scrollTo(), click());

        isDisplayed();


    }


    @Test
    public void aFilteredByRoomName() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.filter), withContentDescription("menu_filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.room_filter),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("Room 2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.description_item), withText("Room 2-12:00-Java conference"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meet_list),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Room 2-12:00-Java conference")));
    }

    @Test
    public void fAddSameMeet() { //start out end out

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
        textInputEditText3.perform(replaceText("08:00"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("13:00"), closeSoftKeyboard());


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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.room_name_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }

    @Test
    public void gAddSameMeet() { //start in end out

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
        textInputEditText3.perform(replaceText("09:00"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("11:30"), closeSoftKeyboard());


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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.room_name_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }

    @Test
    public void hAddSameMeet() {//start in end out

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
        textInputEditText3.perform(replaceText("10:30"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("13:00"), closeSoftKeyboard());


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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.room_name_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));


    }

    @Test
    public void bAddMeetAndCheckList() {

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
        textInputEditText3.perform(replaceText("09:40"), closeSoftKeyboard());


        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.until),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.until_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("10:40"), closeSoftKeyboard());


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
                                        IsInstanceOf.instanceOf(android.view.ViewGroup.class),
                                        0)),
                        4),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
        onView(allOf(isDisplayed(), withId(R.id.meet_list))).check(withItemCount(ITEM_COUNT + 1));
    }


    @Test
    public void aCheckIfListExist() {
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
        return currentActivity[0];
    }
}
