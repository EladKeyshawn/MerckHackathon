package com.projects.elad.platereader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseKitActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


  private static final int RESULT_LOAD_IMAGE = 123;
  private ListView kitsList;
  private ArrayAdapter<String> kitAdapter;
  private ArrayList<String> kitsContents;
  private ImageView croppedImage;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_kit);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    kitsContents = new ArrayList<>();
    kitsContents.add("Acid Phosphatase Assay Kit");
    kitsContents.add("Protein Concentration Assay Kit");
    kitsContents.add("Ammonia Assay Kit");
    kitsContents.add("Lactose Assay Kit");
    kitsContents.add("Glucose Assay Kit");
    kitsContents.add("ADP/ATP Assay Kit");
    kitsContents.add("Citrate Assay Kit");
    kitsList = (ListView) findViewById(R.id.choose_kit_listview);
    croppedImage = (ImageView) findViewById(R.id.cropped_preview);
    kitAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, R.id.list_item_text, kitsContents);
    kitsList.setAdapter(kitAdapter);
    kitsList.setOnItemClickListener(this);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      finish();
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    switch (requestCode) {
      case RESULT_LOAD_IMAGE:
        if (resultCode == RESULT_OK) {
          if (null != data) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
          }
        }
        break;
      case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == RESULT_OK) {
          Uri resultUri = result.getUri();
          Intent intent = new Intent(this, CroppedPreviewActivity.class);
          intent.putExtra("resultUri", resultUri.toString());
          startActivity(intent);
        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
          Exception error = result.getError();
        }

      default:
        break;
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    String kitString = kitsContents.get(position);
    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    intent.putExtra("KIT_TYPE", kitString);
    startActivityForResult(intent, RESULT_LOAD_IMAGE);


  }


}
