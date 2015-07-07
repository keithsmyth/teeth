package com.keithsmyth.teeth;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.UUID;

/**
 * @author keithsmyth
 */
public class SayFragment extends Fragment implements TextToSpeech.OnInitListener {

  private static final String MESSAGE_KEY = "message-key";
  private FloatingActionButton speakButton;
  private TextToSpeech tts;

  public static SayFragment create(String message) {
    Bundle bundle = new Bundle();
    bundle.putString(MESSAGE_KEY, message);
    SayFragment fragment = new SayFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
      savedInstanceState) {
    return inflater.inflate(R.layout.fragment_say, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final String message = getArguments().getString(MESSAGE_KEY);
    final TextView speakText = (TextView) view.findViewById(R.id.txt_speak);
    speakText.setText(message);
    speakButton = (FloatingActionButton) view.findViewById(R.id.btn_speak);
    speakButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString());
      }
    });
  }

  @Override public void onStart() {
    super.onStart();
    speakButton.setVisibility(View.GONE);
    tts = new TextToSpeech(getActivity(), this);
    tts.setLanguage(Locale.UK);
  }

  @Override public void onStop() {
    super.onStop();
    tts.stop();
    tts.shutdown();
  }

  @Override public void onInit(int status) {
    speakButton.setVisibility(View.VISIBLE);
  }
}
