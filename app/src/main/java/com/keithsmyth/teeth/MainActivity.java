package com.keithsmyth.teeth;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity implements MainActivityFragment.Listener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .add(R.id.container, MainActivityFragment.create())
          .commit();
    }
  }

  @Override public void onNext(String say) {
    getFragmentManager().beginTransaction()
        .replace(R.id.container, SayFragment.create(say))
        .addToBackStack(SayFragment.class.getName())
        .commit();
  }
}
