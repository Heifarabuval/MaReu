package com.heifara.buval.mareu2.ui.fragment.meet_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.heifara.buval.mareu2.R;
import com.heifara.buval.mareu2.di.DI;
import com.heifara.buval.mareu2.event.DeleteMeetEvent;
import com.heifara.buval.mareu2.service.MeetApiService;
import com.heifara.buval.mareu2.ui.AddMeetActivity;
import com.heifara.buval.mareu2.ui.fragment.FilterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetActivity extends AppCompatActivity implements FilterFragment.OnButtonClickedListener {
    private static final String TAG ="ListMeetActivity" ;
    public static MeetApiService meetApiService;

    @BindView(R.id.meet_list) RecyclerView mRecyclerView;
    @BindView(R.id.meet_add) FloatingActionButton mFloatingActionButton;




    private ItemMeetRecyclerViewAdapter mMeetRecyclerViewAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meet);
        ButterKnife.bind(this);
        meetApiService = DI.getMeetApiService();
        mFloatingActionButton.setOnClickListener(v -> startActivity(new Intent(ListMeetActivity.this, AddMeetActivity.class)));
    }

    @Override
    public void onButtonClicked(Calendar date, String room, boolean reset) {
        if(reset||date!=null||!room.isEmpty())
            init(date,room);
    }


    @Override
    protected void onResume() {
        super.onResume();
        init(null,"");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()==R.id.filter){
            Log.d(TAG, "onOptionsItemSelected: filterClicked");
            performFilter();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_filter,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private  void performFilter() {
        FilterFragment filterFragment = new FilterFragment(meetApiService.getRooms());
        filterFragment.show(getSupportFragmentManager(), "filter");
    }
    @Subscribe
    public void  onDeleteMeet(DeleteMeetEvent event){
        meetApiService.deleteMeet(event.getMeetId());
        Toast.makeText(getApplicationContext(), "Réunion supprimée", Toast.LENGTH_SHORT).show();
        init(null,"");
        System.out.println("MeetList Size  "+ event.getMeetId());
    }
    private void init(Calendar date,String room){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMeetRecyclerViewAdapter = new ItemMeetRecyclerViewAdapter(this,date,room);
        mRecyclerView.setAdapter(mMeetRecyclerViewAdapter);
    }
}
