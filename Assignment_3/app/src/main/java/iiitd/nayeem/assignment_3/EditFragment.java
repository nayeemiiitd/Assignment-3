package iiitd.nayeem.assignment_3;

/**
 * Created by Mohammad on 9/29/2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFragment extends Fragment {

    private EditText edit_user,edit_pass,edit_mobile,edit_email;
    DatabaseHelper mydb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Button edit_submit;

        View v = inflater.inflate(R.layout.edit_fragment,container,false);

        edit_user = (EditText) v.findViewById(R.id.edit_user);
        edit_email = (EditText) v.findViewById(R.id.edit_email);
        edit_pass = (EditText) v.findViewById(R.id.edit_pass);
        edit_mobile = (EditText) v.findViewById(R.id.edit_mobile);
        edit_submit = (Button) v.findViewById(R.id.edit_submit);

        mydb = new DatabaseHelper(getActivity());

        edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!edit_user.getText().toString().equals("") && !edit_email.getText().toString().equals("") && !edit_pass.getText().toString().equals("")&& !edit_mobile.getText().toString().equals(""))
                {
                    Boolean isUpdate = mydb.updateData(edit_user.getText().toString(),edit_email.getText().toString(),edit_pass.getText().toString(),edit_mobile.getText().toString());
                    if(isUpdate)
                        Toast.makeText(getActivity(), "Data is updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Some error in Updating the data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
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