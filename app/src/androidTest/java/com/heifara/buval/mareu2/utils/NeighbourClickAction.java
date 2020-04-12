package com.heifara.buval.mareu2.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.heifara.buval.mareu2.R;

import org.hamcrest.Matcher;

public class NeighbourClickAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }




    @Override
    public void perform(UiController uiController, View view) {
        View click = view.findViewById(R.id.meet_list);
        // Maybe check for null
        click.performClick();
    }
}