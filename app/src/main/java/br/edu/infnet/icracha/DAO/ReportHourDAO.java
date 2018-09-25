package br.edu.infnet.icracha.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.firebase.FirebaseSingleton;
import br.edu.infnet.icracha.report.AttendanceReport;
import br.edu.infnet.icracha.report.ReportHour;

public class ReportHourDAO {

    private DatabaseReference mDatabaseRef;
    private List<ReportHour> mReportHourList = new ArrayList<>();
    private BaseAdapter adapter;

    public ReportHourDAO(String uid, String data, BaseAdapter adapter, List<ReportHour> reportHourList) {
        mDatabaseRef = FirebaseSingleton.getInstance().getDatabase().getReference("reports").child(uid).child(data);
        this.adapter = adapter;
        this.mReportHourList = reportHourList;
        mDatabaseRef.addChildEventListener(carregar);
    }

    private ChildEventListener carregar = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            ReportHour report = dataSnapshot.getValue(ReportHour.class);
            mReportHourList.add(report);

            adapter.notifyDataSetChanged();

            //Log.i("REPORT_DAO", "" + mAttendanceReportList.size());
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public List<ReportHour> listar(){
        return mReportHourList;
    }
}
