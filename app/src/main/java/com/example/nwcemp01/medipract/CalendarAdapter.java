package com.example.nwcemp01.medipract;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarAdapter extends BaseAdapter {
    private Context context;
    String sDateString;
    private Calendar month;
    public GregorianCalendar pmonth;
    private View previousView;
    /**
     * calendar instance for previous month for getting complete view
     */
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int mnthlength;
    String itemvalue;

    DateFormat df;
    String[] arraysplit;
    String abdate, abstatus, sun;
    private ArrayList<String> items;
    public static List<String> day_string;
    public ArrayList<String> date_collection_arrone;
    String mCustomerKey;
    String currentday;
    public static String gridvalue,curentDateString,curentMonthString;
    TextView dayView;
    Date currentTime;
    public CalendarAdapter(Context context, GregorianCalendar monthCalendar, ArrayList<String> date_collection_arr) {
        date_collection_arrone = new ArrayList<String>();
        date_collection_arrone = date_collection_arr;
        CalendarAdapter.day_string = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
         currentTime = localCalendar.getTime();
        currentday=df.format(currentTime);
        refreshDays();


    }

    public void setItems(ArrayList<String> items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }

    public int getCount() {
        return day_string.size();
    }

    public Object getItem(int position) {
        return day_string.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.cal_item, null);


        }
        SharedPreferences sharecustomerkey = context.getSharedPreferences("CustomerKeyFile", context.MODE_PRIVATE);
        mCustomerKey = sharecustomerkey.getString("Customerkey", null);

        dayView = (TextView) v.findViewById(R.id.date);
        String[] separatedTime = day_string.get(position).split("-");
        gridvalue = separatedTime[2].replaceFirst("^0*", "");

        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {

            dayView.setTextColor(Color.BLACK);
        }
       /* */

        if (day_string.get(position).equals(currentday)) {


            dayView.setTextColor(Color.parseColor("#FF0000"));

            sDateString = day_string.get(position);


        } else {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        }


        dayView.setText(gridvalue);


        String date = day_string.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }


        setEventView(v, position);
        return v;
    }


    public void refreshDays() {

        items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();

        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);


        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);

        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);
        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            day_string.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }


    public void setEventView(View v, final int pos) {

        if (pos % 7 == 0) {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {

            v.setBackgroundColor(Color.parseColor("#ffffff"));

        }
        for (int i = 0; i < date_collection_arrone.size(); i++) {

            abdate = date_collection_arrone.get(i);


            if (day_string.get(pos).equals(abdate)) {
               //  v.setBackgroundResource(R.drawable.present);
            }

        }


    }

}
