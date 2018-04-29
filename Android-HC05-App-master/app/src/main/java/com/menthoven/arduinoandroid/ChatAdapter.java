package com.menthoven.arduinoandroid;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by da Ent on 28/11/2015.
 */
public class ChatAdapter extends ArrayAdapter<ChatMessage> {
    public double convertedWeight;
    public String initWeight = "1";
    public String finalWeight = "1";

    public double convertedTemp;
    public String initTemp = "1";
    public String finalTemp = "1";

    public double convertedHumid;
    public String initHumid = "1";
    public String finalHumid = "1";

    public double distance;

    public double [] weightArray = new double [10];
    int w = 0;
    public double [] distaneArray = new double [10];
    int d = 0;
    public double [] tempArray = new double [10];
    int t = 0;
    public double [] humidArray = new double [10];
    int h = 0;

    public double averageWeight;
    public double averageDistance;
    public double averageTemp;
    public double averageHumid;

    public double averageWeightFinal;
    public double averageDistanceFinal;
    public double averageTempFinal;
    public double averageHumidFinal;

    // View lookup cache
    static class ViewHolder {


        @Bind(R.id.time_text_view) TextView time;
        @Bind(R.id.device_text_view) TextView device;
        @Bind(R.id.message_text_view) TextView message;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ChatAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ChatMessage chatMessage = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_message, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
       /* if (BluetoothActivity.showTimeIsChecked) {
            viewHolder.time.setText(chatMessage.getTime());
        } else {
            viewHolder.time.setText("");
        }
        */
        // PARSE AND CONVERT WEIGHT
        try{
            if (chatMessage.getMessage().charAt(0) == '-'){
                convertedWeight = 0;

                if(w < 10){
                    weightArray[w] = convertedWeight;
                    w++;
                }
                else{
                    w = 0;
                    for (int i = 0; i < 10; i++){
                        averageWeight = averageWeight + weightArray[i];
                    }
                    averageWeightFinal = averageWeight/10;
                    averageWeight = 0;
                    weightArray = new double [10];
                }
            }
            else{
                initWeight = chatMessage.getMessage().split("W")[0];
                initWeight = initWeight.replaceAll("\\D+","");
                convertedWeight = Double.parseDouble(initWeight);
                convertedWeight = convertedWeight /200;

                if(w < 10){
                    weightArray[w] = convertedWeight;
                    w++;
                }
                else{
                    w = 0;
                    for (int i = 0; i < 10; i++){
                        averageWeight = averageWeight + weightArray[i];
                    }
                    averageWeightFinal = averageWeight/10;
                    averageWeight = 0;
                    weightArray = new double [10];
                }
            }
            finalWeight = Double.toString(convertedWeight);
            viewHolder.time.setText(finalWeight);


            // PARSE AND CONVERT TEMPERATURE
            initTemp = chatMessage.getMessage();
            initTemp = initTemp.substring(initTemp.indexOf("W") + 1);
            initTemp = initTemp.substring(0, initTemp.indexOf("T"));
            initTemp = initTemp.replaceAll("\\D+","");
            convertedTemp = Double.parseDouble(initTemp);
            convertedTemp = convertedTemp/100;

            if(t < 10){
               tempArray[t] = convertedTemp;
                t++;
            }
            else{
                t = 0;
                for (int i = 0; i < 10; i++){
                    averageTemp = averageTemp + tempArray[i];
                }
                averageTempFinal = averageTemp/10;
                averageTemp = 0;
                tempArray = new double [10];
            }
            finalTemp = Double.toString(convertedTemp);
            viewHolder.device.setText(finalTemp);


            // PARSE AND CONVERT HUMIDITY
            initHumid = chatMessage.getMessage();
            initHumid = initHumid.substring(initHumid.indexOf("T") + 1);
            initHumid = initHumid.substring(0, initHumid.indexOf("H"));
            initHumid = initHumid.replaceAll("\\D+","");
            convertedHumid = Double.parseDouble(initHumid);
            convertedHumid = convertedHumid/100;

            if(h < 10){
                humidArray[h] = convertedHumid;
                h++;
            }
            else{
                h = 0;
                for (int i = 0; i < 10; i++){
                    averageHumid = averageHumid + humidArray[i];
                }
                averageHumidFinal = averageHumid/10;
                averageHumid = 0;
                humidArray = new double [10];
            }
            finalHumid = Double.toString(convertedHumid);
            //viewHolder.message.setText(finalHumid);
        }
        catch (StringIndexOutOfBoundsException | NumberFormatException e){
        }

        //calculate the remaining distance
        distance = distanceCalc(averageWeightFinal, averageTempFinal, averageHumidFinal);
        viewHolder.message.setText(Double.toString(distance));

        // Return the completed to render on screen
        return convertView;
    }

    public double distanceCalc(double weight, double temp, double humid){

        double tempFactor = -(1/130)*temp + 2;
        double tempHumid = -(1/130)*humid + 2;

        double distance = ((tempFactor + tempHumid)/2)*weight;
        distance = distance/100;
        return distance;
    }

    public double getConvertedWeight(){
        return convertedWeight;
    }

    public double getDistance(){
        return distance;
    }

    public double getConvertedTemp(){
        return convertedTemp;
    }
    public double getConvertedHumid(){
        return convertedHumid;
    }

    public double getAverageWeightFinal(){
        return averageWeightFinal;
    }

    public double getAverageDistanceFinal(){
        return averageDistanceFinal;
    }

    public double getAverageTempFinal(){
        return averageTempFinal;
    }
    public double getAverageHumidFinal(){
        return averageHumidFinal;
    }


}