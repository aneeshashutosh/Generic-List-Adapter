package com.aneeshashutosh.android.genericlistadaptersample;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * @author aneeshashutosh
 */
public class GenericListAdapter extends BaseAdapter {
    public static final String ERROR_TAG = "GenericListAdapterError";

    private Activity mActivity;
    private int mLayoutId;
    private ArrayList<Object> mObjectList;
    private ArrayList<String> mViewIdList;

    public GenericListAdapter(Activity mActivity, int mLayoutId, Object... objects) {
        this.mActivity = mActivity;
        this.mLayoutId = mLayoutId;
        mObjectList = new ArrayList<>();
        mViewIdList = new ArrayList<>();
        for (Object obj : objects) {
            mObjectList.add(obj);
        }
    }

    @Override
    public int getCount() {
        return mObjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mActivity.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(mLayoutId, parent, false);
        getAllChildViews(layout);

        if (mViewIdList.size() != mObjectList.size()) {
            throw new InvalidParameterException("You must supply the same amount of parameters as "
                    + "views with IDs.");
        }

        for (int i = 0; i < mViewIdList.size(); i++) {
            mActivity.getResources().getIdentifier(mViewIdList.get(i), "id", mActivity.getPackageName());
            int resourceId = -1;
            View view = layoutInflater.inflate(resourceId, parent, false);
            GenericListAdapterParser.parseView(view, mObjectList.get(i));
        }

        return layout;
    }

    private void getAllChildViews(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); ++i) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                getAllChildViews((ViewGroup) child);
            } else {
                try {
                    String idString = child.getResources().getResourceEntryName(child.getId());
                    mViewIdList.add(idString);
                } catch (Resources.NotFoundException e) {
                    Log.e(ERROR_TAG, "Could not find canonical ID for the " +
                            "supplied view (this error is usually safe to ignore).");
                    e.printStackTrace();
                }
            }
        }
    }
}
