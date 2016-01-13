package cgc.shootyonev2;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class JavaFragment extends Fragment implements OnClickListener {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view=inflater.inflate(R.layout.j_frag, container, false);
        
        
        
        Button btnJava;
        
        btnJava = (Button)view.findViewById(R.id.btnJava);

        btnJava.setOnClickListener(this);

        
        return view;
    }
	
	@Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), LevelOne.class));
        }

}

