package com.dynamitechetan.android.CardKeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {
    private static final String CARD_KEY = "card" ;
    private static final String SAVED_CARD_KEY = "saved_card" ;
    Card myCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            myCard = getIntent().getExtras().getParcelable(CARD_KEY);

        }   else if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_CARD_KEY)) {
            myCard = savedInstanceState.getParcelable(SAVED_CARD_KEY);
        }
        Bundle arguments = new Bundle();
        arguments.putParcelable(CARD_KEY, myCard);
        DetailActivityFragment fragment = new DetailActivityFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.card_detail_container, fragment)
                .replace(R.id.card_detail_container,fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_CARD_KEY,myCard);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_edit) {
            Intent editCard = new Intent(getBaseContext(),EditCard.class);
            editCard.putExtra(CARD_KEY,myCard);
            startActivity(editCard);
        }
        return super.onOptionsItemSelected(item);
    }
}
