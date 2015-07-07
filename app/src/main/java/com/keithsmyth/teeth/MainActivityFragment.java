package com.keithsmyth.teeth;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  public static MainActivityFragment create() {
    return new MainActivityFragment();
  }

  private Listener listener;

  public MainActivityFragment() {
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    listener = (Listener) activity;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final EditText sayText = (EditText) view.findViewById(R.id.txt_say);
    sayText.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        final int RIGHT = 2;
        if (event.getRawX() >=
            (v.getRight() - sayText.getCompoundDrawables()[RIGHT].getBounds().width())) {
          sayText.setText(null);
        }
        return false;
      }
    });
    view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (listener == null) return;
        hideKeyboard(sayText);
        listener.onNext(sayText.getText().toString());
      }
    });
    view.findViewById(R.id.btn_why).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (listener == null) return;
        hideKeyboard(sayText);
        listener.onNext(getString(R.string.why_answer));
      }
    });
  }

  private void hideKeyboard(TextView textView) {
    InputMethodManager imm = (InputMethodManager) getActivity()
        .getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
  }

  public interface Listener {
    void onNext(String say);
  }
}
