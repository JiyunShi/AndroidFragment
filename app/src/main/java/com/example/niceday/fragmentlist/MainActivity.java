package com.example.niceday.fragmentlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener{

    private TheResponse response;
    private String personTexts;
    MenuFragment menuFragment = new MenuFragment();
    DetailFragment detailFragment = new DetailFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent i = new Intent(MainActivity.this, DownloadService.class);

        startService(i);

        response = new TheResponse(this);

    }

    protected void onPause(){
        super.onPause();
        unregisterReceiver(response);
    }

    protected void onResume(){
        super.onResume();
        IntentFilter filter = new IntentFilter(TheResponse.STATUS_DONE);
        registerReceiver(response,filter);
    }

    @Override
    public void onFragmentInteraction(String person) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter,R.anim.exit);
        ft2.setCustomAnimations(R.anim.menuenter,R.anim.enter);

        Bundle bundle = new Bundle();
        bundle.putString("datas", person);
        detailFragment.setArguments(bundle);

        if(!detailFragment.isAdded()){
            ft.add(R.id.content,detailFragment).commit();
        }else{
            ft.detach(detailFragment).attach(detailFragment).commit();
        }




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    class TheResponse extends BroadcastReceiver {

        Context c;
        public TheResponse(Context c) {
            this.c = c;
        }
        public static final String STATUS_DONE = "com.example.intentservebroaddemo_v1.ALL_DONE";
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(STATUS_DONE)) {
                personTexts= intent.getStringExtra("output");
                Bundle bundle = new Bundle();
                bundle.putString("datas", personTexts);
                menuFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.content, menuFragment).commit();

            }
        }

    }


}
