package com.ma.toothapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Details extends AppCompatActivity {
    BottomSheetBehavior bottomSheetBehavior;
    CustomBottomSheetView customView;
    float gap0;
    float gap ;
    Display display;
    Point size = new Point();
    CustomSolidArc customSolidArc;
    ImageView clock_image;
    Toolbar bottom_sheet_toolbar;
    TextView hourTV;
    TextView minuteTV;
    ArrayList<TextView> figuresList;
    int hourTV_focus;
    int minuteTV_focus;

    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.details);



        /**Processing the viewPager of the bottomSheet**/


        Schedule schedule=new Schedule("raw format");
        Calendar dateOfToday = Calendar.getInstance();
        dateOfToday.setLenient(false);

            SingleCardData singleCardData= new SingleCardData(dateOfToday, schedule.getScheduleString(dateOfToday));


            final int[] hour_focus = new int[1];
            final int[] minute_focus = new int[1];


            final TextView day= findViewById(R.id.day);
            final TextView date= findViewById(R.id.date);
            final CustomSolidArc customSolidArc=findViewById(R.id.csa);
            final ArrayList<TextView> figuresList=new ArrayList<>();


            day.setText(singleCardData.getDay());
            date.setText(singleCardData.getDate());
            customSolidArc.setSolidArc(CustomSolidArc.convertString2Couple(singleCardData.getSchedule()));
            Log.d("PagerAdapter: ", String.valueOf(singleCardData.getSchedule()));


            final TextView hour=findViewById(R.id.hour);
            final TextView minute=findViewById(R.id.minute);


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




        /**End processing the viewPager of the bottomSheet**/




        /**Processing the bottomSheet**/

        hourTV=findViewById(R.id.hour);
        minuteTV=findViewById(R.id.minute);
        hourTV_focus=0;
        minuteTV_focus=0;









        display=getWindowManager().getDefaultDisplay();
        display.getSize(size);
        gap0 = size.x/7;
        /**positioning coordinatorLayout**/
        coordinatorLayout= findViewById(R.id.cd);
        ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) coordinatorLayout.getLayoutParams();
        params2.topMargin = -(int) gap0;

        /**positioning bottom_sheet_toolbar**/
        bottom_sheet_toolbar=findViewById(R.id.bottom_sheet_toolbar);
        ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) bottom_sheet_toolbar.getLayoutParams();
        params3.topMargin = (int) gap0;


        customView=findViewById(R.id.custom_view);
        bottomSheetBehavior= BottomSheetBehavior.from(customView);
        bottomSheetBehavior.setPeekHeight((int) gap0);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }
            }

            @Override
            public void onSlide(@NonNull View view, float p) {
                gap =  ( gap0 *(1-p));
                ((CustomBottomSheetView) view).setCorner((int) gap);
            }
        });


        /**End of bottomSheet**/


    }

    /**For main Sheet**/
    public void afficher_horaire(View view) {
        RecyclerView recyclerView;
        ArrayList<String> list= new ArrayList<>();
        list.add("Lundi");
        list.add("08h00-12h00 & 14h00-18h00");
        list.add("Mardi");
        list.add("08h00-12h00 & 14h00-18h00");
        list.add("Mercredi");
        list.add("08h00-12h00 & 14h00-18h00");
        list.add("Jeudi");
        list.add("08h00-12h00 & 14h00-18h00");
        list.add("Vendredi");
        list.add("08h00-12h00 & 14h00-18h00");
        list.add("Samedi");
        list.add("08h00-12h00");


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.afficher_horaire);
        recyclerView=dialog.findViewById(R.id.my_recycler_view);




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter( list));

        dialog.setTitle("Title...");

        TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    /**For bottomSheet**/

    public void expand(View view) {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED ) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            Toast.makeText(this, "Sélectionnez la date et l'heure du RDV", Toast.LENGTH_SHORT).show();
        }

    }


    public void afficher_carte(View view) {

        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }

    public void showStatisctics(View view) {
        Intent intent = new Intent(this,Statistics.class);
        startActivity(intent);

    }



    public void onBackPressed() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED ) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else{
            super.onBackPressed();
        }

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
