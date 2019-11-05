package com.ma.toothapplication;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

  private  ArrayList<SingleCardData> list;

  public PagerAdapter(ArrayList<SingleCardData> list, Context context){
      this.list=list;
  }
  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {

    final int[] hour_focus = new int[1];
    final int[] minute_focus = new int[1];
    View item = LayoutInflater.from(container.getContext()).inflate(R.layout.rdv_card, container, false);


    final TextView day= item.findViewById(R.id.day);
    final TextView date= item.findViewById(R.id.date);
    final CustomSolidArc customSolidArc=item.findViewById(R.id.csa);
    final ArrayList<TextView> figuresList=new ArrayList<>();


    day.setText(list.get(position).getDay());
    date.setText(list.get(position).getDate());
    customSolidArc.setSolidArc(CustomSolidArc.convertString2Couple(list.get(position).getSchedule()));
    Log.d("PagerAdapter: ", String.valueOf(list.get(position).getSchedule()));


    container.addView(item);
    final TextView hour=item.findViewById(R.id.hour);
    final TextView minute=item.findViewById(R.id.minute);


    hour.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        minute.setTextColor(Color.WHITE);
        minute_focus[0] =0;
        hour.setTextColor(ContextCompat.getColor(view.getContext(), R.color.SELECTED_TEXT));
//        TextView tV= (TextView) customSolidArc.getChildAt(0);
//        tV.setTextColor(ContextCompat.getColor(this, R.color.SELECTED_ITEM));
        hour_focus[0] =1;
        customSolidArc.assignFigures(1);
      }
    });
    minute.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        hour.setTextColor(Color.WHITE);
        hour_focus[0] =0;
        minute.setTextColor(ContextCompat.getColor(view.getContext(), R.color.SELECTED_TEXT));
        minute_focus[0] =1;
        customSolidArc.assignFigures(2);

      }
    });
    figuresList.clear();
    for (int i = 0; i < 12; i++) {
      figuresList.add((TextView) customSolidArc.getChildAt(i));
      final int finalI = i;
      figuresList.get(i).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (hour_focus[0] == 1){
            hour.setText(figuresList.get(finalI).getText().toString());

          } else if (minute_focus[0] ==1){
            minute.setText(figuresList.get(finalI).getText().toString());

          }
        }
      });
    }

    /**processing picking a date**/
    final Calendar myCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabels(myCalendar, date, day);
      }
    };

    date.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        new DatePickerDialog(v.getContext(), onDateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
    day.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        new DatePickerDialog(v.getContext(), onDateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
    /**end of picking date code**/

    return item;
  }

  @Override public int getCount() {
    return list.size();
  }

  @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
  }
  private void updateLabels(Calendar myCalendar, TextView date, TextView day) {

    /**update the current cardView**/
    String myFormat = "dd/MM/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRENCH);
    date.setText(sdf.format(myCalendar.getTime()));

    String[] days = new String[] {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    Log.d("mDay of week", String.valueOf(myCalendar.get(Calendar.DAY_OF_WEEK)));
    day.setText(   days[myCalendar.get(Calendar.DAY_OF_WEEK)-1]);
    /**update the following cards*/




  }
}
