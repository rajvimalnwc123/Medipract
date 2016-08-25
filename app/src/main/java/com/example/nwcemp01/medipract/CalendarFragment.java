package com.example.nwcemp01.medipract;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CalendarFragment extends Activity {
    GridView mCalender, calenderfavorite_grid;
    GregorianCalendar cal_month, cal_month_copy;
    String passmonth, passyear;
    ArrayList<String> date_collection_arrone = new ArrayList<String>();
    GridView gridview;
    CalendarAdapter cal_adapter;
    LinearLayout next, previous;
    TextView tv_month;
    ImageView mcalleft, mcalright;
    ImageView favoriteimage;
    String mCustomerKey;

    String sFavoritename, sFavoriteImage;
    String sMonth, sYear;
    // List<CalenderEventFavoriteApi.Favourite> mGetCalenderFavorite = new ArrayList<CalenderEventFavoriteApi.Favourite>();
    LinearLayout mLinearParent;
    int selectedpos = -1;
    String selectEventName = "";

    View mfavoritesborder;


    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static String mDateselect_sharedPref, mHospital_sharedpref;
    public static final String Dateselect = "dateselect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar);

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
  */     /* SharedPreferences sharecustomerkey = getActivity().getSharedPreferences("CustomerKeyFile", getActivity().MODE_PRIVATE);
        mCustomerKey = sharecustomerkey.getString("Customerkey", null);
        date_collection_arrone.clear();
       */
        gridview = (GridView) findViewById(R.id.gv_calendar);
        //   favoriteimage = (ImageView) view.findViewById(R.id.FC_image);


        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();

        mcalleft = (ImageView) findViewById(R.id.AL_cal_left);
        mcalright = (ImageView) findViewById(R.id.AL_cal_right);
        next = (LinearLayout) findViewById(R.id.Ib_next);
        previous = (LinearLayout) findViewById(R.id.ib_prev);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
        calenderfavorite_grid = (GridView) findViewById(R.id.FC_gv_favorites);
        mfavoritesborder = (View) findViewById(R.id.FC_vi_favoritesborder);

        passmonth = String.valueOf(android.text.format.DateFormat.format("MM", cal_month));
        passyear = String.valueOf(android.text.format.DateFormat.format("yyyy", cal_month));

        sMonth = String.valueOf(android.text.format.DateFormat.format("MMMM", cal_month));
        sYear = String.valueOf(android.text.format.DateFormat.format("yyyy", cal_month));

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        Date currentTime = localCalendar.getTime();

        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);


        System.out.println("Maont : " + String.valueOf(currentMonth));
        System.out.println("Year : " + String.valueOf(currentYear));
/*
        if (objutility.oncheckinginternetconnection(getActivity()) == true) {
            objutility = CommonUtility.getInstance();
            objutility.alert(getActivity(), "", "Please wait...");
            BaseApi.get().getfavSearchformonthdetails(mCustomerKey, String.valueOf(currentMonth), String.valueOf(currentYear), new Callback<RFFavMonth>() {
                @Override
                public void success(RFFavMonth rfFavMonth, Response response) {
                    if (rfFavMonth.getHttpcode() == 200) {
                        for (int i = 0; i < rfFavMonth.getData().getFavourite().size(); i++) {
                            String temparr[] = rfFavMonth.getData().getFavourite().get(i).getCalendar().split(",");
                            for (int arr = 0; arr < temparr.length; arr++) {
                                date_collection_arrone.add(temparr[arr]);

                            }

                            if (!rfFavMonth.getData().getFavourite().get(i).getCalendar().isEmpty()) {
                                calenderfavorite_grid.setVisibility(View.VISIBLE);
                                mfavoritesborder.setVisibility(View.VISIBLE);
                            }

                            cal_adapter = new CalendarAdapter(getActivity(), cal_month, date_collection_arrone);
                            gridview.setAdapter(cal_adapter);
                            cal_adapter.notifyDataSetChanged();

                        }

                    }
                    objutility.dismiss();

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_SHORT).show();
                    objutility.dismiss();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_LONG).show();
        }*/

        mcalleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPreviousMonth();
                refreshCalendar();
            }
        });

        mcalright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        /***************************************/
        //intially display calender
        cal_adapter = new CalendarAdapter(getApplicationContext(), cal_month, date_collection_arrone);
        gridview.setAdapter(cal_adapter);
        cal_adapter.notifyDataSetChanged();
        //   gridview.setBackgroundColor(Color.parseColor("#34343c"));
        gridview.setVerticalSpacing(3);


