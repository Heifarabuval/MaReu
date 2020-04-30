package com.heifara.buval.mareu2.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.heifara.buval.mareu2.R;
import com.heifara.buval.mareu2.di.DI;
import com.heifara.buval.mareu2.model.Meet;
import com.heifara.buval.mareu2.service.MeetApiService;
import com.heifara.buval.mareu2.service.MeetApiServiceException;
import com.heifara.buval.mareu2.ui.fragment.meet_list.ItemMeetRecyclerViewAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

import static com.heifara.buval.mareu2.utils.Calendar.checkTime;
import static com.heifara.buval.mareu2.utils.Calendar.endInStartOut;
import static com.heifara.buval.mareu2.utils.Calendar.inRangeTime;
import static com.heifara.buval.mareu2.utils.Calendar.outRangeTime;
import static com.heifara.buval.mareu2.utils.Calendar.startInEndOut;

public class AddMeetActivity extends AppCompatActivity {
    private static MeetApiService meetApiService;
    private Calendar mNow;
    private boolean error;
    @BindView(R.id.room_name_layout)
    TextInputLayout mRoomNameTextInputLayout;
    @BindView(R.id.room_name)
    AutoCompleteTextView mRoomNameAutoCompleteTextView;
    @BindView(R.id.topic_layout)
    TextInputLayout mTopicTextInputLayout;
    @BindView(R.id.topic)
    TextInputEditText mTopicTextInputEditText;
    @BindView(R.id.date_layout)
    TextInputLayout mDateTextInputLayout;
    @BindView(R.id.date)
    TextInputEditText mDateTextInputEditText;
    @BindView(R.id.from_layout)
    TextInputLayout mStartTimeTextInputLayout;
    @BindView(R.id.from)
    TextInputEditText mStartTimeTextInputEditText;
    @BindView(R.id.until_layout)
    TextInputLayout mEndTimeTextInputLayout;
    @BindView(R.id.until)
    TextInputEditText mEndTimeTextInputEditText;

    @BindView(R.id.participants)
    TextInputLayout mEmailsTextInputLayout;
    @BindView(R.id.emails_group)
    ChipGroup mEmailsChipGroup;
    @BindView(R.id.emails)
    TextInputEditText mEmailsTextInputEditText;

