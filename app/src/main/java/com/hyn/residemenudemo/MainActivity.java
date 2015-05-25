package com.hyn.residemenudemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.hyn.residemenu.ResideMenu;
import com.hyn.residemenu.ResideMenuItem;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setResideMenu();
        if (savedInstanceState == null) {
            changFragment(new HomeFragment());
        }
    }

    private void setResideMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.7f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemSetting = new ResideMenuItem(this, R.drawable.icon_settings, "setting");
        itemHome.setOnClickListener(this);
        itemSetting.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSetting, ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public void onClick(View view) {
        if (view == itemHome) {
            changFragment(new HomeFragment());
        } else if (view == itemSetting) {
            changFragment(new SettingFragment());
        }
        resideMenu.closeMenu();
    }

    public void changFragment(Fragment fragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
