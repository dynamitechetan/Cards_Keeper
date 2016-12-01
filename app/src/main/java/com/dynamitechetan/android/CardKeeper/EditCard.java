package com.dynamitechetan.android.CardKeeper;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dynamitechetan.android.CardKeeper.data.CardColumns;
import com.dynamitechetan.android.CardKeeper.data.CardProvider;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class EditCard extends AppCompatActivity {
    private static final String CARD_KEY = "card";
    Card myCard;
    EditText editName;
    EditText editNumber;
    EditText editEmail;
    EditText editWebsite;
    EditText editAddress;
    Button Photo;
    TextView PhotoPathTv;
    Uri selectedImageURI;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_card);

        Bundle extras = getIntent().getExtras();
        myCard = extras.getParcelable(CARD_KEY);

        editName = (EditText) findViewById(R.id.editNameText);
        editNumber = (EditText) findViewById(R.id.editNumberText);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editWebsite = (EditText) findViewById(R.id.editWebsiteText);
        editAddress = (EditText) findViewById(R.id.editAddressText);

        editName.setText(myCard.name);
        editNumber.setText(myCard.number);
        editEmail.setText(myCard.email);
        editAddress.setText(myCard.address);
        editWebsite.setText(myCard.website);

        Photo = (Button) findViewById(R.id.Photo);
        PhotoPathTv = (TextView) findViewById(R.id.photoTv);
        PhotoPathTv.setText(myCard.photo);
        Photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                start();
            }
        });

    }

    private void start() {
        EasyImage.openDocuments(this, 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {

        EasyImage.handleActivityResult(requestCode, resultCode, data, EditCard.this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(final File imageFile, EasyImage.ImageSource source, int type) {
                selectedImageURI = data.getData();
                PhotoPathTv.setText(selectedImageURI.toString());
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getApplicationContext());
                    if (photoFile != null) photoFile.delete();
                }
            }
        });

        if (requestCode == 10 && resultCode == RESULT_OK) {

        }
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
        String emailText = editEmail.getText().toString();
        String websiteText = editWebsite.getText().toString();
        String addressText = editAddress.getText().toString();

        if ((nameText != "" && !nameText.isEmpty()) && (numberText != "" && !numberText.isEmpty())
                && (emailText != "" && !emailText.isEmpty()) && (websiteText != "" && !websiteText.isEmpty())
                && (addressText != "" && !addressText.isEmpty())
                ) {
            ContentValues cv = new ContentValues();

            cv.put(CardColumns.NAME, nameText);
            cv.put(CardColumns.NUMBER, numberText);
            cv.put(CardColumns.PHOTO, PhotoPathTv.getText().toString());
            cv.put(CardColumns.EMAIL, emailText);
            cv.put(CardColumns.WEBSITE, websiteText);
            cv.put(CardColumns.ADDRESS, addressText);
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