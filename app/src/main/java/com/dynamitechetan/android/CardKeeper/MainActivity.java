package com.dynamitechetan.android.CardKeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appinvite.AppInviteInvitation;


public class MainActivity extends AppCompatActivity {

private static final String CONTENT = "content";
    private static final String FORMAT = "format";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 0;
    private static final int SCAN_CARD = 49374;
    private CoordinatorLayout co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        co = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent showScannedCard = new Intent(this, AddCard.class);

            startActivity(showScannedCard);
                }else if (id == R.id.action_share_app){
                    Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                            .setMessage(getString(R.string.invitation_message))
                            .setCallToActionText(getString(R.string.invitation_cta))
                            .build();
                            startActivityForResult(intent, REQUEST_INVITE);
                }

        return super.onOptionsItemSelected(item);
    }


    private void showMessage(String msg) {
        Snackbar snackbar = Snackbar.make(co, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
