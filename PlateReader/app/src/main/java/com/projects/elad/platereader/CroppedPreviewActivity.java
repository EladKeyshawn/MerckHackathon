package com.projects.elad.platereader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class CroppedPreviewActivity extends AppCompatActivity implements View.OnClickListener {

  private ImageView croppedImage;
  private Button resultsButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cropped_preview);

    resultsButton = (Button) findViewById(R.id.show_results_button);
    Uri resultUri = Uri.parse(getIntent().getExtras().getString("resultUri"));
    croppedImage = (ImageView) findViewById(R.id.cropped_preview);
    try {
      Bitmap  croppedImageBitMap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
      croppedImage.setImageBitmap(croppedImageBitMap);
    } catch (IOException e) {
      e.printStackTrace();
    }

    resultsButton.setOnClickListener(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

  }

  @Override
  public void onClick(View v) {

    Intent intent = new Intent(this,ResultsActivity.class );
    startActivity(intent);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      finish();
    }

    return super.onOptionsItemSelected(item);
  }
}
