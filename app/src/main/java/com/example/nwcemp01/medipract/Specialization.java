/*
package com.example.nwcemp01.medipract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.UUID;

import Api.specializationapi;
import Api.specializationdata;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Function;


public class Specialization extends Activity {
    View v;
    LayoutInflater mInflater = null;
    ;
    String mCategory;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<specializationdata> mGetCategoryDetails;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    specializationdata categorydetail;
    ArrayList<specializationdata> CategoryArryList = new ArrayList<>();

    // View vSpecialization;
    public Specialization() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
 */
/*   public static Specialization newInstance(String param1, String param2) {
        Specialization fragment = new Specialization();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*//*

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_specialization);
       */
/* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



         vSpecialization =inflater.inflate(R.layout.fragment_specialization, container, false);

       *//*
 //Toolbar   mToolbars = (Toolbar)findViewById(R.id.tb_header_view);
        //TextView  mToolTitle = (TextView)findViewById(R.id.tv_toolbar_title);


        boolean network = Function.isNetworkAvailable(Specialization.this);
        if (network == false) {
            Function.showToast(getParent(), "Please activate the network", Toast.LENGTH_LONG);
        } else {
            final CustomProgressDialog oCustomProgressDialog = CustomProgressDialog.getInstance();
            oCustomProgressDialog.show(Specialization.this, "", "Please wait...");


            specializationapi.getInstance().Callresponse(new Callback<specializationapi.ResponseBasespecialization>() {
                @Override
                public void success(specializationapi.ResponseBasespecialization responseBasespecialization, Response response) {
                    oCustomProgressDialog.dismiss();

                    if (responseBasespecialization.getData().size() > 0) {
                        for (int j = 0; j < responseBasespecialization.getData().size(); j++) {
                            mCategory = responseBasespecialization.getData().get(j).getSpeacialist_name();
                            categorydetail = new specializationdata();
                            oCustomProgressDialog.dismiss();
                            categorydetail.setSpeacialist_name(responseBasespecialization.getData().get(j).getSpeacialist_name());
                            categorydetail.setImage(responseBasespecialization.getData().get(j).getImage());
                            categorydetail.setSpeacialist_id(responseBasespecialization.getData().get(j).getSpeacialist_id());
                            CategoryArryList.add(categorydetail);

                            Log.v("Res mcategoryname", "" + CategoryArryList.size());
                        }
                    } else {

                    }

                    getDynamicSpecialization();


                }

                @Override
                public void failure(RetrofitError error) {
                    oCustomProgressDialog.dismiss();
                    Toast.makeText(Specialization.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, "4");


        }


        //mToolbars.setNavigationIcon(R.drawable.ic_back);
        //   mToolTitle.setText("Sign in");
        // getContext().setSupportActionBar(mToolbars);


        //   return vSpecialization;


    }

    private void getDynamicSpecialization() {
        LinearLayout llChid = null;
        LinearLayout lParent = (LinearLayout) findViewById(R.id.ll_parent);
        lParent.setGravity(Gravity.CENTER);

        mInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //    Log.d("Size111", "" + CategoryArryList.size());
        double a = (float) CategoryArryList.size() / 2;
        double mCategorySize = Math.ceil(a);
        //    Log.d("Size", "" + mCategorySize);


        for (int i = 0; i < mCategorySize; i++) {
            llChid = new LinearLayout(getApplication());
            llChid.setOrientation(LinearLayout.HORIZONTAL);
            llChid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llChid.setGravity(Gravity.CENTER);
            //  img.setScaleType(ImageView.ScaleType.FIT_XY);
            llChid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getApplicationContext(), Doctor.class);

                }
            });
            for (int j = 0; j < 2; j++) {

                int z = j + (i * 2);

                v = mInflater.inflate(R.layout.activity_gridcontent, null);

                v.setId(100 + z);
                v.setCameraDistance(3);
                ImageView mCategoryImage = (ImageView) v.findViewById(R.id.RVCI_IV_category_image);
                final TextView mCategoryName = (TextView) v.findViewById(R.id.RVCI_TV_category_name);
                mCategoryImage.setPadding(2, 2, 2, 2);


                llChid.addView(v);
                if (CategoryArryList.size() > z) {

                    categorydetail = CategoryArryList.get(z);

                    final String categoryid = CategoryArryList.get(z).getSpeacialist_name();
                    final String categoryimg = CategoryArryList.get(z).getImage();
                    /// Log.v("CATEGORYID", categoryid);

                    mCategoryName.setText(categoryid);
                    Glide.with(getApplicationContext()).load(categoryimg).signature(new StringSignature(UUID.randomUUID().toString())).into(mCategoryImage);
                }
            }
            lParent.addView(llChid);

        }
    }

    public static Specialization newInstance(String hospital_id) {
        return null;
    }

    */
/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
*/
