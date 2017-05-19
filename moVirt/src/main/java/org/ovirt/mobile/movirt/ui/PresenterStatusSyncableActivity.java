package org.ovirt.mobile.movirt.ui;

import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.ovirt.mobile.movirt.ui.mvp.BasePresenter;
import org.ovirt.mobile.movirt.ui.mvp.BaseView;
import org.ovirt.mobile.movirt.ui.mvp.StatusView;

@EActivity
public abstract class PresenterStatusSyncableActivity extends SyncableActivity implements StatusView, BaseView {

    @ViewById
    public TextView statusText;

    @Override
    public abstract BasePresenter getPresenter();

    public void displayTitle(String title) {
        setTitle(title);
    }

    @Override
    protected void onStop() {
        if (isFinishing()) {
            if (getPresenter() != null) {
                getPresenter().destroy();
            }
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
        super.onDestroy();
    }

    public void displayStatus(String status) {
        if (statusText == null) {
            throw new IllegalStateException("TextView statusText is missing in this activity.");
        }
        statusText.setText(status);
    }
}
