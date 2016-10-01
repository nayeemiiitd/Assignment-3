package iiitd.nayeem.assignment_3;

/**
 * Created by Mohammad on 9/29/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AddNoteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final EditText heading,notes;
        Button note_submit,view_note;

        View v = inflater.inflate(R.layout.note_fragment,container,false);
        heading = (EditText) v.findViewById(R.id.heading);
        notes = (EditText) v.findViewById(R.id.notes);
        note_submit = (Button) v.findViewById(R.id.note_submit);
        view_note = (Button) v.findViewById(R.id.view_note);

        note_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!heading.getText().toString().equals("") && !notes.getText().toString().equals(""))
                {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        try {
                            File myFile = new File("/sdcard/" + "add_notes.txt");
                            FileWriter fw = new FileWriter(myFile, true);
                            fw.append(heading.getText().toString() + "," + notes.getText().toString());
                            fw.append("\n");
                            fw.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getActivity(), "Notes added", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(view.getContext(), ViewNoteActivity.class);
                        startActivity(a);
                    }
                }
                else
                    Toast.makeText(getActivity(), "Please Fill all the Details", Toast.LENGTH_SHORT).show();
            }
        });

        view_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Opening new clickable list view activity", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(view.getContext(),ViewNoteActivity.class);
                startActivity(a);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
