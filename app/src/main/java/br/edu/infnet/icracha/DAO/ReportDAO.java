package br.edu.infnet.icracha.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.firebase.FirebaseSingleton;
import br.edu.infnet.icracha.report.AttendanceReport;
import br.edu.infnet.icracha.user.User;

public class ReportDAO {

    private DatabaseReference mDatabaseRef;
    private List<AttendanceReport> mAttendanceReportList = new ArrayList<>();

    public ReportDAO() {
        mDatabaseRef = FirebaseSingleton.getInstance().getDatabase().getReference("reports");
        mDatabaseRef.addChildEventListener(carregar);
    }

    public ReportDAO(String cpf) {
        mDatabaseRef = FirebaseSingleton.getInstance().getDatabase().getReference("reports").child(cpf);
        mDatabaseRef.addChildEventListener(carregar);
    }

    private ChildEventListener carregar = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            AttendanceReport report = dataSnapshot.getValue(AttendanceReport.class);
            mAttendanceReportList.add(report);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            AttendanceReport report = dataSnapshot.getValue(AttendanceReport.class);

            for(int i = 0; i < mAttendanceReportList.size(); i++){
                if(mAttendanceReportList.get(i).getDate().equals(report.getDate())){
                    mAttendanceReportList.set(i, report);
                    break;
                }
            }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            AttendanceReport report = dataSnapshot.getValue(AttendanceReport.class);

            for(int i = 0; i < mAttendanceReportList.size(); i++){
                if(mAttendanceReportList.get(i).getDate().equals(report.getDate())){
                    mAttendanceReportList.remove(report);
                    break;
                }
            }
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void salvar(AttendanceReport attendanceReport){
        mDatabaseRef.child(attendanceReport.getDate()).setValue(attendanceReport);
    }

    public List<AttendanceReport> listar(){
        return mAttendanceReportList;
    }

    public void excluir(String date){
        mDatabaseRef.child(date).removeValue();
    }



}