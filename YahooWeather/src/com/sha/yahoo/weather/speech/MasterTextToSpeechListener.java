package com.sha.yahoo.weather.speech;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

/**
 * 
 * @author Shaik Shahul Hameed
 *
 */
public class MasterTextToSpeechListener implements OnInitListener {

	// TTS object
	private TextToSpeech shaTextSpeech;
	// status check code
	private int MY_DATA_CHECK_CODE = 0;

	private Context context;
	private String speechText;
	
	public MasterTextToSpeechListener(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		shaTextSpeech=new TextToSpeech(context, this);

	}

	// setup TTS
	public void onInit(int initStatus) {

		// check for successful instantiation
		if (initStatus == TextToSpeech.SUCCESS) {
			if (shaTextSpeech.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
				shaTextSpeech.setLanguage(Locale.US);
		} else if (initStatus == TextToSpeech.ERROR) {
			Toast.makeText(context, "Sorry! Text To Speech failed...",
					Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void speechTextCall(String speech){
		speakWords(speech);
	}
	
	//speak the user text
	private void speakWords(String speech) {
			//speak straight away
		shaTextSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	}

}