/*
        try

        {

            String sEventDate = CalendarAdapter.curentDateString;

            if (objutility.oncheckinginternetconnection(getActivity()) == true) {
               */
/* objutility = CommonUtility.getInstance();
                objutility.alert(getActivity(), "", "Please wait...");*//*

                CalenderEventFavoriteApi.getInstance().CallresponseCalendarEvent(mCustomerKey, sEventDate, new Callback<CalenderEventFavoriteApi.ResponseCalendarEventFavoriteAPI>() {
                    @Override
                    public void success(CalenderEventFavoriteApi.ResponseCalendarEventFavoriteAPI responseCalendarEventFavoriteAPI, Response response) {
                        //   objutility.dismiss();
                        if (responseCalendarEventFavoriteAPI.getData().getFavourite().isEmpty()) {
                            //  Toast.makeText(getActivity(), responseCalendarEventFavoriteAPI.getMessage(), Toast.LENGTH_SHORT).show();
                            calenderfavorite_grid.setVisibility(View.INVISIBLE);
                            mfavoritesborder.setVisibility(View.INVISIBLE);
                        } else if (!responseCalendarEventFavoriteAPI.getData().getFavourite().isEmpty())

                        {
                            calenderfavorite_grid.setVisibility(View.VISIBLE);
                            mfavoritesborder.setVisibility(View.VISIBLE);
                            //  Toast.makeText(getActivity(), responseCalendarEventFavoriteAPI.getMessage(), Toast.LENGTH_SHORT).show();
                            CalendarFavoriteEventAdapter mFavouriteAdapter = new CalendarFavoriteEventAdapter(getActivity(), responseCalendarEventFavoriteAPI.getData().getFavourite());
                            calenderfavorite_grid.setAdapter(mFavouriteAdapter);
                        }
                        //  objutility.dismiss();
                    }


                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_SHORT).show();
                        // objutility.dismiss();

                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_LONG).show();
            }


        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }
*/


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                                                String sEventDate = CalendarAdapter.day_string.get(position);
                                                Log.v("SEvent date", sEventDate);


                                                sharedpreferences = getApplicationContext().getSharedPreferences(
                                                        MyPREFERENCES, MODE_PRIVATE);
                                                editor = sharedpreferences.edit();
                                                editor.putString(Dateselect, sEventDate);
                                                editor.commit();
                                                mDateselect_sharedPref = sharedpreferences.getString(Dateselect, null);


                                                Intent in = new Intent(CalendarFragment.this, BooklaterSpecialization.class);
                                                startActivity(in);


