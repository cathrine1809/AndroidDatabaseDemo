package edu.unikl.stud0104.androiddatabasedemo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import edu.unikl.stud0104.androiddatabasedemo.database.DatabaseHelper;
import edu.unikl.stud0104.androiddatabasedemo.entity.Student;

public class MainActivity extends Activity implements OnClickListener {
	
	
	private static List<Student> list;
	private static TextView textView_value_id;
	private static TextView textView_value_name;
	private static TextView textView_value_samester;
	private static TextView textView_value_course;
	private static TextView textView_recordNo;
	private static int currentStudentIndex;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonPrevious =(Button) findViewById(R.id.button_previous);
        buttonPrevious.setOnClickListener(this);
        
        Button buttonNext = (Button) findViewById(R.id.button_next);
        buttonNext.setOnClickListener(this);
        
        DatabaseHelper db = new DatabaseHelper (getBaseContext());
        
        list = db.getStudentList();
        db.close();
        int currentStudentIndex = 0;
        displayStudentInfo(currentStudentIndex);
    }
    
    @Override
    public void onClick(View v){
    	int buttonId = v.getId();
    	
    
		if (buttonId == R.id.button_previous && currentStudentIndex > 0){
    		displayStudentInfo(--currentStudentIndex);
    	} else if (buttonId == R.id.button_next && currentStudentIndex < list.size()-1){
    		displayStudentInfo(++ currentStudentIndex);
    	}
    }

    private void displayStudentInfo(int index){
    	
    	Student student = list.get(index);
    	textView_value_id = (TextView) findViewById (R.id.textView_value_id);
    	textView_value_name = (TextView) findViewById (R.id.textView_value_name);
    	textView_value_samester = (TextView) findViewById(R.id.textView_value_samester);
    	textView_value_course = (TextView) findViewById(R.id.textView_value_course);
    	textView_value_id = (TextView) findViewById(R.id.textView_value_id);
    	textView_recordNo = (TextView) findViewById(R.id.textView_recordNo);
    	
    	textView_value_id.setText(student.getId());
    	textView_value_name.setText(student.getName());
    	textView_value_samester.setText(""+student.getSamester());
    	textView_value_course.setText(student.getCourse());
    	textView_recordNo.setText("RECORD NO."+ (++ index));
    	
    	
    }
}