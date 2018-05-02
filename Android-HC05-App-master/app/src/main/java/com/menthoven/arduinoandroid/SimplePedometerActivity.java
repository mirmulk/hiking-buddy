/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.menthoven.arduinoandroid;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SimplePedometerActivity extends AppCompatActivity implements SensorEventListener, StepListener {
  //private TextView textView;
  TextView textView5;
  private SimpleStepDetector simpleStepDetector;
  private SensorManager sensorManager;
  private Sensor accel;
  private static final String TEXT_NUM_STEPS = "Number of Steps: ";
  private int numSteps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //textView = new TextView(this);
    //textView.setTextSize(30);
    //setContentView(textView);
    textView5 = (TextView)findViewById(R.id.Pedo);
    // Get an instance of the SensorManager
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    simpleStepDetector = new SimpleStepDetector();
    simpleStepDetector.registerListener(this);

  }

  @Override
  public void onResume() {
    super.onResume();
    //numSteps = 0;
    //textView.setText(TEXT_NUM_STEPS + numSteps);
    //DISPLAY HUMIDITY
    //TextView textView5 = (TextView)findViewById(R.id.Pedo);
    textView5.setText("Distance Walked: " + numSteps + "steps");
    sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
  }

  @Override
  public void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
      simpleStepDetector.updateAccel(
          event.timestamp, event.values[0], event.values[1], event.values[2]);
    }
  }
  
  @Override
  public void step(long timeNs) {
    numSteps++;
    //textView.setText(TEXT_NUM_STEPS + numSteps);
  }


}