/*

                                                if (objutility.oncheckinginternetconnection(getActivity()) == true) {
                                                    objutility = CommonUtility.getInstance();
                                                    objutility.alert(getActivity(), "", "Please wait...");


                                                    try

                                                    {
                                                        CalenderEventFavoriteApi.getInstance().CallresponseCalendarEvent(mCustomerKey, sEventDate, new Callback<CalenderEventFavoriteApi.ResponseCalendarEventFavoriteAPI>() {
                                                            @Override
                                                            public void success(CalenderEventFavoriteApi.ResponseCalendarEventFavoriteAPI responseCalendarEventFavoriteAPI, Response response) {
                                                                if (responseCalendarEventFavoriteAPI.getData().getFavourite().isEmpty()) {
                                                                    Toast.makeText(getActivity(), responseCalendarEventFavoriteAPI.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    calenderfavorite_grid.setVisibility(View.INVISIBLE);
                                                                    mfavoritesborder.setVisibility(View.INVISIBLE);
                                                                } else if (!responseCalendarEventFavoriteAPI.getData().getFavourite().isEmpty())

                                                                {
                                                                    calenderfavorite_grid.setVisibility(View.VISIBLE);
                                                                    mfavoritesborder.setVisibility(View.VISIBLE);
                                                                    Toast.makeText(getActivity(), responseCalendarEventFavoriteAPI.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    CalendarFavoriteEventAdapter mFavouriteAdapter = new CalendarFavoriteEventAdapter(getActivity(), responseCalendarEventFavoriteAPI.getData().getFavourite());
                                                                    calenderfavorite_grid.setAdapter(mFavouriteAdapter);
                                                                }
                                                                objutility.dismiss();

                                                            }


                                                            @Override
                                                            public void failure(RetrofitError error) {
                                                                Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_SHORT).show();
                                                                objutility.dismiss();

                                                            }
                                                        });
                                                    } catch (
                                                            Exception e
                                                            )

                                                    {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_LONG).show();
                                                }


                                            }*/
                                            }
                                        }

        );
        gridview.setHorizontalSpacing(3);

    }

    protected void setNextMonth() {
        calenderfavorite_grid.setVisibility(View.INVISIBLE);
        mfavoritesborder.setVisibility(View.INVISIBLE);

        if (cal_month.get(GregorianCalendar.MONTH) == cal_month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1),
                    cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }

        passmonth = String.valueOf(android.text.format.DateFormat.format("MM", cal_month));
        passyear = String.valueOf(android.text.format.DateFormat.format("yyyy", cal_month));
       /* if (objutility.oncheckinginternetconnection(getActivity()) == true) {
            objutility = CommonUtility.getInstance();
            objutility.alert(getActivity(), "", "Please wait...");
            BaseApi.get().getfavSearchformonthdetails(mCustomerKey, passmonth, passyear, new Callback<RFFavMonth>() {
                @Override
                public void success(RFFavMonth rfFavMonth, Response response) {
                    if (rfFavMonth.getHttpcode() == 200) {
                        for (int i = 0; i < rfFavMonth.getData().getFavourite().size(); i++) {
                            String temparr[] = rfFavMonth.getData().getFavourite().get(i).getCalendar().split(",");
                            for (int arr = 0; arr < temparr.length; arr++) {
                                date_collection_arrone.add(temparr[arr]);
                            }
                            // date_collection_arrone.add(rfFavMonth.getData().getFavourite().get(i).getCalendar());
                            //   Toast.makeText(getActivity(),rfFavMonth.getData().getFavourite().get(i).getCalendar(),Toast.LENGTH_SHORT).show();
                            if (!rfFavMonth.getData().getFavourite().get(i).getCalendar().isEmpty()) {
                                //  calenderfavorite_grid.setVisibility(View.VISIBLE);
                                //   mfavoritesborder.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "Please select your event", Toast.LENGTH_SHORT).show();
                            }

                            cal_adapter = new CalendarAdapter(getActivity(), cal_month, date_collection_arrone);
                            gridview.setAdapter(cal_adapter);
                            cal_adapter.notifyDataSetChanged();

                        }

                    }
                    objutility.dismiss();

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_SHORT).show();
                    objutility.dismiss();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_LONG).show();
        }*/

    }

    protected void setPreviousMonth() {

        calenderfavorite_grid.setVisibility(View.INVISIBLE);
        mfavoritesborder.setVisibility(View.INVISIBLE);
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1),
                    cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) - 1);
        }

        passmonth = String.valueOf(android.text.format.DateFormat.format("MM", cal_month));
        passyear = String.valueOf(android.text.format.DateFormat.format("yyyy", cal_month));
/*
        if (objutility.oncheckinginternetconnection(getActivity()) == true) {
            objutility = CommonUtility.getInstance();
            objutility.alert(getActivity(), "", "Please wait...");
            BaseApi.get().getfavSearchformonthdetails(mCustomerKey, passmonth, passyear, new Callback<RFFavMonth>() {
                @Override
                public void success(RFFavMonth rfFavMonth, Response response) {
                    if (rfFavMonth.getHttpcode() == 200) {
                        for (int i = 0; i < rfFavMonth.getData().getFavourite().size(); i++) {
                            String temparr[] = rfFavMonth.getData().getFavourite().get(i).getCalendar().split(",");
                            for (int arr = 0; arr < temparr.length; arr++) {
                                date_collection_arrone.add(temparr[arr]);
                            }
                            // date_collection_arrone.add(rfFavMonth.getData().getFavourite().get(i).getCalendar());
                            // Toast.makeText(getActivity(),rfFavMonth.getData().getFavourite().get(i).getCalendar(),Toast.LENGTH_SHORT).show();
                            if (!rfFavMonth.getData().getFavourite().get(i).getCalendar().isEmpty()) {
                                //calenderfavorite_grid.setVisibility(View.VISIBLE);
                                //mfavoritesborder.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "Please select your event", Toast.LENGTH_SHORT).show();
                            }
                            cal_adapter = new CalendarAdapter(getActivity(), cal_month, date_collection_arrone);
                            gridview.setAdapter(cal_adapter);
                            cal_adapter.notifyDataSetChanged();


                        }

                    }
                    objutility.dismiss();

                }

                @Override
                public void failure(RetrofitError error) {
                    objutility.dismiss();
                    Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Please check your internet connect", Toast.LENGTH_LONG).show();
        }

*/

    }

    public void refreshCalendar() {
        cal_adapter.refreshDays();
        cal_adapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
        passmonth = String.valueOf(android.text.format.DateFormat.format("MM", cal_month));
        passyear = String.valueOf(android.text.format.DateFormat.format("yyyy", cal_month));


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CalendarFragment.this, Booking.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
