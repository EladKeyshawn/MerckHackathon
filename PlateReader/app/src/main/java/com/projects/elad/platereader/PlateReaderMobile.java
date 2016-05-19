package com.projects.elad.platereader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlateReaderMobile extends AppCompatActivity implements View.OnClickListener {

  private Button chooseKitBtn;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plate_reader_mobile);

    chooseKitBtn = (Button) findViewById(R.id.activity_kit_choose);
    chooseKitBtn.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {

    Intent intent = new Intent(this, ChooseKitActivity.class);
    startActivity(intent);
  }
}
