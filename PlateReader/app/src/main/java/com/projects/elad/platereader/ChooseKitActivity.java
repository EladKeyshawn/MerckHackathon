package com.projects.elad.platereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ChooseKitActivity extends AppCompatActivity {


  private ListView kitsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_kit);

    kitsList = (ListView) findViewById(R.id.choose_kit_listview);

  }
}
