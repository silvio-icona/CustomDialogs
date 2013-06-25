package com.example.customdialogs;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button normal = (Button) findViewById(R.id.normaldialog);
		Button popupbutton = (Button) findViewById(R.id.popupbutton);
		Button transparent = (Button) findViewById(R.id.transparentdialog);
		
		normal.setOnClickListener(this);
		popupbutton.setOnClickListener(this);
		transparent.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		HashMap<String, Object> par = new HashMap<String, Object>();

	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    LayoutInflater inflater = this.getLayoutInflater();
		
	    TextView personaltitle = new TextView(this);
	    LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    
		switch(id)
		{
			case R.id.normaldialog:
			    builder.setView(inflater.inflate(R.layout.mydialog, null));
			    personaltitle.setText("NORMAL DIALOG");
			    personaltitle.setTextColor(Color.RED);
			    personaltitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
			    builder.setCustomTitle(personaltitle);

				builder.setNeutralButton("BACK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
					
				});
				
				AlertDialog dialog = builder.create();
			    dialog.show();
				break;
				
			case R.id.popupbutton:
				par.put("width", Integer.valueOf(760));
				par.put("height", Integer.valueOf(1092));
				par.put("posx", Integer.valueOf(20));
				par.put("posy", Integer.valueOf(20));

				par.put("layout", Integer.valueOf(R.layout.popuptest));
				par.put("ref", Integer.valueOf(R.id.popup));
				
				initiatePopupWindow(par);
				break;
				
			case R.id.transparentdialog:
				par.put("width", Integer.valueOf(760));
				par.put("height", Integer.valueOf(1092));
				par.put("posx", Integer.valueOf(20));
				par.put("posy", Integer.valueOf(20));

				par.put("layout", Integer.valueOf(R.layout.popuptransparent));
				par.put("ref", Integer.valueOf(R.id.seethrough));
				
				initiatePopupWindow(par);
				break;
			
			case R.id.closebutton:
				if(pw != null)
				{
					pw.dismiss();
					pw = null;
				}
				break;
				
			default:
				break;
		}
	}

	private PopupWindow pw = null;
	private void initiatePopupWindow(HashMap<String, Object> param) 
	{
		int width = 0;
		int height = 0;
		int posX = 0;
		int posY = 0;
		
		int popuplayout = 0;
		int popupref = 0;

    	height = ( (Integer) param.get("height") ).intValue();
    	width = ( (Integer) param.get("width") ).intValue();
    	posX = ( (Integer) param.get("posx") ).intValue();
    	posY = ( (Integer) param.get("posy") ).intValue();
    	
    	popuplayout = ( (Integer) param.get("layout") ).intValue();
    	popupref = ( (Integer) param.get("ref") ).intValue();

    	LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	
        View layout = inflater.inflate(popuplayout, (ViewGroup) findViewById(popupref));

        pw = new PopupWindow(layout, width, height, true);
        
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        
        Button closebutton = (Button) layout.findViewById(R.id.closebutton);
        closebutton.setOnClickListener(this);
	}
	
}
