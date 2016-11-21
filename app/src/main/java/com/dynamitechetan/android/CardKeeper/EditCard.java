package com.dynamitechetan.android.CardKeeper;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dynamitechetan.android.CardKeeper.data.CardColumns;
import com.dynamitechetan.android.CardKeeper.data.CardProvider;

public class EditCard extends AppCompatActivity {
    Card myCard;
    private static final String CARD_KEY = "card";
    EditText editName;
    EditText editNumber;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_card);

            Bundle extras = getIntent().getExtras();
            myCard = extras.getParcelable(CARD_KEY);

            editName = (EditText) findViewById(R.id.editNameText);
            editNumber = (EditText) findViewById(R.id.editNumberText);
            editName.setText(myCard.name);
            editNumber.setText(myCard.number);
    }


    public void deleteCard(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditCard.this);
        alertDialog.setTitle(getString(R.string.delete_dialogbox_title))
                    .setMessage(getString(R.string.delete_dialogbox_message))
                    .setPositiveButton(getString(R.string.delete_dialogbox_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            getBaseContext().getContentResolver().delete(CardProvider.Cards.withId(myCard.id), null, null);
                            Toast.makeText(getBaseContext(), R.string.card_delete_message ,Toast.LENGTH_LONG).show();
                            Intent backToMain = new Intent(EditCard.this , MainActivity.class);
                            backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(backToMain);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.delete_dialogbox_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
      }

    public void saveCard(View view) {
        String nameText = editName.getText().toString();
        String numberText = editNumber.getText().toString();
        if ((nameText != null && !nameText.isEmpty()) && (numberText != null && !numberText.isEmpty())){
            ContentValues cv = new ContentValues();
            cv.put(CardColumns.NAME, nameText);
            cv.put(CardColumns.NUMBER, numberText);
            getBaseContext().getContentResolver().update(CardProvider.Cards.withId(myCard.id),cv,null,null);
                Toast.makeText(getBaseContext(), R.string.card_saved_message ,Toast.LENGTH_LONG).show();
                Intent backToMain = new Intent(this, MainActivity.class);
                backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(backToMain);
        }else{
            Toast.makeText(this, R.string.savecard_missing_fields, Toast.LENGTH_SHORT).show();
        }
    }
}