    private static boolean validateEmail(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value.trim()).matches();
    }

    private static String randomColor() {
        int random = new Random().nextInt(ItemMeetRecyclerViewAdapter.DRAWABLES.size() - 1);

        return ItemMeetRecyclerViewAdapter.DRAWABLES.get(random);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meet);
        ButterKnife.bind(this);
        error = false;
        meetApiService = DI.getMeetApiService();
        mNow = Calendar.getInstance();
        List<String> mRooms = meetApiService.getRooms();
        initEmailsKeyListener();
        mRoomNameAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this, R.layout.room_item, mRooms));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_add_meet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.date)
    void displayDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(AddMeetActivity.this,
                (view, year, month, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, dayOfMonth);
                    mDateTextInputEditText.setText(DateFormat.getDateFormat(getApplicationContext()).format(cal.getTime()));
                    if (cal.before(calendar))
                        mDateTextInputLayout.setError(getText(R.string.error_date_passed));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick({R.id.from, R.id.until})
    void displayTime(View v) {
        final int id = v.getId();

        Calendar time = Calendar.getInstance();
        TimePickerDialog timePickerDialog;

        int roundedMinutes = time.get(Calendar.MINUTE) % 15;
        time.add(Calendar.MINUTE, roundedMinutes > 0 ? (15 - roundedMinutes) : 0);

        timePickerDialog = new TimePickerDialog(AddMeetActivity.this,
                (view, hourOfDay, minute) -> {
                    Calendar tim = Calendar.getInstance();
                    tim.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    tim.set(Calendar.MINUTE, minute);
                    if (id == R.id.from)
                        mStartTimeTextInputEditText.setText(DateFormat.getTimeFormat(getApplicationContext()).format(tim.getTime()));
                    else if (id == R.id.until)
                        mEndTimeTextInputEditText.setText(DateFormat.getTimeFormat(getApplicationContext()).format(tim.getTime()));
                },
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(AddMeetActivity.this));

        timePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_meeting:
                addMeet();
                return true;
            case R.id.home:
                Toast.makeText(this.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnTouch(R.id.room_name)
    boolean onTouch(MotionEvent motionEvent) {
        mRoomNameTextInputLayout.setHint("");
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();

            return true;
        }
        return (motionEvent.getAction() == MotionEvent.ACTION_UP);
    }


    private void checkBookingRooms() {
        String roomName = checkTextInput(mRoomNameTextInputLayout);
        Calendar date = validDate(mDateTextInputLayout);
        Calendar start = validStartTime(mStartTimeTextInputLayout);
        Calendar end = validStartTime(mEndTimeTextInputLayout);
        List<Meet> currentMeetList = meetApiService.getMeets(date, roomName);


        for (int i = 0; i < currentMeetList.size(); i++) {
            Calendar tempStartTime = currentMeetList.get(i).getStart();
            Calendar tempEndTime = currentMeetList.get(i).getEnd();

            if (checkTime(start, end, tempStartTime, tempEndTime)
                    || outRangeTime(start, end, tempStartTime, tempEndTime)
                    || inRangeTime(start, end, tempStartTime, tempEndTime)
                    || startInEndOut(start, tempStartTime, tempEndTime)
                    || endInStartOut(end, tempStartTime, tempEndTime)) {
                mRoomNameTextInputLayout.setError(getText(R.string.error_meeting_room_already_booked));
                error = true;
                break;
            }


        }
    }

    private void addMeet() {
        String roomName = checkTextInput(mRoomNameTextInputLayout);
        String topic = checkTextInput(mTopicTextInputLayout);
        Calendar date = validDate(mDateTextInputLayout);
        Calendar start = validStartTime(mStartTimeTextInputLayout);
        Calendar end = validStartTime(mEndTimeTextInputLayout);
        List<String> email = validEmail(mEmailsTextInputLayout, mEmailsChipGroup);

        error = email == null;

        if (start != null && end != null) {
            checkBookingRooms();
        } else {
            error = true;
        }
        if (start != null && end != null) {
            if (end.before(start)) {
                mEndTimeTextInputLayout.setError(getText(R.string.error_date_passed));
                error = true;
            }
        }

        if (date != null && start != null) {
            start.set(Calendar.YEAR, date.get(Calendar.YEAR));
            start.set(Calendar.MONTH, date.get(Calendar.MONTH));
            start.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
            if (mNow.get(Calendar.DAY_OF_MONTH) == start.get(Calendar.DAY_OF_MONTH)) {
                if (start.before(mNow) || end.before(mNow)) {
                    mStartTimeTextInputLayout.setError(getText(R.string.error_time_passed));
                    error = true;
                }
            }

        }
        if (error) {
            Toast.makeText(this.getApplicationContext(), getText(R.string.error_input), Toast.LENGTH_LONG).show();

        } else {
            error = false;
            try {

                meetApiService.createMeet(new Meet(
                        roomName,
                        date,
                        start,
                        end,
                        email,
                        topic, Color.parseColor(randomColor())));
                Toast.makeText(this.getApplicationContext(), R.string.add_new_meet, Toast.LENGTH_LONG).show();
                finish();
            } catch (MeetApiServiceException e) {
                mRoomNameTextInputLayout.setError(getText(R.string.error_has_occured));
                error = false;
            }
        }
    }

    private String checkTextInput(TextInputLayout inputValue) {
        String tempValue = Objects.requireNonNull(inputValue.getEditText().getText().toString().trim());
        if (tempValue.isEmpty()) {
            inputValue.setError(getText(R.string.empty_error_text));
            error = true;
            return null;
        } else {
            inputValue.setError(null);
            return tempValue;
        }
    }

    private Calendar validDate(TextInputLayout inputValue) {
        String tempValue = Objects.requireNonNull(inputValue.getEditText().getText().toString().trim());
        if (tempValue.isEmpty()) {
            inputValue.setError(getString(R.string.error_empty));
            error = true;
            return null;

        } else {
            try {
                Date dDate = DateFormat.getDateFormat(getApplicationContext()).parse(tempValue);
                Calendar now = Calendar.getInstance();
                now.set(Calendar.HOUR_OF_DAY, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                now.set(Calendar.MILLISECOND, 0);
                Calendar date = (Calendar) now.clone();
                date.setTime(Objects.requireNonNull(dDate));
                if (date.before(now)) {
                    inputValue.setError(getText(R.string.error_date_passed));
                    error = true;
                    return null;
                }
                inputValue.setError(null);
                return date;
            } catch (ParseException e) {
                inputValue.setError(getText(R.string.error_format));
                error = true;
                return null;
            }

        }

    }

    private List<String> validEmail(TextInputLayout inputValue, ChipGroup emails) {
        inputValue.setError(null);
        int number = emails.getChildCount();
        List<String> listEmails = new ArrayList<>();

        if (number == 0) {
            inputValue.setError(getText(R.string.error_empty));
            error = true;
            return null;
        } else {
            for (int i = 0; i < number; i++) {
                Chip tempMail = (Chip) emails.getChildAt(i);
                String email = tempMail.getText().toString();
                listEmails.add(email);
            }
        }
        return listEmails;
    }

    private Calendar validStartTime(TextInputLayout inputValue) {
        String tempValue = Objects.requireNonNull(inputValue.getEditText().getText().toString().trim());

        if (tempValue.isEmpty()) {
            inputValue.setError(getText(R.string.error_empty));
            error = true;
            return null;
        } else {
            try {
                Date dTime = android.text.format.DateFormat.getTimeFormat(getApplicationContext()).parse(tempValue);
                Calendar time = Calendar.getInstance();
                time.setTime(Objects.requireNonNull(dTime));

                time.set(Calendar.YEAR, mNow.get(Calendar.YEAR));
                time.set(Calendar.MONTH, mNow.get(Calendar.MONTH));
                time.set(Calendar.DAY_OF_MONTH, mNow.get(Calendar.DAY_OF_MONTH));
                mNow.get(Calendar.DAY_OF_MONTH);
                inputValue.setError(null);
                return time;
            } catch (ParseException e) {
                inputValue.setError(getText(R.string.error_format));
                error = true;
                return null;
            }

        }
    }

    @OnTextChanged(R.id.emails)
    void afterTextChanged(Editable s) {
        String value = s.toString();

        if (value.length() > 0) {
            char lastChar = value.charAt(value.length() - 1);

            if (lastChar == ' ' || lastChar == ',') {
                value = value.substring(0, value.length() - 1);
                value = value.trim();


                if (!value.isEmpty()) {
                    if (validateEmail(value)) {
                        addEmailToChipGroup(value);

                    } else {
                        mEmailsTextInputLayout.setError(getText(R.string.empty_email)); 
                    }
                }
            }
        }
    }


    private void addEmailToChipGroup(String email) {
        final Chip emailChip = new Chip(AddMeetActivity.this);
        emailChip.setText(email);
        emailChip.setCloseIconVisible(true);
        emailChip.setOnCloseIconClickListener(v -> mEmailsChipGroup.removeView(emailChip));

        mEmailsChipGroup.addView(emailChip);
        mEmailsTextInputEditText.setText("");
        mEmailsTextInputLayout.setError(null);
    }

    private void initEmailsKeyListener() {
        mEmailsTextInputEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String value = Objects.requireNonNull(mEmailsTextInputEditText.getText()).toString().trim();

                    if (!value.isEmpty()) {
                        if (!validateEmail(value)) {
                            mEmailsTextInputLayout.setError(getText(R.string.invalid_mail));

                            return false;
                        } else {
                            addEmailToChipGroup(value);

                            return true;
                        }
                    }
                }
            }
            return false;
        });
    }
}

